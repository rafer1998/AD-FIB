/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.imagenes.Image;
import org.me.imagenes.ServicioImagenes_Service;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Practica3/servicioImagenes.wsdl")
    private ServicioImagenes_Service service;

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
        
        //Sesion            
        HttpSession misession= (HttpSession) request.getSession();
        String autor = (String) misession.getAttribute("autor");        
        
        PrintWriter out = response.getWriter();
        
        //CSS
        out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
        
        try{            
            out.println("<h1> Imagenes encontradas </h1>");  
            List<Image> resultado = searchComb(autorbus, desbus, titulobus, fechabus, palclavebusqueda);
            out.println("<table style=\"width:100%\"> "
                            + "<tr>"
                            + "<th>Id</th> <th>Titulo</th> <th>Descripcion</th> <th>Autor</th> <th>Fecha Creación</th> <th>Fecha_Alta</th> <th>Nombre Fichero</th> <th>Modificar Foto</th> <th>Eliminar Foto</th>"
                            + "</tr> ");
            
            if(!resultado.isEmpty()){               
                for(Image img : resultado){
                    //Datos de la imagen
                    int idf = img.getID();
                    String titulof = img.getTitulo();
                    String descripcionf = img.getDescripcion();
                    String autorf = img.getAutor();
                    String fechaf = img.getCreaDate();
                    String fechaa = img.getCreaDate();
                    String nomf = img.getFichero();
                    String palabra = img.getKeywords();
                    
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
            out.println("</table>");
            
            out.println("<form action=\"buscarImagen.jsp\" method=\"POST\">  ");
            out.println("<input type=\"submit\" value=\"Volver a la busqueda\">");
            out.println("</form>");   
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private java.util.List<org.me.imagenes.Image> searchComb(java.lang.String autorbus, java.lang.String desbus, java.lang.String titulobus, java.lang.String fechabus, java.lang.String palclavebusqueda) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imagenes.ServicioImagenes port = service.getServicioImagenesPort();
        return port.searchComb(autorbus, desbus, titulobus, fechabus, palclavebusqueda);
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
