<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.dto.MensajeDTO" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 07/05/2023
  Time: 2:30
  To change this template use File | Settings | File Templates.
  //Autor Pablo Robles Mansilla
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mensajes intercambiados</title>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<a href="/asistencia/conversaciones">Volver</a>
<%
    List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("todomensajes");
    if (mensajes != null && !mensajes.isEmpty()) {
%>
<h1>Mensajes intercambiados con el usuario <%=mensajes.get(0).getEmisorNif()%>
</h1>
<table border="1">
    <tr>
        <th>Emisor</th>
        <th>Contenido</th>
        <th>Fecha de envío</th>
    </tr>
    <%
        for (MensajeDTO mensaje : mensajes) {
            if (mensaje.getEmisor() == 27) {
    %>
    <tr>
        <td><b>Tú</b></td>
        <td><%=mensaje.getContenido()%>
        </td>
        <td><%=mensaje.getFecha()%>
        </td>
    </tr>
    <%
    } else {
    %>
    <tr>
        <td><b><%=mensaje.getEmisorNif()%>
        </b></td>
        <td><%=mensaje.getContenido()%>
        </td>
        <td><%=mensaje.getFecha()%>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>
<%
} else {
%>
<h1>No hay mensajes intercambiados con el usuario</h1>
<%
    }
%>
</body>
</html>
