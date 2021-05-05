

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
        <h1>COCHES</h1>
        <form method="post" action="webdetallescoche.jsp">
         <table BORDER="1">
            <thead>
                <tr>
                    <th>IDCOCHE</th>
                     <th>IMAGEN COCHE</th>
                     <td>DETALLES</td>
                </tr>
            </thead>
            <tbody>
                <%=controller.getTablaCoches()%>
            </tbody>
        </table>
            </form>
    </body>
</html>
