<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.dto.OperacionDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.id.IncrementGenerator" %>
<%@ page import="es.uma.taw.bank.dto.TransaccionDTO" %>
<%@ page import="es.uma.taw.bank.ui.FiltroOperacionesPersona" %>
<%@ page import="javax.swing.text.StyledEditorKit" %>
<%@ page import="es.uma.taw.bank.dto.TransaccionDTO" %>
<%@ page import="es.uma.taw.bank.service.OperacionService" %>
<%@ page import="es.uma.taw.bank.service.TransaccionService" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 05/05/2023
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<TransaccionDTO> operaciones = (List<TransaccionDTO>) request.getAttribute("operaciones");
  Integer cuentaid = (Integer) request.getAttribute("cuentaid");
  FiltroOperacionesPersona f= (FiltroOperacionesPersona) request.getAttribute("filtro");
  TransaccionService transaccionService;
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<a href="/persona/">Volver</a>
<%
  if (operaciones.isEmpty()) {
%>
<h1>No hay ninguna operación registrada</h1>
<%
} else {
%>
<h1>Lista de operaciones</h1>

<form:form action="/persona/operaciones/${cuentaid}/filtrar" modelAttribute="filtro" method="post">
  <label>Buscar por:</label> <br/>
  <form:label path="Iban">Iban:</form:label>
  <form:input path="Iban"/>
  <h5>Ordenar por:</h5>
  <form:checkbox path="fecha" label="Fecha"/>
  <form:checkbox path="cantidad" label="Cantidad"/>
  <form:button type="submit">Filtrar</form:button>
</form:form>

<table border="1">
  <tr>
    <th>Cuenta origen</th>
    <th>Cuenta destino</th>
    <th>Cantidad</th>
    <th>Fecha de instrucción</th>
    <th>Fecha de ejecución</th>
  </tr>
  <%
    for (TransaccionDTO operacion : operaciones) {
  %>
  <tr>
    <td><%= operacion.getCuentaOrigenIbanCuenta() %>
    </td>
    <td><%= operacion.getCuentaDestinoIbanCuenta() %>
    </td>
    <td><%= operacion.getCantidad() %> <%= operacion.getDivisa() %>
    </td>
    <td><%= operacion.getFechaInstruccion() %>
    </td>
    <td><%= operacion.getFechaEjecucion() %>
    </td>
  </tr>
  <%
    }
  %>
</table>
<%
  }
%>
</body>
</html>
