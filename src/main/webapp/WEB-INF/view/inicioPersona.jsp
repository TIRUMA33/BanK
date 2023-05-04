<%@ page import="es.uma.taw.bank.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
%>

<html>
<head>
  <title>BanK</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>
<h1>Bienvenido ${persona.nombre} ${persona,apellido1}.</h1>
<form action="/persona/editar" method="post">

  <a href="/persona/editar?id=<%= usuario.getId() %>">Modificar datos Personales</a>
  <a href="/persona/transferencia?id=<%= usuario.getId() %>">Realizar transferencia</a>
  <a href="/asistencia/chat?id=<%= usuario.getId() %>">Asistencia</a>
</form>
</body>
</html>