<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 24/04/2023
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <h2>Gestión de acceso</h2>
    <a href="/registro/empresa/${empresa.id}/persona">Personas autorizadas</a>
    <h2>Perfil</h2>
    <a href="/empresa/${empresa.id}/editar">Empresa</a> <br/>
    <a href="">Personales</a>
</form>
</body>
</html>
