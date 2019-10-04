<%-- 
    Document   : buscarImagen
    Created on : 17-sep-2019, 15:46:45
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
        <h1>Buscar Imagen</h1>
        <form action="buscarImagen">
            <h3>Inserta los campos de busqueda</h3>
            <input type="text" name="campos_busqueda" value="">
            <input type="submit" value="Enviar">
        </form>
        <br>
        <form action="menu.jsp" method="POST" >        	
            <input type="submit" value="Menu">
    	</form>
    </body>
</html>