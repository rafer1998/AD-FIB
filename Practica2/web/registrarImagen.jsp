<%--
	Document   : registrarImagen
	Created on : 17-sep-2019, 15:44:12
	Author 	: ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%
Random rand = new Random();
int n = rand.nextInt(1000) + 1;
%>
<!DOCTYPE html>
<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>registImatge</title>
	</head>
	<body>
    	<h1><i>Registrar Imagen</i></h1>
   	 
    	<form action="registrarImagen" method="POST">
        	<p>Titulo: <input type="text" name="titulo" size="40"></p>
        	<p>Descripcion: <input type="text" name="descripcion" size="40"></p>
        	<p>Palabras clave: <input type="text" name="palabras_clave" size="40"></p>
        	<p>Autor: <input type="text" name="autor" size="40"></p>
        	<label for="date">Fecha creación:</label>
        	<input type="date" name="fecha" value="2010-12-16;">
        	<label for="avatar">Choose a profile picture:</label>
        	<input type="file" id="fichero" name="fichero" accept="image/png, image/jpeg">
        	<p>
            	<input type="submit" value="Enviar">
            	<input type="reset" value="Borrar">
        	</p>
    	</form>
   	 
	</body>
</html>
