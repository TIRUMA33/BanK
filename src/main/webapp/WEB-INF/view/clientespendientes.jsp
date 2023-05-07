<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.EmpresaEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %><%--
  Created by IntelliJ IDEA.
  User: itsso
  Date: 22/04/2023
  Time: 12:13
  To change this template use File | Settings | File Templates.
  @author: David Castaños Benedicto
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<EmpresaEntity> EmpresasPendientes = (List<EmpresaEntity>) request.getAttribute("EmpresasPendientes");%>
<%List<PersonaEntity> PersonasPendientes = (List<PersonaEntity>) request.getAttribute("PersonasPendientes");%>

<html>
<head>
    <title>Gestor</title>
</head>
<body>
<h1>SOLICITUDES</h1>
<a href="/gestor/">
    <button>Volver a Inicio</button>
</a>
<hr style="border: 0">
<table border="1">
    <tr>
        <th>Tipo</th>
        <th>Nombre</th>
        <th>Identificador</th>
        <th>Concesión</th>
    </tr>

    <%
        for (PersonaEntity persona : PersonasPendientes) {
    %>
    <tr>
        <td>Persona</td>
        <td><%=persona.getNombre()%> <%=persona.getApellido1()%> <%=persona.getApellido2()%>
        </td>
        <td><%=persona.getDni()%>
        </td>
        <td><a href="/gestor/concesion?id=<%=persona.getId()%>">Gestionar</a></td>
    </tr>
    <%
        }
    %>
    <%
        for (EmpresaEntity empresas : EmpresasPendientes) {
    %>
    <tr>
        <td>Empresa</td>
        <td><%=empresas.getNombre()%>
        </td>
        <td><%=empresas.getCif()%>
        </td>
        <td><a href="/gestor/concesion?id=<%=empresas.getId()%>">Gestionar</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
