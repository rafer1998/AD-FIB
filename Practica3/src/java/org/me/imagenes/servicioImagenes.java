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
import java.util.Date;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ruben
 */
@WebService(serviceName = "servicioImagenes")
public class servicioImagenes {

    /*
    //Esta clase solo la he generado para poder copiar el codigo de conectar a la BD facilmente
    public void conectar(){   
        Connection connection = null;
        try {
            
            PreparedStatement statement;
            String query;
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");               
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
    */
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
                statement.setString(8, Image.getTitulo());
                
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
    public int ModifyImage(@WebParam(name = "image") String image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImages")
    public List ListImages() {
        //TODO write your implementation code here:
        return null;
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
                query = "select *"
                      + "from imagen"
                      + "where id ?";
                
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);  
                ResultSet rs = statement.executeQuery(); 
                
                if(rs.next()){
                    //Ha encontrado una imagen con ID = id                    
                    resultado.setID(id);
                    resultado.setAutor(rs.getString("autor"));
                    resultado.setDescripcion(rs.getString("descripcion"));
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
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreatDate")
    public List SearchbyCreatDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "autor") String autor) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public String SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        //TODO write your implementation code here:
        return null;
    }
}
