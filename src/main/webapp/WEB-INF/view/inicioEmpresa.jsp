<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 23/04/2023
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<form action="/empresa/${empresa.id}" method="post">
    <h1>Bienvenidos de nuevo, ${empresa.nombre}</h1>
    <h2>Gestión de acceso</h2>
    <a href="/registro/empresa/${empresa.id}/persona">Personas autorizadas</a>
</form>
</body>
</html>
