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

<h1>Bienvenido de nuevo</h1>
<p>Está gestionando ${empresa.nombre}.</p>

<form action="/empresa/${empresa.id}/persona" method="post">
    <h2>Operaciones</h2>
    <table style="border: 0px; width: 100%">
        <tr>
            <td><a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/transferencia">Transferencias</a></td>
            <td><a href="/empresa/${empresa.id}/cuenta/cambioDivisa">Cambio de divisas</a></td>
            <td><a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/operaciones">Historial de
                operaciones</a></td>
            <td><a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/listar">Socios/Autorizados</a></td>
        </tr>
    </table>
    <h2>Gestiones</h2>
    <table style="border: 0px; width: 100%">
        <tr>
            <td><a href="/registro/empresa/${empresa.id}/persona">Personas autorizadas</a></td>
            <td><a href="/empresa/${empresa.id}/cuentas">Cuentas</a></td>
        </tr>
    </table>
    <h2>Perfil</h2>
    <table style="border: 0px; width: 100%">
        <tr>
            <td><a href="/empresa/${empresa.id}/persona/<%= usuario.getId() %>/editar">Personal</a></td>
            <td><a href="/empresa/${empresa.id}/editar">Empresa</a></td>
        </tr>
    </table>
</form>
</body>
</html>
