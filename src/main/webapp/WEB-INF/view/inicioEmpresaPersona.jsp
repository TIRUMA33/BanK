  <%@ page import="es.uma.taw.bank.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 24/04/2023
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
%>

<html>
<head>
    <title>BanK</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<form action="/empresa/${empresa.id}/persona" method="post">
    <h1>Bienvenido de nuevo</h1>
    <p>Está gestionando ${empresa.nombre}.</p>
    <h2>Operaciones</h2>
    <a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/transferencia">Transferencias</a>
    <a href="#">Cambio de divisas</a>
    <a href="#">Historial empresa</a>
    <a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/listar">Socios/Autorizados</a>
    <h2>Gestión de acceso</h2>
    <a href="/registro/empresa/${empresa.id}/persona">Personas autorizadas</a>
    <h2>Perfil</h2>
    <a href="/empresa/${empresa.id}/editar">Empresa</a> <br/>
    <a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/editar">Personales</a>
</form>
</body>
</html>
