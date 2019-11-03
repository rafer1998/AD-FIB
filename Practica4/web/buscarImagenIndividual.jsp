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
        
        <form id="formulari" name="formulari" action="./webresources/generic/searchID/" method="POST">        
            <h4>Id</h4>
            <input type="text" name="id" id="id" value="">                  
            <input onclick="afegirPathID(document.getElementById('id').value)" type="submit" value="Enviar">
        </form>
        <br>
        
        <form id="formulari2" name="formulari2" action="./webresources/generic/searchTitle/" method="POST">        
            <h4>Titulo</h4>
            <input type="text" name="title" id="title" value="">                  
            <input onclick="afegirPathTitle(document.getElementById('title').value)" type="submit" value="Enviar">
        </form>
        <br>
        
        <form id="formulari3" name="formulari3" action="./webresources/generic/searchAuthor/" method="POST">        
            <h4>Autor</h4>
            <input type="text" name="autor" id="autor" value="">                  
            <input onclick="afegirPathAuthor(document.getElementById('autor').value)" type="submit" value="Enviar">
        </form>
        <br>
        
        <form id="formulari4" name="formulari4" action="./webresources/generic/searchCreationDate/" method="POST">        
            <h4>Fecha Creacion</h4>
            <input type="text" name="date" id="date" value="">                  
            <input onclick="afegirPathCreaDate(document.getElementById('date').value)" type="submit" value="Enviar">
        </form>
        <br>
        
         <form id="formulari5" name="formulari5" action="./webresources/generic/searchByKeywords/" method="POST">        
            <h4>Palabra clave</h4>
            <input type="text" name="keyword" id="keyword" value="">                  
            <input onclick="afegirPathKeywords(document.getElementById('keyword').value)" type="submit" value="Enviar">
        </form>
        <br><br>
        
        
        <form action="menu.jsp" method="POST" >        	
            <input type="submit" value="Menu">
    	</form> 
        
        <script>
        function afegirPathID(data) {
            document.getElementById("formulari").action = "./webresources/generic/searchID/" + data;
        }
        function afegirPathTitle(data) {
            document.getElementById("formulari2").action = "./webresources/generic/searchTitle/" + data;
        }
        function afegirPathAuthor(data) {
            document.getElementById("formulari3").action = "./webresources/generic/searchAuthor/" + data;
        }        
        function afegirPathCreaDate(data) {
            document.getElementById("formulari4").action = "./webresources/generic/searchCreationDate/" + data;
        }        
        function afegirPathKeywords(data) {
            document.getElementById("formulari5").action = "./webresources/generic/searchByKeywords/" + data;
        }
        </script>
        
    </body>
</html>