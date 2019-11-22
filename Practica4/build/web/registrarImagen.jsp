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
        String autor;         
        HttpSession misession= (HttpSession) request.getSession();
        autor = (String) misession.getAttribute("autor");
        if(autor == null)
               //Si el usuario no tiene sesion ->  redirect a login//
               response.sendRedirect("login.jsp");
        
        %>   
            
            
    	<h1><i>Registrar Imagen</i></h1>
   	 
        <form action="./webresources/generic/register" method="POST" enctype="multipart/form-data">
        	<p>Titulo: <input type="text" name="title" required></p>
        	<p>Descripcion: <input type="text" name="description" required></p>
        	<p>Palabras clave: <input type="text" name="keywords" placeholder="Palabras separadas con ';'" required></p>        	
                <p><label for="date">Fecha creación:</label>
                    <input type="date" name="creation" value="2010-12-16; " required></p>    
                
                <p>Fichero: <input type ="file" name = "file" size = "45" /></p>
        	
                <p><input type="hidden" name="author" value ="<%=autor%>"></p>
                
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
