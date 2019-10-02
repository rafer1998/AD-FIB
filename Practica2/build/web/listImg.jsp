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
        <title>JSP Page</title>
        <script type="text/javascript">                
            function picture(nombreFichero){ 
                var pic = "http://localhost:8080/Practica2/imagenes/";
                var pic2 = nombreFichero;
                var pic3 = pic.concat(pic2)
                document.getElementById(pic3).src = pic3.replace('90x90', '225x225');
                document.getElementById(pic3).style.display='block';
            } 
       </script> 
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
                %>                
                <table>                    
                <%
                    while(rs.next()){
                %>  
                          <tr>
                            <td><%=rs.getString("NOMBRE_FICHERO")%></td>
                            <td><img style="display:none;" id="<%=rs.getString("NOMBRE_FICHERO")%>" src="http://localhost:8080/Practica2/imagenes/<%=rs.getString("NOMBRE_FICHERO")%>" /></td>
                            <td><button onclick="picture(<%=rs.getString("NOMBRE_FICHERO")%>)">Previsualizar</button></td>
                            
                            <td><form action="modificarImagen.jsp"> <input type="submit" value="Previsualizar Imagen"> </form></td>
                            <td><form action="modificarImagen.jsp"> <input type="submit" value="Modificar Imagen"> </form></td>
                          </tr>
                <%  
                        //<img src="http://localhost:8080/Practica2/imagenes/icono.png">  
                    }
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
