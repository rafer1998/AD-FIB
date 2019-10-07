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
import javax.servlet.http.HttpSession;

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
        
        //Parametros
        String palclavebusqueda = request.getParameter("palabras_clave");
        String autorbus = request.getParameter("autor");
        String desbus = request.getParameter("descripcion");
        String titulobus = request.getParameter("titulo");
        String fechabus = request.getParameter("fecha_creacion");
        String vpalclavebusqueda [] = palclavebusqueda.split(";");
        
        String autor = "NULL";         
        int bool_autor = 0;
        int bool_descrip = 0;
        int bool_titulo = 0;
        int bool_fecha = 0;
        int bool_pal = 0;
        
        //Sesion
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        
        Connection connection = null; 
        PrintWriter out = response.getWriter();
        
        //CSS
        out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
        
        try{
            out.println("<h1> Imagenes encontradas </h1>");
            
            //Comprobacion -> campo de datos vacio [0] o no [1]
            if (autorbus != null && !autorbus.isEmpty())
                bool_autor = 1;
            if (desbus != null && !desbus.isEmpty())
                bool_descrip = 1;
            if (titulobus != null && !titulobus.isEmpty())
                bool_titulo = 1;
            if (fechabus != null && !fechabus.isEmpty())
                bool_fecha = 1;
            if (palclavebusqueda != null && !palclavebusqueda.isEmpty())
                bool_pal = 1;
            
            int num = -1;
            PreparedStatement statement,statement2;
            String query, query2;
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            //Seleccion de todas las imagenes
            query = "select * from imagen"; 
            //Cuenta todas las imagenes de la BD
            query2 = "select count(id) from imagen";
            
            statement = connection.prepareStatement(query);
            statement2 = connection.prepareStatement(query2);
            ResultSet rs = statement.executeQuery(); 
            ResultSet rs2 = statement2.executeQuery(); 
            
            if (rs2.next()) {
                //Numero de imagenes en la BD
                num = rs2.getInt(1);
            }
            if (num > 0){
                out.println("<table style=\"width:100%\"> "
                        + "<tr>"
                        + "<th>Id</th> <th>Titulo</th> <th>Descripcion</th> <th>Autor</th> <th>Fecha Creación</th> <th>Fecha_Alta</th> <th>Nombre Fichero</th> <th>Modificar Foto</th> <th>Eliminar Foto</th>"
                        + "</tr> ");
                while (rs.next()) {
                    //Bucle con todas las imagenes
                    int trobat = 0;
                    int falla = 0;
                    
                    //Obtencion parametros de la imagen -> BD
                    String palabra = rs.getString("palabras_clave");
                    String[] parts = palabra.split(";");  
                    String idf = rs.getString("id");
                    String titulof = rs.getString("titulo");
                    String descripcionf = rs.getString("descripcion");
                    String autorf = rs.getString("autor");
                    String fechaf = rs.getString("fecha_creacion");
                    String fechaa = rs.getString("fecha_alta");
                    String nomf = rs.getString("nombre_fichero");
                    
                     if (bool_pal != 0) {
                        //Comprobacion palabras clave
                        for (int i = 0; i < parts.length && trobat == 0; ++i) {
                            for (int j = 0; j < vpalclavebusqueda.length && trobat == 0; ++j) {
                                if (parts[i].equals(vpalclavebusqueda[j])) {
                                   trobat = 1;
                                   falla = 0;
                                }                         
                            }
                        }
                     }
                    if (bool_autor != 0) {
                        //Comprobacion autor
                        if (!autorf.equals(autorbus)) falla = 1;
                    }
                    if (bool_titulo != 0) {
                        //Comprobacion titulo
                        if (!titulof.equals(titulobus)) falla = 1;
                    }
                    if (bool_descrip != 0) {
                        //Comprobacion descripcion
                        if (!descripcionf.equals(desbus)) falla = 1;
                    }
                    if (falla == 0) {
                        //Si coincide todos los campos ->
                        out.println("<tr> "
                                           + "<td> "+idf+" </td> <td>"+titulof+"</td> <td>"+descripcionf+"</td> <td>"+autorf+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td> <td>"+nomf+"</td>"); 
                                   if (autorf.equals(autor)){
                                       //El usuario es el mismo que el autor de la imagen
                                       out.println("<td><form action=\"modificarImagen.jsp\"> "
                                                    + "<input type=\"submit\" value=\"Modificar Imagen\"> "
                                                    + "<input type=\"hidden\" name=\"titulo\" value =\"" + titulof + "\"> "
                                                    + "<input type=\"hidden\" name=\"descripcion\" value =\"" + descripcionf + "\">"
                                                    + "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + palabra + "\">"
                                                    + "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + fechaf + "\">"
                                                    + "<input type=\"hidden\" name=\"id\" value =\"" + idf + "\">"
                                                    + "</form></td>"
                                                    );
                                       out.println("<td><form action=\"eliminarImagen\" method=\"POST\"> "
                                                    + "<input type=\"submit\" value=\"Eliminar Imagen\"> "
                                                    + "<input type=\"hidden\" name=\"id\" value =\"" + idf + "\">"
                                                    + "<input type=\"hidden\" name=\"nombre_fichero\" value =\"" + nomf + "\">"
                                                    + "</form></td>");
                                   }                               
                                   out.println("</tr>");
                    }
                }
                
            } 
            out.println("</table>");
            
            out.println("<form action=\"menu.jsp\" method=\"POST\">  ");
            out.println("<input type=\"submit\" value=\"Volver al menu\">");
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
