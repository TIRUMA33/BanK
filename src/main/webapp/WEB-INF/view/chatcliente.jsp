<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 24/04/2023
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");
%>
<h1>Mensajes</h1>
<%if (mensajes != null) {%>

    <table>
        <tr>
            <th>Id</th>
            <th>Contenido</th>
            <th>Fecha</th>
        </tr>
        <%for (MensajeEntity mensaje : mensajes) {%>
            <tr>
                <td><%=mensaje.getId()%></td>
                <td><%=mensaje.getContenido()%></td>
                <td><%=mensaje.getFecha()%></td>
            </tr>
        <%}%>
    </table>
<%}%>



</body>
</html>
