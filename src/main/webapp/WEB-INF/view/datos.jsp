<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 20/04/2023
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datos del cliente</title>
</head>
<body>
<h1>
    Tus datos:
</h1>
<br>
<form:form action="/cajero/editar" modelAttribute="persona" method="post">
    <form:input path="nombre" maxlength="45" size="45"></form:input><br>
    <form:input path="apellido1" maxlength="45" size="45"></form:input><br>
    <form:input path="apellido2" maxlength="45" size="45"></form:input><br>
    <form:input path="fechaNacimiento"></form:input><br>
    <form:input path="dni" maxlength="9" size="9"></form:input><br>
    <form:button>Guardar</form:button>
</form:form>
</body>
</html>
