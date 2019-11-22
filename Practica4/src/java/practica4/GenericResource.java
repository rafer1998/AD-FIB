/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * resources.add(MultiPartFeature.class);
 */
package practica4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author raul.fernandez.almendros
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @param file
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage  (@FormDataParam("title") String title, 
            @FormDataParam("description") String description,
            @FormDataParam("keywords") String keywords,
            @FormDataParam("author") String author,
            @FormDataParam("creation") String crea_date,
            @FormDataParam("file") InputStream uploadedInputStream,
	    @FormDataParam("file") FormDataContentDisposition fileDetail){
        
        Connection connection = null;
        String resultado = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        
        try {      
            //Subir la imagen al servidor
            String pathFile = "C:\\Users\\ruben\\Documents\\GitHub\\AD-FIB\\Practica4\\web\\images\\" + fileDetail.getFileName();
            writeToFile(uploadedInputStream, pathFile);
            String output = "File uploaded to : " + pathFile;
            System.out.println(output);            
            
            //subir la imagen a la BD
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            //id
            int id = 0;
            query = "select max(id) from imagen";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                //Obtener siguiente id
                id = rs.getInt(1)+1;
            }  
            
            //Fecha alta
            Date date = new Date();
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String altaDate = dateformat.format(date);
            
            try{
                query = "insert into imagen values(?,?,?,?,?,?,?,?)";
                statement = connection.prepareStatement(query);
                
                statement.setInt(1, id);
                statement.setString(2, title); 
                statement.setString(3, description);
                statement.setString(4, keywords);
                statement.setString(5, author);
                statement.setString(6, crea_date);
                statement.setString(7, altaDate);
                statement.setString(8, title);
                
                statement.executeUpdate(); 
                resultado += "<h1>Imagen subida correctamente</h1>";
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                resultado +=  "<h1>Imagen no se ha podido subir</h1>";
                return resultado;
            }
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
                resultado += "<h1>Imagen no se ha podido subir</h1>";
                return resultado;
            }
        }
        
        resultado += "<form action=\"/Practica4/menu.jsp\" method=\"POST\">  ";
        resultado += "<input type=\"submit\" value=\"Volver al menu\">";
        resultado += "</form>";
        return resultado;
    }
    
    private void writeToFile(InputStream uploadedInputStream, String pathFile) {
        try {
            OutputStream out = new FileOutputStream(new File(pathFile));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(pathFile));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }    
   
    
    /**
    * POST method to register a new image
    * @param fileName
    * @return
    */
    @Path("download")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String downloadImage  (@FormDataParam("fileName") String fileName){
        String resultado = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        
        try {      
            String filePathEntrada = "C:\\Users\\ruben\\Documents\\GitHub\\AD-FIB\\Practica4\\web\\images\\" + fileName;
            String filePathSalida = "C:\\Users\\ruben\\Downloads\\" + fileName;
            
            File file = new File(filePathEntrada);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);            
            
            OutputStream outS = null;
            outS = new FileOutputStream(new File(filePathSalida));
            
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outS.write(bytes, 0, read);
            }
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        
        resultado += "<form action=\"/Practica4/menu.jsp\" method=\"POST\">  ";
        resultado += "<input type=\"submit\" value=\"Volver al menu\">";
        resultado += "</form>";
        return resultado;
    }
      
    
    /**
    * POST method to register a new image
    * @param id
    * @return
    */
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage  (@FormParam("id") String id){
        Connection connection = null;
        String resultado = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            
            
            try{
                query = "DELETE FROM IMAGEN "
                        + "WHERE ID = ?"; 
                statement = connection.prepareStatement(query);                
                statement.setString(1, id);                
                statement.executeUpdate(); 
                resultado += "<h1>Imagen eliminada correctamente</h1>";
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                resultado +=  "<h1>Imagen no se ha podido eliminar</h1>";
                return resultado;
            }
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
                resultado += "<h1>Imagen no se ha podido eliminar</h1>";
                return resultado;
            }
        }
        
        resultado += "<form action=\"/Practica4/menu.jsp\" method=\"POST\">  ";
        resultado += "<input type=\"submit\" value=\"Volver al menu\">";
        resultado += "</form>";
        return resultado;
    }
    /**
 * POST method to register a new image
 * @param title
 * @param description
 * @param keywords
 * @param id
 * @param crea_date
 * @return
 */
 @Path("modify")
 @POST
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_HTML)
 public String modifyImage (@FormParam("title") String title,
                            @FormParam("description") String description,
                            @FormParam("keywords") String keywords,
                            @FormParam("id") String id,
                            @FormParam("crea_date") String crea_date) 
 {    
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>"; 
    Connection connection = null;
       try {           
           PreparedStatement statement;
           String query;

           Class.forName("org.apache.derby.jdbc.ClientDriver");
           connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");  

           try{
               query = "UPDATE IMAGEN "
                   + "SET TITULO = ?, DESCRIPCION = ?, PALABRAS_CLAVE = ?, FECHA_CREACION = ? "
                   + "WHERE id = ?";   
               statement = connection.prepareStatement(query);

               statement.setString(1, title);
               statement.setString(2, description); 
               statement.setString(3, keywords);
               statement.setString(4, crea_date);
               statement.setString(5, id);

               statement.executeUpdate();   
               
               resultat += "<h1> Imatge modificada </h1>";
           }
           catch(Exception e){
               System.err.println(e.getMessage());
               resultat += "<h1> Error </h1>";
               return resultat;
           }
           resultat += "</table>";
           resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
           resultat += "<input type=\"submit\" value=\"Menu\">";              
           resultat += "</form>"; 
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
               resultat = "<h1> Error </h1>";
               return resultat;
           }
       }
       return resultat;
 }
 /**
 * GET method to list images
 * @return
 */
 @Path("list")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String listImages () {  
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>"; 
    resultat += "<h1>Llista Imatges</1><br>";
    Connection connection = null;
    try{
        String query;
        PreparedStatement statement;
        Class.forName("org.apache.derby.jdbc.ClientDriver");  

        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

        //Seleccion de todas las imagenes de la BD
        query = "select * from imagen";
        statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        String autorImg, palabra, idf, titulo, descripcion, fechaf, fechaa, nomf = "null";  
        resultat += "<table style=width:100%>";
        resultat += "<tr>";
        resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Descargar Imagen</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
                        
        
            while(rs.next()){ 
                   //Bucle para listar todas las imagenes y generar TABLA
                    autorImg = rs.getString("AUTOR");
                    palabra = rs.getString("palabras_clave"); 
                    idf = rs.getString("id");
                    titulo = rs.getString("titulo");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    nomf = rs.getString("nombre_fichero");
                   
                    resultat += "<tr><td>" + idf  + "</td><td>" + titulo  + "</td><td>" + descripcion  + "</td><td>" + autorImg  
                           + "</td><td>" + fechaf  + "</td><td>" + fechaa  + "</td>";
                    
                   
                   //Modificar Imagen
                   resultat += "<td><form action=\"./download\" method=\"POST\" enctype=\"multipart/form-data\">";
                   resultat += "<input type=\"submit\" value=\"Descargar Imagen\">";
                   resultat += "<input type=\"hidden\" name=\"fileName\" value =\"" + rs.getString("NOMBRE_FICHERO") + "\">";
                   resultat += "</form></td>"; 

                   //Modificar Imagen
                   resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                   resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                   resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                   resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                   resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                   resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                   resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                   resultat += "</form></td>"; 
                   
                   //Eliminar Imagen
                   resultat += "<td><form action=\"./delete\" method=\"POST\">";
                   resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                   resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                   resultat += "</form></td>";
                   
                   resultat += "</tr>";
            }  
        resultat += "</table>";
        resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
        resultat += "<input type=\"submit\" value=\"Menu\">";              
        resultat += "</form>"; 
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
    finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } 
        catch (Exception e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
    return resultat;
 }
 
/**
* GET method to search images by id
* @param id
* @return
*/
@Path("searchID/{id}")
@POST
@Produces(MediaType.TEXT_HTML)
public String searchByID (@PathParam("id") int id){
    Connection connection = null;
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            String autorImg, titulo, descripcion, fechaf, fechaa = "null";  
            resultat += "<table style=width:100%>";
            resultat += "<tr>";
            resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
            try{
                query = "select * "
                      + "from imagen "
                      + "where id = ?";
                
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);  
                ResultSet rs = statement.executeQuery(); 
                if(rs.next()){
                    autorImg = rs.getString("autor");
                    titulo = rs.getString("titulo");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    
                    resultat += "<tr> "
                       + "<td> "+id+" </td> <td>"+titulo+"</td> <td>"+descripcion+"</td> <td>"+autorImg+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";  
                    //Modificar Imagen
                    resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultat += "</form></td>"; 
                    
                    //Eliminar Imagen
                    resultat += "<td><form action=\"./delete\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultat += "</form></td>";
                   
                    resultat += "</tr>";
                } 
                
                resultat += "</table>";
                resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
                resultat += "<input type=\"submit\" value=\"Menu\">";              
                resultat += "</form>"; 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }            
            
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
        return resultat;
}

/**
* GET method to search images by title
* @param title
* @return
*/
@Path("searchTitle/{title}")
@POST
@Produces(MediaType.TEXT_HTML)
public String searchByTitle (@PathParam("title") String title){
    Connection connection = null;
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            String autorImg, id, descripcion, fechaf, fechaa, titulo = "null";  
            resultat += "<table style=width:100%>";
            resultat += "<tr>";
            resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
            try{
                query = "select * "
                      + "from imagen "
                      + "where titulo LIKE '%"+title+"%'";
                
                statement = connection.prepareStatement(query);   
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                    titulo = rs.getString("titulo");
                    autorImg = rs.getString("autor");
                    id = rs.getString("id");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    
                    resultat += "<tr> "
                       + "<td> "+id+" </td> <td>"+titulo+"</td> <td>"+descripcion+"</td> <td>"+autorImg+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";  
                    //Modificar Imagen
                    resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultat += "</form></td>"; 
                    
                    //Eliminar Imagen
                    resultat += "<td><form action=\"./delete\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultat += "</form></td>";
                   
                    resultat += "</tr>";
                } 
                
                resultat += "</table>";
                resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
                resultat += "<input type=\"submit\" value=\"Menu\">";              
                resultat += "</form>"; 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }            
            
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
        return resultat;
}

/**
* GET method to search images by author
* @param author
* @return
*/
@Path("searchAuthor/{author}")
@POST
@Produces(MediaType.TEXT_HTML)
public String searchByAuthor (@PathParam("author") String author){
    Connection connection = null;
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            String autorImg, id, descripcion, fechaf, fechaa, titulo = "null";  
            resultat += "<table style=width:100%>";
            resultat += "<tr>";
            resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
            try{
                query = "select * "
                      + "from imagen "
                      + "where autor LIKE '%"+author+"%'";
                
                statement = connection.prepareStatement(query);   
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                    titulo = rs.getString("titulo");
                    autorImg = rs.getString("autor");
                    id = rs.getString("id");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    
                    resultat += "<tr> "
                       + "<td> "+id+" </td> <td>"+titulo+"</td> <td>"+descripcion+"</td> <td>"+autorImg+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";
                    
                    //Modificar Imagen
                    resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultat += "</form></td>"; 
                    
                    //Eliminar Imagen
                    resultat += "<td><form action=\"./delete\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultat += "</form></td>";
                   
                    resultat += "</tr>";
                } 
                
                resultat += "</table>";
                resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
                resultat += "<input type=\"submit\" value=\"Menu\">";              
                resultat += "</form>"; 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }            
            
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
    return resultat;    
}

/**
* GET method to search images by creation date
* @param creaDate
* @return
*/
@Path("searchCreationDate/{date}")
@POST
@Produces(MediaType.TEXT_HTML)
public String searchByCreationDate (@PathParam("date") String date){
    Connection connection = null;
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            String autorImg, id, descripcion, fechaf, fechaa, titulo = "null";  
            resultat += "<table style=width:100%>";
            resultat += "<tr>";
            resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
            try{
                query = "select * "
                      + "from imagen "
                      + "where fecha_creacion LIKE '%"+date+"%'";
                
                statement = connection.prepareStatement(query);   
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                    titulo = rs.getString("titulo");
                    autorImg = rs.getString("autor");
                    id = rs.getString("id");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    
                    resultat += "<tr> "
                       + "<td> "+id+" </td> <td>"+titulo+"</td> <td>"+descripcion+"</td> <td>"+autorImg+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";  
                    
                    //Modificar Imagen
                    resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultat += "</form></td>"; 
                    
                    //Eliminar Imagen
                    resultat += "<td><form action=\"./delete\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultat += "</form></td>";
                   
                    resultat += "</tr>";
                } 
                
                resultat += "</table>";
                resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
                resultat += "<input type=\"submit\" value=\"Menu\">";              
                resultat += "</form>"; 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }            
            
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
    return resultat;   
}

/**
* GET method to search images by keyword
* @param keywords
* @return
*/
@Path("searchKeywords/{keywords}")
@POST
@Produces(MediaType.TEXT_HTML)
public String searchByKeywords (@PathParam("keywords") String keywords){
    Connection connection = null;
    String resultat = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            String autorImg, id, descripcion, fechaf, fechaa, titulo = "null";  
            resultat += "<table style=width:100%>";
            resultat += "<tr>";
            resultat += "<th>Id</th><th>Titulo</th><th>Descripcion</th><th>Autor</th><th>Fecha Creacion</th><th>Fecha Alta</th><th>Modificar Imagen</th><th>Eliminar Imagen</th></tr>";
            try{
                query = "select * "
                      + "from imagen "
                      + "where palabras_clave LIKE '%"+keywords+"%'";
                
                statement = connection.prepareStatement(query);   
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                    titulo = rs.getString("titulo");
                    autorImg = rs.getString("autor");
                    id = rs.getString("id");
                    descripcion = rs.getString("descripcion");
                    fechaf = rs.getString("fecha_creacion");
                    fechaa = rs.getString("fecha_alta");
                    
                    resultat += "<tr> "
                       + "<td> "+id+" </td> <td>"+titulo+"</td> <td>"+descripcion+"</td> <td>"+autorImg+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";  
                    
                    //Modificar Imagen
                    resultat += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultat += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultat += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultat += "</form></td>"; 

                    //Eliminar Imagen
                    resultat += "<td><form action=\"./delete\" method=\"POST\">";
                    resultat += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultat += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultat += "</form></td>";
                   
                    resultat += "</tr>";
                    
                } 
                
                resultat += "</table>";
                resultat += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
                resultat += "<input type=\"submit\" value=\"Menu\">";              
                resultat += "</form>"; 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }            
            
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
    return resultat; 
}

/**
    * POST method to search image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
@Path("searchCombo")
@POST
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public String searchCombo (@FormParam("title") String title,
                            @FormParam("author") String author,
                            @FormParam("description") String description,
                            @FormParam("keywords") String keywords,
                            @FormParam("crea_date") String crea_date){
    
    Connection connection = null;        
    String resultado = "<head><style> body {background-color: lightblue; text-align: center; }</style></head>";

    int bool_autor = 0;
    int bool_descrip = 0;
    int bool_titulo = 0;
    int bool_fecha = 0;
    int bool_pal = 0;

    String vpalclavebusqueda [] = keywords.split(";");

    try {           
        PreparedStatement statement,statement2;
        String query,query2;

        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 

        try{
            query = "SELECT * FROM imagen";
            query2 = "select count(id) from imagen ";

            //Comprobacion -> campo de datos vacio [0] o no [1]
            if (author != null && !author.isEmpty())
                bool_autor = 1;
            if (description != null && !description.isEmpty())
                bool_descrip = 1;
            if (title != null && !title.isEmpty())
                bool_titulo = 1;
            if (crea_date != null && !crea_date.isEmpty())
                bool_fecha = 1;
            if (keywords != null && !keywords.isEmpty())
                bool_pal = 1;

            int num = -1;
            int nume = 0; //esta variable es para saber si toca where o and
            if (bool_autor == 1) {
                if (nume == 0) query += " WHERE";
                else query += " and";
                nume ++;
                query += " autor LIKE '%"+author+"%'";
            }
            if (bool_descrip == 1) {
                if (nume == 0) query += " WHERE";
                else query += " and";
                nume ++;
                query += " descripcion LIKE '%"+description+"%'";
            }
            if (bool_fecha == 1) {
                 if (nume == 0) query += " WHERE";
                else query += " and";
                nume ++;
                query += " fecha_creacion LIKE '%"+crea_date+"%'";
            }
            if (bool_titulo == 1) {
                 if (nume == 0) query += " WHERE";
                else query += " and";
                nume ++;
                query += " titulo LIKE '%"+title+"%'";
            }
            if (bool_pal == 1) {
                for (int v = 0; v< vpalclavebusqueda.length ;v++) {
                    if (nume == 0) query += " WHERE";
                    else query += " and";
                    nume ++;
                    query += " palabras_clave LIKE '%"+vpalclavebusqueda[v]+"%'";
                }
                
            }
            statement = connection.prepareStatement(query);
            statement2 = connection.prepareStatement(query2);

            ResultSet rs = statement.executeQuery(); 
            ResultSet rs2 = statement2.executeQuery(); 
            
            resultado += "<h1> Imagenes encontradas </h1>"; 
                    resultado += "<table style=\"width:100%\"> "
                            + "<tr>"
                            + "<th>Id</th> <th>Titulo</th> <th>Descripcion</th> <th>Autor</th> <th>Fecha Creación</th> <th>Fecha_Alta</th> <th>Modificar Foto</th> <th>Eliminar Foto</th>"
                            + "</tr> ";
                    
            if (rs2.next()) {
                //Numero de imagenes en la BD
                num = rs2.getInt(1);
                if(nume == 0)
                    num = 0; //Si no se busca ningun campo -> no sale ninguna imagen
            }
            if (num > 0){
                while (rs.next()) {
                    int idf = rs.getInt("id");
                    String titulof = rs.getString("titulo");
                    String autorf = rs.getString("autor");
                    String descirpcionf = rs.getString("descripcion");
                    String fechaf = rs.getString("fecha_creacion");
                    String fechaa = rs.getString("fecha_alta");                  
                    
                    resultado += "<tr> "
                       + "<td> "+idf+" </td> <td>"+titulof+"</td> <td>"+descirpcionf+"</td> <td>"+autorf+"</td> <td>"+fechaf+"</td> <td>"+fechaa+"</td>";  
                    
                    //Modificar Imagen
                    resultado += "<td><form action=\"/Practica4/modificarImagen.jsp\" method=\"POST\">";
                    resultado += "<input type=\"submit\" value=\"Modificar Imagen\">";
                    resultado += "<input type=\"hidden\" name=\"titulo\" value =\"" + rs.getString("TITULO") + "\"> ";
                    resultado += "<input type=\"hidden\" name=\"descripcion\" value =\"" + rs.getString("DESCRIPCION") + "\">";
                    resultado += "<input type=\"hidden\" name=\"palabras_clave\" value =\"" + rs.getString("PALABRAS_CLAVE") + "\">";
                    resultado += "<input type=\"hidden\" name=\"fecha_creacion\" value =\"" + rs.getString("FECHA_CREACION") + "\">";
                    resultado += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("ID") + "\">";
                    resultado += "</form></td>"; 
                    
                    resultado += "<td><form action=\"./delete\" method=\"POST\">";
                    resultado += "<input type=\"submit\" value=\"Eliminar Imagen\">";
                    resultado += "<input type=\"hidden\" name=\"id\" value =\"" + rs.getString("id")+ "\"> ";
                    resultado += "</form></td>";
                   
                    resultado += "</tr>";
                }
            }
            
            resultado += "</table>";
            resultado += "<form action=\"/Practica4/menu.jsp\" method=\"POST\" >";  
            resultado += "<input type=\"submit\" value=\"Menu\">";              
            resultado += "</form>"; 
        }    
        catch(Exception e){
            System.err.println(e.getMessage());
            resultado = "<h1> Error </h1>";
        }            

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
            resultado = "<h1> Error </h1>";
        }
    }
    return resultado;
}     
        
}
