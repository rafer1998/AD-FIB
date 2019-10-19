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
        <title>buscarImagen</title>
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
        <h1>Buscar Imagen</h1>
        <form action="buscarImagen">
            <h3>Inserta los campos de busqueda</h3>
            
            <h4>Palabras Clave</h4>
            <input type="text" name="palabras_clave" value=""> 
            
            <h4>Autor</h4>            
            <input type="text" name="autor" value="">
            
            <h4>Descripción</h4>
            <input type="text" name="descripcion" value="">
            
            <h4>Titulo</h4>
            <input type="text" name="titulo" value="">
            
            <h4>Fecha de Creación</h4>
            <input type="date" name="fecha_creacion" value="2010-12-16; ">
            
            <br><br>
            
            <input type="submit" value="Enviar">
        </form>
        <br>
        <form action="menu.jsp" method="POST" >        	
            <input type="submit" value="Menu">
    	</form>
    </body>
</html>