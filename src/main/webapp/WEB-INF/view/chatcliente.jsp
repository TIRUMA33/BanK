<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.dto.MensajeDTO" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 05/05/2023
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<a href="/persona/">Volver</a>
<h1>Conversación con el asistente</h1>
<%
   List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("mensajes");
%>

<table style="width: 100%; table-layout:fixed">
    <%
        for (MensajeDTO msj : mensajes) {
            if(msj.getEmisor()!=27){
    %>
    <tr>
        <td></td>
        <td style="word-wrap: break-word; border: black 1px solid"><b>Tú: </b><%=msj.getContenido()%></td>
    </tr>
    <%
        }else{
    %>
    <tr>
        <td style="word-wrap: break-word; border: black 1px solid"><b>Asistente: </b><%=msj.getContenido()%></td>
        <td></td>
    </tr>
    <%
            }
        }
    %>
</table>
<div style="float: right">
    <br/><br/>
    <form:form action="/asistencia/enviar" method="post" modelAttribute="mensaje">
        <form:hidden path="id"/>
        Escriba su mensaje:<br/> <form:textarea path="contenido" size="500" maxlength="500" rows="10" cols="50"/> <br/>
        <form:hidden path="conversacion"/>
        <form:hidden path="conversacionFechaCreacion"/>
        <form:hidden path="conversacionTerminada"/>
        <form:hidden path="emisor"/>
        <form:hidden path="emisorContrasena"/>
        <form:hidden path="emisorNif"/>
        <input type="submit" value="Enviar">
    </form:form>
    <a href="/asistencia/cerrar?id=${conversacion.id}">Cerrar</a>
</div>

</body>
</html>
