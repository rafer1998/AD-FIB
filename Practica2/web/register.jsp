<%-- 
    Document   : register
    Created on : 17-sep-2019, 15:11:39
    Author     : ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1><i>Crear Usuario</i></h1>
        
        <form action="register" method="POST">
            <p>Usuario: <input type="text" name="usuario" size="40"></p>
            <p>Password: <input type="text" name="password" size="40"></p>
            <p>
                <input type="submit" value="Enviar">
                <input type="reset" value="Borrar">
            </p>
        </form> 
        
        <form action="login.jsp">
        <input type="submit" value="Volver a la pantalla principal">
        </form>
        
    </body>
</html>
