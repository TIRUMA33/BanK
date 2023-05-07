<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 07/05/2023
  Time: 2:30
  To change this template use File | Settings | File Templates.
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
  List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("todomensajes");
%>
<h1>Mensajes intercambiados con el usuario <%=mensajes.get(0).getUsuarioByEmisor().getNif()%></h1>
<table border="1">
    <tr>
        <th>Emisor</th>
        <th>Contenido</th>
        <th>Fecha de envío</th>
    </tr>
    <%
        for (MensajeEntity mensaje : mensajes) {
            if(mensaje.getUsuarioByEmisor().getTipoUsuarioByTipoUsuario().getId()==3){
    %>
    <tr>
        <td><b>Tú</b></td>
        <td><%=mensaje.getContenido()%></td>
        <td><%=mensaje.getFecha()%></td>
    </tr>
    <%
        }else{
    %>
    <tr>
        <td><b><%=mensaje.getUsuarioByEmisor().getNif()%></b></td>
        <td><%=mensaje.getContenido()%></td>
        <td><%=mensaje.getFecha()%></td>
    </tr>
    <%
        }
        }
    %>

</table>

</body>
</html>
