<%-- 
    Document   : modificarImagen
    Created on : 17-sep-2019, 15:44:40
    Author     : ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Imagen</title>
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
        
        <h1>Modifica Imagen</h1>
        
        <%    
        //Obtencion de los parametros de la imagen -> para poder enviarlo al .java
        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String palabras_clave = request.getParameter("palabras_clave");
        String fecha_creacion = request.getParameter("fecha_creacion");   
        %>
        
        <h1><%=titulo%></h1>
        <form action="modificarImagen" enctype="multipart/form-data">
                <p>Titulo: <input type="text" name="titulo" size="40" value="<%=titulo%>" required></p>
        	<p>Descripcion: <input type="text" name="descripcion" size="40" value="<%=descripcion%>" required></p>
        	<p>Palabras clave: <input type="text" name="palabras_clave" size="40" value="<%=palabras_clave%>" placeholder="Palabras separadas con ';'" required></p>        	
                <p><label for="date">Fecha creación:</label>
                    <input type="date" name="fecha" value="<%=fecha_creacion%>" required></p>  
                
                <input type="hidden" name="id" value ="<%=id%>"> 
                
            	<input type="submit" value="Enviar">
            	<input type="reset" value="Borrar">
        	</p>
    	</form>
                
        <form action="menu.jsp" method="POST" >        	
        <input type="submit" value="Menu">
    	</form>
    </body>
</html>
