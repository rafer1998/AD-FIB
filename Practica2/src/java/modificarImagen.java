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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/modificarImagen"})
public class modificarImagen extends HttpServlet {

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
        Connection connection = null; 
        
        String id = request.getParameter("id"); 
        String titulo = request.getParameter("titulo");            
        String descripcion = request.getParameter("descripcion");        
        String palabras_clave = request.getParameter("palabras_clave");
        String fecha = request.getParameter("fecha");
        out.println("<h4>id: " + id + "</h4>");
        out.println("<h4>titulo: " + titulo + "</h4>");
        out.println("<h4>descripcion: " + descripcion + "</h4>");
        out.println("<h4>palabras_clave: " + palabras_clave + "</h4>");
        out.println("<h4>fecha: " + fecha + "</h4>");
        
        
        try {
            PreparedStatement statement;
            String query;
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");        
            
            try{
            query = "UPDATE IMAGEN "
                    + "SET TITULO = ?, DESCRIPCION = ?, PALABRAS_CLAVE = ?, FECHA_CREACION = ? "
                    + "WHERE id = ?";            
            statement = connection.prepareStatement(query);
            
            statement.setString(1, titulo);
            statement.setString(2, descripcion); 
            statement.setString(3, palabras_clave);
            statement.setString(4, fecha);
            statement.setString(5, id);
            
            statement.executeUpdate();     
            }
            catch(Exception e){
                response.sendRedirect("error.jsp");
            }
            out.println("<h4>Has modificado la imagen correctamente!</h4>");
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet modificarImagen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet modificarImagen at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
