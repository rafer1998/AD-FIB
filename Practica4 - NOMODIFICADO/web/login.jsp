<%-- 
    Document   : login
    Created on : 17-sep-2019, 14:13:07
    Author     : ruben.barcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
              background-color: lightblue;
              text-align: center;
            }            
        </style>
    </head>
    <body>
        <div class="center">
            <h1><i>Iniciar Sesión</i></h1>
            
            <form action="login" method="POST">
                <p>Usuario: <input type="text" name="usuario" size="40" required></p>
                <p>Password: <input type="password" name="password" size="40" required></p>
                <p>
                    <input type="submit" value="Enviar">
                    <input type="reset" value="Borrar">
                </p>
            </form>
            
            <a href="register.jsp">Registrarse</a>
        </div>
    </body>
</html>
