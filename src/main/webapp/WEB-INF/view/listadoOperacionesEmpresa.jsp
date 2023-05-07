<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw.bank.dto.OperacionDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.dto.EmpresaDTO" %>
<%@ page import="es.uma.taw.bank.dto.PersonaDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 02/05/2023
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<OperacionDTO> operaciones = (List<OperacionDTO>) request.getAttribute("operaciones");
    List<EmpresaDTO> empresas = (List<EmpresaDTO>) request.getAttribute("empresas");
    List<PersonaDTO> personas = (List<PersonaDTO>) request.getAttribute("personas");
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

<form:form action="/empresa/${id}/operaciones/filtrar" modelAttribute="filtro" method="post">
    <label>Buscar por:</label> <br/>
    <form:label path="cuenta">Cuenta:</form:label>
    <form:input path="cuenta" size="24" maxlength="24"/>
    <form:checkbox path="cantidad" label="Cantidad"/>
    <form:checkbox path="fechaEjecucion" label="Fecha de ejecución"/>
    <form:button type="submit">Filtrar</form:button>
</form:form>

<table border="1">
    <tr>
        <th>Ordenante</th>
        <%--<th>Cuenta origen</th>--%>
        <%--<th>Beneficiario</th>--%>
        <%--<th>Cuenta destino</th>--%>
        <th>Cantidad</th>
        <th>Fecha de instrucción</th>
        <th>Fecha de ejecución</th>
    </tr>
    <%
        for (OperacionDTO operacion : operaciones) {
    %>
    <tr>
        <td><%= operacion.getPersonaNombre() %> <%= operacion.getPersonaApellido1()
        %> <%= operacion.getPersonaApellido2() %>
        </td>
        <%--<td><%= operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaOrigen().getIbanCuenta() %>
        </td>
        <td>
            <%
                for (EmpresaDTO empresa : empresas) {
                    if
                    (empresa.getId().equals(operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaDestino().getClienteByTitularId().getId())) {
            %>
            <%= empresa.getNombre() %>
            <%
                    }
                }
                for (PersonaDTO persona : personas) {
                    if
                    (persona.getId().equals(operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaDestino().getClienteByTitularId().getId())) {
            %>
            <%= persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2() %>
            <%
                    }
                }
            %>
        </td>
        <td><%= operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaDestino().getIbanCuenta() %>
        </td>--%>
        <td><%= operacion.getTransaccionCantidad() %> <%--<%=
        operacion.getTransaccionByTransaccionId().getCuentaBancoByCuentaOrigen().getDivisaByDivisaId().getNombre() %>--%>
        </td>
        <td><%= operacion.getTransaccionFechaInstruccion() %>
        </td>
        <td><%= operacion.getTransaccionFechaEjecucion() %>
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
