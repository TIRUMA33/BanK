<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 15/2/23
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> lista = (List<ClienteEntity>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h1>Listado de clientes</h1>


<table border="1">
    <tr>
        <th>ID</th>
        <th>FechaInicio</th>
        <th>EstadoCliente</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (ClienteEntity cliente: lista) {
    %>
    <tr>
        <td><%= cliente.getId() %></td>
        <td><%= cliente.getFechaInicio() %></td>
        <td><%= cliente.getEstadoclienteByEstadoCliente() %></td>
    </tr>


    <%
        }
    %>
</table border="1">

<a href="/customer/nuevo" >Nuevo cliente ...</a>

</body>
</html>
