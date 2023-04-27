<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 27/04/2023
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>Nueva transferencia</h1>
<form:form action="/empresa/${empresaId}/persona/${personaId}/transferencia/realizar" modelAttribute="transaccion"
           method="post">
    <form:hidden path="id"/>
    <form:hidden path="cuentaBancoByCuentaOrigen"/>
    <table>
        <tr>
            <th>Cuenta origen</th>
            <td>${transaccion.cuentaBancoByCuentaOrigen.ibanCuenta}</td>
        </tr>
        <tr>
            <th>Saldo disponible</th>
            <td>${transaccion.cuentaBancoByCuentaOrigen.saldo} ${transaccion.cuentaBancoByCuentaOrigen.moneda}</td>
        </tr>
        <tr>
            <th>Cuenta destino</th>
            <td><form:select path="cuentaBancoByCuentaDestino" items="${cuentas}" itemValue="id"
                             itemLabel="ibanCuenta"/></td>
        </tr>
        <tr>
            <th>Importe</th>
            <td><form:input path="cantidad"/> ${transaccion.cuentaBancoByCuentaOrigen.moneda}</td>
        </tr>
    </table>
    <form:button>Realizar</form:button>
</form:form>
</body>
</html>
