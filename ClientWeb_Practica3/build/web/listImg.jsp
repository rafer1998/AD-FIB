<%-- 
    Document   : listImg
    Created on : 17-sep-2019, 15:46:24
    Author     : ruben.barcelo
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>listImg</title>
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
        
        <h1>Lista de Imagenes</h1> 
        
        <%-- start web service invocation --%><hr/>
        <%
        java.util.List<org.me.imagenes.Image> result = null;
        try {
            org.me.imagenes.ServicioImagenes_Service service = new org.me.imagenes.ServicioImagenes_Service();
            org.me.imagenes.ServicioImagenes port = service.getServicioImagenesPort();
            // TODO process result here
            result = port.listImages();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        %>
        <%-- end web service invocation --%><hr/>

        
          <%            
            try{                
                for(org.me.imagenes.Image img : result)
                {
                    String autorImg = img.getAutor(); 
           %>                
                <table>   
                    <tr>
                      <td><%=img.getTitulo()%></td>   
                      <%
                      if(autorImg.equals(autor)){
                      //Si la imagen pertenece al usuario actual -> puede modificar y borrar
                      %> 
                          <td><form action="modificarImagen.jsp" method="POST">  
                                  <input type="submit" value="Modificar Imagen"> 
                                  <input type="hidden" name="titulo" value ="<%=img.getTitulo()%>">  
                                  <input type="hidden" name="descripcion" value ="<%=img.getDescripcion()%>">
                                  <input type="hidden" name="palabras_clave" value ="<%=img.getKeywords()%>">
                                  <input type="hidden" name="fecha_creacion" value ="<%=img.getCreaDate()%>">
                                  <input type="hidden" name="id" value ="<%=img.getID()%>">


                          </form></td>
                          <td><form action="eliminarImagen" method="POST">  
                                  <input type="submit" value="Eliminar Imagen"> 
                                  <input type="hidden" name="id" value ="<%=img.getID()%>">  
                                  <input type="hidden" name="nombre_fichero" value ="<%=img.getTitulo()%>">
                          </form></td>
                      <%   
                      }
                      %> 
                    </tr>
                <%  
                }
                %>
                </table>
                
                <form action="menu.jsp" method="POST" >        	
                    <input type="submit" value="Menu">
                </form>
                <%
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
         %>  
    </body>
</html>
