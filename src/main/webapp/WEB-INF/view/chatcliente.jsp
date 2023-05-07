<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 05/05/2023
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<a href="/persona/">Volver</a>
<h1>Conversación con el asistente</h1>
<%
   List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");
%>

<table border="1" style="width: 100%; table-layout:fixed">
    <%
        for (MensajeEntity msj : mensajes) {
            if(msj.getUsuarioByEmisor().getId()!=27){
    %>
    <tr>
        <td></td>
        <td style="word-wrap: break-word"><b>Tú: </b><%=msj.getContenido()%></td>
    </tr>
    <%
        }else{
    %>
    <tr>
        <td style="word-wrap: break-word"><b>Asistente: </b><%=msj.getContenido()%></td>
        <td></td>
    </tr>
    <%
            }
        }
    %>
</table>
<form:form action="/asistencia/enviar" method="post" modelAttribute="mensaje">
    <form:hidden path="id"/>
    Escriba su mensaje:<br/> <form:textarea path="contenido" size="500" maxlength="500" rows="10" cols="50"/>
    <form:hidden path="conversacionByConversacion"/>
    <form:hidden path="usuarioByEmisor"/>
    <input type="submit" value="Enviar">
</form:form>
<a href="/asistencia/cerrar?id=${conversacion.id}">Cerrar</a>

</body>
</html>
