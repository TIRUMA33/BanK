<%@ page import="es.uma.taw.bank.dto.UsuarioDTO" %>
<%@ page import="es.uma.taw.bank.dto.CuentaDTO" %>
<%@ page import="es.uma.taw.bank.dto.PersonaDTO" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
  CuentaDTO cuenta = (CuentaDTO) request.getAttribute("cuenta");
  PersonaDTO persona = (PersonaDTO) request.getAttribute("persona");
%>

<html>
<head>
  <title>BanK</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>
<h1>Bienvenido ${persona.nombre} ${persona. apellido1}.</h1>
<form action="/persona/editar" method="post">

    <a href="/persona/editar?id=<%= usuario.getId() %>">Modificar datos Personales</a>
    <a href="/persona/transferencia?id=<%= usuario.getId() %>">Realizar transferencia</a>
    <a href="/persona/cambioDivisa?id=<%= cuenta.getId() %>">Cambio de divisa</a>
    <a href="/persona/operaciones?id=<%= cuenta.getId() %>">Ver operaciones</a>
     <% if (cuenta.getEstado() == 1) { %>
      <a href="/persona/solicitar?id=<%= cuenta.getId()%>">Solicitar bloqueo de cuenta</a>
    <% } else if (cuenta.getEstado() == 2){  %>
      <a href="/persona/solicitar?id=<%= cuenta.getId()%>">Solicitar activacion de cuenta</a>
    <% } else {%>
      Solicitud pendiente
    <%
    }
    %>
    <a href="/asistencia/?id=<%= usuario.getId() %>">Asistencia</a>
    <a href="/cajero/listar?cliente=<%= persona.getId() %>">Cajero</a>

</form>
</body>
</html>