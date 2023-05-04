<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 02/05/2023
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<%
    if (operaciones.isEmpty()) {
%>
<h1>No hay ninguna operación registrada</h1>
<%
} else {
%>
<h1>Lista de operaciones</h1>

<form:form action="/empresa/${id}/persona/${personaId}/operaciones/filtrar" modelAttribute="filtro" method="post">
    <label>Buscar por:</label> <br/>
    <form:label path="socio">Nombre:</form:label>
    <form:input path="socio"/>
    <form:label path="cuenta">Cuenta:</form:label>
    <form:select path="cuenta" multiple="true">
        <form:option value="" label=""/>
        <form:options items="${cuentas}" itemLabel="ibanCuenta" itemValue="ibanCuenta"/>
    </form:select>
    <form:label path="cantidad">Cantidad:</form:label>
    <form:input path="cantidad"/>
    <form:checkbox path="fechaEjecucion" label="Fecha de ejecución"/>
    <form:button type="submit">Filtrar</form:button>
</form:form>

<table border="1">
    <tr>
        <th>Socio</th>
        <th>Cuenta origen</th>
        <th>Cuenta destino</th>
        <th>Cantidad</th>
        <th>Fecha de instrucción</th>
        <th>Fecha de ejecución</th>
    </tr>
    <%
        for (OperacionEntity operacion : operaciones) {
    %>
    <tr>
        <td><%= operacion.getPersonaByPersonaId().getNombre() %> <%= operacion.getPersonaByPersonaId().getApellido1()
        %> <%= operacion.getPersonaByPersonaId().getApellido2() %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaOrigen().getIbanCuenta() %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaDestino().getIbanCuenta() %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getCantidad() %> <%=
        operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaOrigen().getDivisaByDivisaId().getNombre() %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getFechaInstruccion() %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getFechaEjecucion() %>
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
