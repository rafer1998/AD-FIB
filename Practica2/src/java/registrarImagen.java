/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ruben.barcelo
 */
@WebServlet(urlPatterns = {"/registrarImagen"})
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class registrarImagen extends HttpServlet {

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
        
        final Part filePart = request.getPart("fichero");
        String nombrefichero = getFileName(filePart);
        String extensionfichero = getExtensionName(filePart);        
        
        String titulo = request.getParameter("titulo");            
        String descripcion = request.getParameter("descripcion");        
        String palabras_clave = request.getParameter("palabras_clave");
        
        String autor = "NULL";   
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        
        
        String fecha_creacion = request.getParameter("fecha");  
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_alta = dateformat.format(date);
        Connection connection = null; 
        
        try {
            out.println("<head><style> body {background-color: lightblue; text-align: center; }</style></head>");
            PreparedStatement statement;
            String query;
            int id = -1;
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");        
            query = "select max(id) from imagen";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
                        
            OutputStream outS = null;
            String root = getServletContext().getRealPath("/imagenes");
            outS = new FileOutputStream(new File(root + File.separator + nombrefichero + extensionfichero));
            InputStream inS = null;
            inS = filePart.getInputStream();
            
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = inS.read(bytes)) != -1) {
                outS.write(bytes, 0, read);
            }
            
            if (rs.next()) {
                id = rs.getInt(1)+1;
            }
            try{
                query = "insert into imagen values(?,?,?,?,?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.setString(2, titulo); 
                statement.setString(3, descripcion);
                statement.setString(4, palabras_clave);
                statement.setString(5, autor);
                statement.setString(6, fecha_creacion);
                statement.setString(7, fecha_alta);
                statement.setString(8, nombrefichero+extensionfichero);
                
                statement.executeUpdate();                
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                response.sendRedirect("error.jsp");
            }            
            out.println("<h4>Has subido la imagen correctamente!</h4>");                   
            
            out.println("<form action=\"menu.jsp\" method=\"POST\">  ");
            out.println("<input type=\"submit\" value=\"Volver al menu\">");
            out.println("</form>");   
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        finally {
            try {
                if (connection != null)
                    connection.close();               
            } 
            catch (Exception e) {
                // connection close failed.
                System.err.println(e.getMessage());                
            }
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

}
