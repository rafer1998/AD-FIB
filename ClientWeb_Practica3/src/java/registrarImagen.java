/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
//import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

import org.me.imagenes.Image;
import org.me.imagenes.ServicioImagenes_Service;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/registrarImagen"})
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class registrarImagen extends HttpServlet {

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
        Image img = new Image();          
        
        //Parametros de la imagen
        img.setTitulo(request.getParameter("titulo"));  
        img.setDescripcion(request.getParameter("descripcion"));
        img.setKeywords(request.getParameter("palabras_clave")); 
        
        final Part filePart = request.getPart("fichero");
        String nombrefichero = getFileName(filePart);
        String extensionfichero = getExtensionName(filePart);  
        String nombreTotal = nombrefichero.concat(extensionfichero);        
        img.setFichero(nombreTotal);        
        
        String autor = "NULL";   
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        img.setAutor(autor);        
        img.setCreaDate(request.getParameter("fecha")); 
 
        
        try {            
            out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
            int res = registerImage(img);
            if(res == 1)
                out.println("<h4>Has subido la imagen correctamente!</h4>");
            else 
                response.sendRedirect("error.jsp");    
                        
            out.println("<form action=\"menu.jsp\" method=\"POST\">  ");
            out.println("<input type=\"submit\" value=\"Volver al menu\">");
            out.println("</form>");   
        }
         catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }    
    
    protected String getFileName(Part p){
        String GUIDwithext = Paths.get(p.getSubmittedFileName()).getFileName().toString();
        String GUID = GUIDwithext.substring(0, GUIDwithext.lastIndexOf('.'));
        return GUID;
    }
    
    protected String getExtensionName(Part p){
        String GUIDwithext = Paths.get(p.getSubmittedFileName()).getFileName().toString();
        String GUID = GUIDwithext.substring(GUIDwithext.lastIndexOf('.'));
        return GUID;
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

    private int registerImage(org.me.imagenes.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imagenes.ServicioImagenes port = service.getServicioImagenesPort();
        return port.registerImage(image);
    }
}
