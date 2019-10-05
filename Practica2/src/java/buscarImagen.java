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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

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
        String palclavebusqueda = request.getParameter("campos_busqueda");
        String vpalclavebusqueda [] = palclavebusqueda.split(";");
        String autor = "NULL";
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                  if(cookie.getName().equals("autor")) autor = cookie.getValue();
            }
        }
        Connection connection = null; 
        try (PrintWriter out = response.getWriter()) {
            int num = -1;
            PreparedStatement statement,statement2;
            String query, query2;
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");        
            query = "select * from imagen";
            query2 = "select count(id) from imagen";
            statement = connection.prepareStatement(query);
            statement2 = connection.prepareStatement(query2);
            ResultSet rs = statement.executeQuery(); 
            ResultSet rs2 = statement2.executeQuery(); 
            if (rs2.next()) {
                num = rs2.getInt(1);
            }
            if (num > 0){
                out.println("<table style=\"width:100%\"> <tr>\n <th>Id</th>\n <th>Titulo</th>\n <th>Descripcion</th>\n <th>Autor</th>\n <th>Fecha Creación</th>\n <th>Fecha_Alta</th>\n<th>Nombre Fichero</th>\n<th>Modificar Foto</th>\n</tr> ");
                while (rs.next()) {
                    String palabra = rs.getString("palabras_clave");
                    String[] parts = palabra.split(";");
                    int trobat = 0;
                    for (int i = 0; i < parts.length && trobat == 0; ++i) {
                        for (int j = 0; j < vpalclavebusqueda.length && trobat == 0; ++j) {
                            if (parts[i].equals(vpalclavebusqueda[j])) {
                               trobat = 1;
                               String idf = rs.getString("id");
                               String titulof = rs.getString("titulo");
                               String descripcionf = rs.getString("descripcion");
                               String autorf = rs.getString("autor");
                               String fechaf = rs.getString("fecha_creacion");
                               String fechaa = rs.getString("fecha_alta");
                               String nomf = rs.getString("nombre_fichero");
                               out.println("<tr> <td> "+idf+" </td><td>"+titulof+"</td><td>"+descripcionf+"</td><td>"+autorf+"</td><td>"+fechaf+"</td><td>"+fechaa+"</td><td>"+nomf+"</td>"); 
                               if (autorf.equals(autor)){
                                   out.println("<td><form action=\"modificarImagen.jsp\"> <input type=\"submit\" value=\"Modificar Imagen\"> </form></td></tr>");
                               }
                               else out.println("</tr>");
                            }
                        }
                    }
                }
                
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet buscarImagen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buscarImagen at " + request.getContextPath() + "</h1>");
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
