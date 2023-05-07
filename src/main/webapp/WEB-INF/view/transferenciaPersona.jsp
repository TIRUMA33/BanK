<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 03/05/2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1>Nueva transferencia</h1>
<form:form action="/persona/transferencia/realizar" modelAttribute="transaccion"
           method="post">
    <form:hidden path="cuentaOrigen"/>
    <table>
        <tr>
            <th>Cuenta origen</th>
            <td>${transaccion.cuentaOrigenIbanCuenta}</td>
        </tr>
        <tr>
            <th>Saldo disponible</th>
            <td>${transaccion.cuentaOrigenSaldo}
                    ${transaccion.divisa}</td>
        </tr>
        <tr>
            <th>Cuenta destino</th>
            <td><form:select path="cuentaDestino" items="${cuentas}" itemValue="id"
                             itemLabel="ibanCuenta"/></td>
        </tr>
        <tr>
            <th>Importe</th>
            <td><form:input required="required" path="cantidad"/> ${transaccion.divisa}</td>
        </tr>
    </table>
    <form:button>Realizar</form:button>
</form:form>
</body>
</html>
