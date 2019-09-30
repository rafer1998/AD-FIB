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
    </head>
    <body>
        <h1>Imagenes</h1>
     
          <%
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            //1c. Construct our SQL statement
            String query = "select * from usuarios";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
       
            System.out.println("Error1");
            System.out.println("ID USUARIO:      " + rs.getString("id_usuario"));
            if(rs.next()) {     
                System.out.println("Error2");
                rs.beforeFirst();  
                while(rs.next())   
                    System.out.println("Error3");
                {
                    System.out.println(rs.getString("id_usuario"));
                }  
            }

         %>     
        
    </body>
</html>
