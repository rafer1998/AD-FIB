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
            String autor = "NULL";
            Cookie[] cookies = request.getCookies();
            if(cookies !=null){
                for(Cookie cookie : cookies){
                      if(cookie.getName().equals("autor")) autor = cookie.getValue();
                }
            }
              
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
                
                String autorImg = "null";
                %>                
                <table>                    
                <%
                    while(rs.next()){                        
                           autorImg = rs.getString("AUTOR");
                           System.out.println("autorImg: " + autorImg);
                           System.out.println("usuario " + autor);
                %>  
                          <tr>
                            <td><%=rs.getString("NOMBRE_FICHERO")%></td>   
                            <td><form action="mostrarImagen"> 
                                    <input type="submit" value="Previsualizar Imagen"> 
                                    <input type="hidden" name="nombre_fichero" value ="<%=rs.getString("NOMBRE_FICHERO")%>">
                            </form></td>
                            <%
                            if(autorImg.equals(autor)){
                            %> 
                                <td><form action="modificarImagen.jsp" method="POST">  
                                        <input type="submit" value="Modificar Imagen"> 
                                        <input type="hidden" name="titulo" value ="<%=rs.getString("TITULO")%>">  
                                        <input type="hidden" name="descripcion" value ="<%=rs.getString("DESCRIPCION")%>">
                                        <input type="hidden" name="palabras_clave" value ="<%=rs.getString("PALABRAS_CLAVE")%>">
                                        <input type="hidden" name="fecha_creacion" value ="<%=rs.getString("FECHA_CREACION")%>">
                                        
                                        
                                </form></td>
                            <%   
                            }
                            %> 
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
