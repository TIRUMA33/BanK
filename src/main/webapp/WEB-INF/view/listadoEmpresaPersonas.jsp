<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.dto.PersonaDTO" %>
<%@ page import="es.uma.taw.bank.dto.DireccionDTO" %><%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 26/04/2023
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Object[]> personas = (List<Object[]>) request.getAttribute("personas");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>Listado de socios/autorizados</h1>

<form:form action="/empresa/${id}/persona/${personaId}/filtrar" modelAttribute="filtro" method="post">
    <label>Buscar por:</label> <br/>
    <form:label path="texto">Contiene:</form:label>
    <form:input path="texto"/>
    <form:checkbox path="fechaNacimiento" label="Fecha de nacimiento"/>
    <form:label path="tipo">Tipo:</form:label>
    <form:select path="tipo">
        <form:option value="" label=""/>
        <form:options items="${tipoPersonaRelacionada}" itemLabel="tipo" itemValue="tipo"/>
    </form:select>
    <form:button type="submit">Filtrar</form:button>
</form:form>

<table border="1">
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Fecha de nacimiento</th>
        <th>Tipo</th>
        <th>Código postal</th>
        <th>País</th>
    </tr>
    <%
        for (Object[] p : personas) {
            PersonaDTO persona = (PersonaDTO) p[0];
            DireccionDTO direccion = (DireccionDTO) p[1];
            String tipoClienteRelacionado = (String) p[2];
            String tipoPersonaRelacionada = (String) p[3];
    %>
    <tr>
        <td><%= persona.getDni() %>
        </td>
        <td><%= persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2() %>
        </td>
        <td><%= persona.getFechaNacimiento() %>
        </td>
        <td><%= tipoPersonaRelacionada %>
        </td>
        <td><%= direccion.getCodigoPostal() %>
        </td>
        <td><%= direccion.getPais() %>
        </td>
        <td>
            <a href="/empresa/${id}/persona/${personaId}/permiso/<%= persona.getId() %>"><%= tipoClienteRelacionado %>
            </a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
