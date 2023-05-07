<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="es.uma.taw.bank.dto.CuentaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alejandro Guerra
  Date: 20/04/2023
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tu cuenta</title>
</head>
<body>
<a href="/cajero/listar?cliente=${cuenta.cliente}">Volver a mis cuentas</a>
<h2>Tu cuenta:</h2>
<h3>IBAN: ${cuenta.ibanCuenta}</h3>
<%
    CuentaDTO cuenta = (CuentaDTO) request.getAttribute("cuenta");
    if (cuenta.getEstado().equals("Bloqueada")) {

%>
<h1>Tu cuenta está bloqueada, solicite un desbloqueo <a href="/cajero/desbloquear?cuenta=<%= cuenta.getId() %>">
    aquí </a>.</h1>
<%
} else if (cuenta.getEstado().equals("Pendiente de desbloqueo")) {
%>
<h1>Tu cuenta está bloqueada, tienes la solicitud de desbloqueo pendiente.</h1>
<%
} else {
%>

<table border="1">
    <tr>
        <th>MONEDA</th>
        <th>SALDO</th>
        <th>SWIFT</th>
        <th>PAÍS</th>
        <th>FECHA DE APERTURA</th>
        <th>FECHA DE CIERRE</th>
    </tr>
    <tr>
        <td>${cuenta.divisaNombre}</td>
        <td>${cuenta.saldo}</td>
        <td>${cuenta.swift}</td>
        <td>${cuenta.pais}</td>
        <td>${cuenta.fechaApertura}</td>
        <td>${cuenta.fechaCierre}</td>
    </tr>
</table>
<p><a href="/cajero/transferencia?cuenta=${cuenta.id}">Realizar transferencia</a></p>
<p><a href="/cajero/retirada?cuenta=${cuenta.id}">Sacar dinero</a></p>
<p><a href="/cajero/operaciones?cuenta=${cuenta.id}">Operaciones de mi cuenta</a></p>
<p><a href="/cajero/cambioDivisa?cuenta=${cuenta.id}">Cambio de divisa</a></p>
<%
    }
%>
</body>
</html>
