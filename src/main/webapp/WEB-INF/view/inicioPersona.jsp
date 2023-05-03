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

<form action="/persona/editar" method="post">
  <a href="/persona/editar?id=<%= usuario.getId() %>">Modificar datos Personales</a>
</form>
</body>
</html>
