<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Óscar Fernández Díaz
  Date: 22/04/2023
  Time: 15:39
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
<%
    if (!personas.isEmpty()) {
%>
<form:form action="/registro/empresa/${empresa.id}/persona/borrar" modelAttribute="registroEmpresaPersona"
           method="post">
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>NIF</th>
            <th>Tipo</th>
        </tr>
        <%
            for (Object[] p : personas) {
                PersonaEntity persona = (PersonaEntity) p[0];
                String tipo = (String) p[1];
        %>
        <tr>
            <td><%= persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2() %>
            </td>
            <td><%= persona.getDni() %>
            </td>
            <td><%= tipo %>
            </td>
            <td><input type="checkbox" name="personaId" value="<%= persona.getId() %>"></td>
        </tr>
        <%
            }
        %>
    </table>
    <input type="submit" value="Borrar" onclick="return confirm('¿Está seguro de borrar las personas seleccionadas?')"/>
    <br/>
</form:form>
<%
    }
%>
</body>
</html>
