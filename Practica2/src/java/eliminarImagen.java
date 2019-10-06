/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruben
 */
@WebServlet(urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {

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
        
        String id = request.getParameter("id"); 
        String nombre_fichero = request.getParameter("nombre_fichero");
        out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
        Connection connection = null; 
        try {            
            PreparedStatement statement;
            String query;
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");       
            
            try{
                query = "DELETE FROM IMAGEN "
                        + "WHERE ID = ?"; 
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                statement.executeUpdate(); 
                
                String path = "C:\\Users\\ruben\\Documents\\GitHub\\AD-FIB\\Practica2\\web\\imagenes\\";
                String compl = path.concat(nombre_fichero);    
                
                File f = new File(compl);
                if (f.delete())
                    System.out.println("El fichero ha sido borrado satisfactoriamente");
                else
                    System.out.println("El fichero no puede ser borrado");
                
            }
            catch(Exception e){
                response.sendRedirect("error.jsp");
            }   

            out.println("<h4>Has eliminado la imagen correctamente!</h4>");
            
            out.println("<form action=\"menu.jsp\" method=\"POST\">  ");
            out.println("<input type=\"submit\" value=\"Menu\">");
            out.println("</form>");
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
