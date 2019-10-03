<%-- 
    Document   : listImg
    Created on : 17-sep-2019, 15:46:24
    Author     : ruben.barcelo
--%>

<%@page import="java.sql.*"%>
<%@page import="javax.servlet.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>listImg</title>
    </head>
    <body>
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
                
                //Sesiones
                HttpSession misession= request.getSession(true);
               
                %>                
                <table>                    
                <%
                    while(rs.next()){
                %>  
                          <tr>
                            <td><%=rs.getString("NOMBRE_FICHERO")%></td>   
                            <td><form action="mostrarImagen"> 
                                    <input type="submit" value="Previsualizar Imagen"> 
                                    <input type="hidden" name="nombre_fichero" value ="<%=rs.getString("NOMBRE_FICHERO")%>">
                            </form></td>
                            <td><form action="modificarImagen.jsp"> <input type="submit" value="Modificar Imagen"> </form></td>
                          </tr>
                <%  
                    }
                        System.out.println("path fichero: " + misession.getAttribute("url"+1));
                        System.out.println("path fichero: " + misession.getAttribute("url"+2));
                %>
                </table>
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
