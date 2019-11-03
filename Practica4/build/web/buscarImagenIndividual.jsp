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
        //String autor = "NULL";   
        //HttpSession misession= (HttpSession) request.getSession();
        //autor = (String) misession.getAttribute("autor");
        //if(autor == null)
               //Si el usuario no tiene sesion ->  redirect a login//
        //       response.sendRedirect("login.jsp");
        %>
        
        <h1>Buscar Imagen</h1>
        <form onchange="afegirPath()" id="formulari" class="form-signin" action="./webresources/generic/searchID/" method="GET">
            <h3>Inserta los campos de busqueda</h3>
            
            <h4>Id</h4>
            <input type="hidden" name="id" value="2">                  
            <button type="submit"> Enviar </button>
        </form>
        <br>
        
        
        <form action="menu.jsp" method="POST" >        	
            <input type="submit" value="Menu">
    	</form>
        
        
        <form onchange="afegirPath()" id="formulari" class="form-signin" action="./webresources/generic/searchByID/" method="GET">
            <select name="accio" id="accio">
                <option value="ID">Id</option>
                <option value="Title">Titol</option>
                <option value="Author">Autor</option>
                <option value="CreationDate">Data de creació</option>
                <option value="Keywords">Tags</option>
            </select> 
            <input type="number" id="text" name="text" required autofocus>            

            <button class="btn btn-lg btn-primary btn-block" type="submit">Buscar</button>
        </form> 
    </body>
</html>