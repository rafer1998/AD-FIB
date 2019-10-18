/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import org.me.imagenes.Image;
import org.me.imagenes.ServicioImagenes_Service;

/**
 *
 * @author ruben
 */
@WebServlet(urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {

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
        PrintWriter out = response.getWriter();  
        
        //CSS
        out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
        
        //Parametros para eliminar imagen
        int id = Integer.parseInt(request.getParameter("id")); 
         
        try {            
            Image img = searchbyId(id);
            int res = deleteImage(img);
            if(res == 1)            
                out.println("<h4>Has eliminado la imagen correctamente!</h4>");
            else
                out.println("<h4>No se ha eliminado la imagen!</h4>");  
            
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

    private int deleteImage(Image img) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imagenes.ServicioImagenes port = service.getServicioImagenesPort();
        return port.deleteImage(img);
    }

    private Image searchbyId(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imagenes.ServicioImagenes port = service.getServicioImagenesPort();
        return port.searchbyId(id);
    }

}
