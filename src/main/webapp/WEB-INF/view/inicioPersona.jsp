<%@ page import="es.uma.taw.bank.entity.UsuarioEntity" %>
<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Pablo Ruiz Galianez
  Date: 02/05/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
  CuentaBancoEntity cuenta = (CuentaBancoEntity) request.getAttribute("cuenta");
  PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");
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
     <% if (cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 1) { %>
      <a href="/persona/solicitar?id=<%= cuenta.getId()%>">Solicitar bloqueo de cuenta</a>
    <% } else if (cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 2){  %>
      <a href="/persona/solicitar?id=<%= cuenta.getId()%>">Solicitar activacion de cuenta</a>
    <% } else {%>
      Solicitud pendiente
    <%
    }
    %>
    <a href="/asistencia/?id=<%= usuario.getId() %>">Asistencia</a>

</form>
</body>
</html>