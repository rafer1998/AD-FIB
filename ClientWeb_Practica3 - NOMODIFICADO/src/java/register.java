/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //CSS -> 
        out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
        
        
        //Obtencion de parametros que ha obtenido el usuario
        String usuario = request.getParameter("usuario");            
        String password = request.getParameter("password");
        String conf_password = request.getParameter("conf_password");
        
        Connection connection = null; 
        try {            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");       
            
            PreparedStatement statement;
            String query;
            
            query = "SELECT * FROM usuarios WHERE id_usuario LIKE '"+ usuario  +"'";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                //Error 1: usuario ya existe
                out.println("<br><h2>Error</h2>");
                out.println("<form action=\"menu.jsp\" method=\"POST\"> <p> <input type=\"submit\" value=\"Volver al menu\"></p></form>");
            }
            else{     
                if(!password.equals(conf_password)){
                    //Error 2: contraseña mal introducida 
                    out.println("<br><h2>Error</h2>");
                    out.println("<form action=\"menu.jsp\" method=\"POST\"> <p> <input type=\"submit\" value=\"Volver al menu\"></p></form>");
                }
                else{
                    //Correcto -> insertar usuario
                    query = "insert into usuarios values(?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, usuario);
                    statement.setString(2, password);                                    
                    statement.executeUpdate();
                    
                    out.println("<h4>Te has registrado correctamente!</h4>");
                    out.println("<form action=\"menu.jsp\" method=\"POST\"> <p> <input type=\"submit\" value=\"Volver al menu\"></p></form>");
                }
            }    
        }
         catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
