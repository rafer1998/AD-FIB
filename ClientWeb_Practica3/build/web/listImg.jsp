<%-- 
    Document   : listImg
    Created on : 17-sep-2019, 15:46:24
    Author     : ruben.barcelo
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>listImg</title>
        <style>
            body {
              background-color: lightblue;
              text-align: center;
            }            
        </style>
    </head>
    <body>
        <%
        //Comprobacion usuario con sesion iniciada//
        String autor = "NULL";   
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        if(autor == null)
               //Si el usuario no tiene sesion ->  redirect a login//
               response.sendRedirect("login.jsp");
        %>        
        
        <h1>Lista de Imagenes</h1>     
        <table style=width:100%> 
            <tr>
                <th>Id</th> 
                <th>Titulo</th> 
                <th>Descripcion</th> 
                <th>Autor</th> 
                <th>Fecha Creación</th> 
                <th>Fecha_Alta</th> 
                <th>Nombre Fichero</th> 
                <th>Descargar Foto</th> 
                <th>Modificar Foto</th> 
                <th>Eliminar Foto</th>
            </tr> 
          <%            
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
                %>  
                          
                         <tr>
                             <td><%=idf%></td> 
                             <td><%=titulo%></td> 
                             <td><%=descripcion%></td> 
                             <td><%=autorImg%></td> 
                             <td><%=fechaf%></td> 
                             <td><%=fechaa%></td> 
                             <td><%=nomf%></td>                            
             
                            
                            <%
                            if(autorImg.equals(autor)){
                            //Si la imagen pertenece al usuario actual -> puede modificar y borrar
                            %> 
                                <td><form action="descargarImagen" method="POST">  
                                        <input type="submit" value="Descargar Imagen"> 
                                        <input type="hidden" name="nombreFichero" value ="<%=rs.getString("NOMBRE_FICHERO")%>">   
                                </form></td>
                                
                                <td><form action="modificarImagen.jsp" method="POST">  
                                        <input type="submit" value="Modificar Imagen"> 
                                        <input type="hidden" name="titulo" value ="<%=rs.getString("TITULO")%>">  
                                        <input type="hidden" name="descripcion" value ="<%=rs.getString("DESCRIPCION")%>">
                                        <input type="hidden" name="palabras_clave" value ="<%=rs.getString("PALABRAS_CLAVE")%>">
                                        <input type="hidden" name="fecha_creacion" value ="<%=rs.getString("FECHA_CREACION")%>">
                                        <input type="hidden" name="id" value ="<%=rs.getString("ID")%>">
                                </form></td>
                                
                                <td><form action="eliminarImagen" method="POST">  
                                        <input type="submit" value="Eliminar Imagen"> 
                                        <input type="hidden" name="id" value ="<%=rs.getString("ID")%>">  
                                        <input type="hidden" name="nombre_fichero" value ="<%=rs.getString("NOMBRE_FICHERO")%>">
                                </form></td>
                            <%   
                            }
                            %> 
                          </tr>
                <%  
                    }
                %>
                </table>
                
                <form action="menu.jsp" method="POST" >        	
                    <input type="submit" value="Menu">
                </form>
                <%
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }
         %>  
    </body>
</html>
