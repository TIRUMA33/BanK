<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: oscfd
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
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>NIF</th>
        <th>Tipo</th>
    </tr>
    <%
        for (Object[] persona : personas) {
    %>
    <tr>
        <td><%= ((PersonaEntity) persona[0]).getNombre() + " " + ((PersonaEntity) persona[0]).getApellido1() + " " + ((PersonaEntity) persona[0]).getApellido2() %>
        </td>
        <td><%= ((PersonaEntity) persona[0]).getDni() %>
        </td>
        <td><%= persona[1] %>
        </td>
        <td><input type="checkbox"></td>
    </tr>
    <%
        }
    %>
</table>
<input type="reset" value="Borrar"/> <br/>
<input type="submit" value="Finalizar"/>
</body>
</html>
