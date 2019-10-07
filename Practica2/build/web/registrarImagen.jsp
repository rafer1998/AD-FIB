<%--
	Document   : registrarImagen
	Created on : 17-sep-2019, 15:44:12
	Author 	: ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>registImatge</title>
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
            
            
    	<h1><i>Registrar Imagen</i></h1>
   	 
        <form action="registrarImagen" method="POST" enctype="multipart/form-data">
        	<p>Titulo: <input type="text" name="titulo" size="40" required></p>
        	<p>Descripcion: <input type="text" name="descripcion" size="40" required></p>
        	<p>Palabras clave: <input type="text" name="palabras_clave" size="40" placeholder="Palabras separadas con ';'" required></p>        	
                <p><label for="date">Fecha creación:</label>
                    <input type="date" name="fecha" value="2010-12-16; " required></p>
                <p><label for="avatar">Seleccionar imagen:</label>
                    <input type="file" id="fichero" name="fichero" accept="image/png, image/jpeg" required></p>                
        	<p>
            	<input type="submit" value="Enviar">
            	<input type="reset" value="Borrar">
        	</p>
    	</form>
        
        <form action="menu.jsp" method="POST" >        	
            	<input type="submit" value="Menu">
    	</form>
   	 
	</body>
</html>
