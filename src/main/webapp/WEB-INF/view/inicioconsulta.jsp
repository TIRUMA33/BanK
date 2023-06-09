<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.MensajeEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 24/04/2023
  Time: 13:10
  To change this template use File | Settings | File Templates.
  //Autor Pablo Robles Mansilla
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<a href="/persona/">Volver</a>

<h1>Asistencia virtual</h1>

<form:form action="/asistencia/consultar" method="post" modelAttribute="mensaje">
    <form:hidden path="id"/>
    Escriba su mensaje:<br/> <form:textarea path="contenido" size="500" maxlength="500" rows="10" cols="50"/> <br/>
    <form:hidden path="fecha"/>
    <form:hidden path="conversacion"/>
    <form:hidden path="conversacionTerminada"/>
    <form:hidden path="conversacionFechaCreacion"/>
    <form:hidden path="emisor"/>
    <form:hidden path="emisorNif"/>
    <form:hidden path="emisorContrasena"/>
    <input type="submit" value="Enviar">
</form:form>


</body>
</html>
