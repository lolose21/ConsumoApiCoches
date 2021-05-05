

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller"
             class="controllers.ControllerCoche"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DETALLES COCHE</h1>
        <%
        String idcoche = request.getParameter("idcoche");
        
        %>
        <h1><%=idcoche%></h1>
    </body>
</html>
