/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.imagenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ruben
 */
@WebService(serviceName = "servicioImagenes", wsdlLocation="WEB-INF/wsdl/ServicioImagenes.wsdl")
public class servicioImagenes {
    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage(@WebParam(name = "Image") Image Image) {
        Connection connection = null;
        try {           
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
                statement.setString(2, Image.getTitulo()); 
                statement.setString(3, Image.getDescripcion());
                statement.setString(4, Image.getKeywords());
                statement.setString(5, Image.getAutor());
                statement.setString(6, Image.getCreaDate());
                statement.setString(7, altaDate);
                statement.setString(8, Image.getFichero());
                
                statement.executeUpdate();                
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                return 0;
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
                return 0;
            }
        }
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "Image") Image Image) {
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
                
                statement.setString(1, Image.getTitulo());
                statement.setString(2, Image.getDescripcion()); 
                statement.setString(3, Image.getKeywords());
                statement.setString(4, Image.getCreaDate());
                statement.setInt(5, Image.getID());
            
                statement.executeUpdate();                 
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                return 0;
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
                return 0;
            }
        }
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImages")
    public List<Image> ListImages() {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select *"
                      + "from imagen";
                
                statement = connection.prepareStatement(query); 
                ResultSet rs = statement.executeQuery(); 
                
                while(rs.next()){
                    //Conjunto de imagenes que hay en la BD
                    Image res = new Image();
                    
                    res.setID(rs.getInt("id"));
                    res.setTitulo(rs.getString("titulo"));
                    res.setAutor(rs.getString("autor"));
                    res.setDescripcion(rs.getString("descripcion"));
                    res.setCreaDate(rs.getString("fecha_creacion"));
                    res.setKeywords(rs.getString("palabras_clave"));
                    
                    resultado.add(res);
                }
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
       Connection connection = null;
       Image resultado = new Image();
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select * "
                      + "from imagen "
                      + "where id = ?";
                
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);  
                ResultSet rs = statement.executeQuery(); 
                System.out.println("Error1");
                if(rs.next()){
                    System.out.println("Error2");
                    //Ha encontrado una imagen con ID = id                    
                    resultado.setID(id);
                    resultado.setAutor(rs.getString("autor"));
                    resultado.setTitulo(rs.getString("titulo"));
                    resultado.setDescripcion(rs.getString("descripcion"));
                    resultado.setCreaDate(rs.getString("fecha_creacion"));
                    resultado.setKeywords(rs.getString("palabras_clave"));
                } 
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List<Image> SearchbyTitle(@WebParam(name = "title") String title) {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select * "
                      + "from imagen "
                      + "where titulo = ? ";
                
                statement = connection.prepareStatement(query);
                statement.setString(1, title);  
                ResultSet rs = statement.executeQuery(); 
                
                while(rs.next()){
                    //Conjunto de imagenes que cumplen la condicion
                    Image res = new Image();
                    
                    res.setID(rs.getInt("id"));
                    res.setTitulo(title);
                    res.setAutor(rs.getString("autor"));
                    res.setDescripcion(rs.getString("descripcion"));
                    res.setCreaDate(rs.getString("fecha_creacion"));
                    res.setKeywords(rs.getString("palabras_clave"));
                    
                    resultado.add(res);
                }
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreatDate")
    public List<Image> SearchbyCreatDate(@WebParam(name = "creaDate") String creaDate) {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select * "
                      + "from imagen "
                      + "where fecha_creacion = ? ";
                
                statement = connection.prepareStatement(query);
                statement.setString(1, creaDate);  
                ResultSet rs = statement.executeQuery(); 
                
                while(rs.next()){
                    //Conjunto de imagenes que cumplen la condicion
                    Image res = new Image();
                    
                    res.setID(rs.getInt("id"));
                    res.setTitulo(rs.getString("titulo"));
                    res.setAutor(rs.getString("autor"));
                    res.setDescripcion(rs.getString("descripcion"));
                    res.setCreaDate(creaDate);
                    res.setKeywords(rs.getString("palabras_clave"));
                    
                    resultado.add(res);
                }
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List<Image> SearchbyAuthor(@WebParam(name = "autor") String autor) {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select * "
                      + "from imagen "
                      + "where autor = ? ";
                
                statement = connection.prepareStatement(query);
                statement.setString(1, autor);  
                ResultSet rs = statement.executeQuery(); 
                
                while(rs.next()){
                    //Conjunto de imagenes que cumplen la condicion
                    Image res = new Image();
                    
                    res.setID(rs.getInt("id"));
                    res.setTitulo(rs.getString("titulo"));
                    res.setAutor(autor);
                    res.setDescripcion(rs.getString("descripcion"));
                    res.setCreaDate(rs.getString("fecha_creacion"));
                    res.setKeywords(rs.getString("palabras_clave"));
                    
                    resultado.add(res);
                }
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List<Image> SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "select * "
                      + "from imagen "
                      + "where palabras_clave = ? ";
                
                statement = connection.prepareStatement(query);
                String palabras_clave = '%' + keywords + '%';
                statement.setString(1, palabras_clave);  
                ResultSet rs = statement.executeQuery(); 
                
                while(rs.next()){
                    //Conjunto de imagenes que cumplen la condicion
                    Image res = new Image();
                    
                    res.setID(rs.getInt("id"));
                    res.setTitulo(rs.getString("titulo"));
                    res.setAutor(rs.getString(("autor")));
                    res.setDescripcion(rs.getString("descripcion"));
                    res.setCreaDate(rs.getString("fecha_creacion"));
                    res.setKeywords(rs.getString("palabras_clave"));
                    
                    resultado.add(res);
                }
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
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "img") Image img) {
        Connection connection = null;
        try {           
            PreparedStatement statement;
            String query;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            //id
            int id = img.getID();   
            
            try{
                query = "DELETE FROM IMAGEN "
                        + "WHERE ID = ?"; 
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);         
                statement.executeUpdate();                
            }
            catch(Exception e){
                System.err.println(e.getMessage());
                return 0;
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
                return 0;
            }
        }
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchComb")
    public List<Image> SearchComb(@WebParam(name = "autorbus") String autorbus, @WebParam(name = "desbus") String desbus, @WebParam(name = "titulobus") String titulobus, @WebParam(name = "fechabus") String fechabus, @WebParam(name = "palclavebusqueda") String palclavebusqueda) {
        Connection connection = null;        
        ArrayList<Image> resultado = new ArrayList<Image>();
        
        int bool_autor = 0;
        int bool_descrip = 0;
        int bool_titulo = 0;
        int bool_fecha = 0;
        int bool_pal = 0;
        
        String vpalclavebusqueda [] = palclavebusqueda.split(";");
        
        try {           
            PreparedStatement statement,statement2;
            String query,query2;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2"); 
            
            try{
                query = "SELECT * FROM imagen";
                query2 = "select count(id) from imagen ";
                
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
                int nume = 0; //esta variable es para saber si toca where o and
                if (bool_autor == 1) {
                    if (nume == 0) query += " WHERE";
                    else query += " and";
                    nume ++;
                    query += " autor LIKE '%"+autorbus+"%'";
                }
                if (bool_descrip == 1) {
                    if (nume == 0) query += " WHERE";
                    else query += " and";
                    nume ++;
                    query += " descripcion LIKE '%"+desbus+"%'";
                }
                if (bool_fecha == 1) {
                     if (nume == 0) query += " WHERE";
                    else query += " and";
                    nume ++;
                    query += " fecha_creacion LIKE '%"+fechabus+"%'";
                }
                if (bool_titulo == 1) {
                     if (nume == 0) query += " WHERE";
                    else query += " and";
                    nume ++;
                    query += " titulo LIKE '%"+titulobus+"%'";
                }
                if (bool_pal == 1) {
                    for (int v = 0; v< vpalclavebusqueda.length ;v++) {
                        if (nume == 0) query += " WHERE";
                        else query += " and";
                        nume ++;
                        query += " palabras_clave LIKE '%"+vpalclavebusqueda[v]+"%'";
                    }
                    System.out.println("Query -> " + query);
                }
                
                statement = connection.prepareStatement(query);
                statement2 = connection.prepareStatement(query2);

                ResultSet rs = statement.executeQuery(); 
                ResultSet rs2 = statement2.executeQuery(); 
                
                if (rs2.next()) {
                    //Numero de imagenes en la BD
                    num = rs2.getInt(1);
                    if(nume == 0)
                        num = 0; //Si no se busca ningun campo -> no sale ninguna imagen
                }
                if (num > 0){
                    while (rs.next()) {
                        Image res = new Image();
                    
                        res.setID(rs.getInt("id"));
                        res.setTitulo(rs.getString("titulo"));
                        res.setAutor(rs.getString(("autor")));
                        res.setDescripcion(rs.getString("descripcion"));
                        res.setCreaDate(rs.getString("fecha_creacion"));
                        res.setKeywords(rs.getString("palabras_clave"));
                        res.setFichero(rs.getString("nombre_fichero"));

                        resultado.add(res);                    
                    }
                }
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
        return resultado;
    }
}