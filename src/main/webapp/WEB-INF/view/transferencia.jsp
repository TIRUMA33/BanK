<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 22/04/2023
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transferencia entre cuentas</title>
</head>
<body>
<h1>Realizar una transferencia:</h1>
<form:form method="post" modelAttribute="transaccion" action="/cajero/transferir">
    <form:hidden path="id"></form:hidden>
    <form:hidden path="cuentaBancoByCuentaOrigen"></form:hidden>
  Destino:<form:select path="cuentaBancoByCuentaDestino" items="${cuentas}" itemValue="id" itemLabel="ibanCuenta"></form:select><br>
  Cantidad:<form:input path="cantidad" size="20" maxlength="20" required="required"></form:input><br>
  <form:button>Realizar</form:button>

</form:form>
</body>
</html>
