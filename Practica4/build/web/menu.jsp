<%-- 
    Document   : menu
    Created on : 17-sep-2019, 15:36:41
    Author     : ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        /*
        String autor = "NULL";   
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        if(autor == null)
               //Si el usuario no tiene sesion ->  redirect a login//
               response.sendRedirect("login.jsp");
        */
        %>
        
        <h1>Menu</h1>        
        <a href="registrarImagen.jsp">Registrar Imagen</a><br>  
        <a href="./webresources/generic/list">Listar Imagenes</a><br>
        <a href="buscarImagenIndividual.jsp">Buscar Imagen</a><br>
        <a href="buscarImagen.jsp">Buscar Imagen Combinada</a><br><br>  
        
        <form action="logout" method="POST">        	
            	<input type="submit" value="Logout">
    	</form>
    </body>
</html>
