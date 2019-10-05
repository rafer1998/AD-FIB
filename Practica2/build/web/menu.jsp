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
    </head>
    <body>
        <h1>Menu</h1>        
        <a href="registrarImagen.jsp">Registrar Imagen</a><br>  
        <a href="listImg.jsp">Listar Imagenes</a><br>
        <a href="buscarImagen.jsp">Buscar Imagen</a><br><br>  
        
        <form action="logout" method="POST">        	
            	<input type="submit" value="Logout">
    	</form>
    </body>
</html>
