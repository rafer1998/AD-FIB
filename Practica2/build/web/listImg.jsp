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
          <%            
            Connection connection = null;
            try{
                String query;
                PreparedStatement statement;
                Class.forName("org.apache.derby.jdbc.ClientDriver");  

                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

                query = "select * from imagen";
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                
                String autorImg = "null";
                %>                
                <table>                    
                <%
                    while(rs.next()){ 
                           //Bucle para listar todas las imagenes que se encuentran en la base de datos
                           autorImg = rs.getString("AUTOR");
                %>  
                          <tr>
                            <td><%=rs.getString("TITULO")%></td>   
                            <td><form action="mostrarImagen"> 
                                    <input type="submit" value="Previsualizar Imagen"> 
                                    <input type="hidden" name="nombre_fichero" value ="<%=rs.getString("NOMBRE_FICHERO")%>">
                            </form></td>
                            <%
                            if(autorImg.equals(autor)){
                            //Si la imagen pertenece al usuario actual -> puede modificar y borrar
                            %> 
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
