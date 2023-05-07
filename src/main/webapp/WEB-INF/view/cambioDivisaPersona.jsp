<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.DivisaEntity" %>
<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 05/05/2023
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DivisaEntity> divisas = (List<DivisaEntity>) request.getAttribute("divisas");
    List<String> cambios = (List<String>) request.getAttribute("cambios");
    CuentaBancoEntity cuenta = (CuentaBancoEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<h1>Cambio de divisa</h1>
<form action="/persona/cambioDivisa/${cuenta.id}/realizar" method="post">
    <table>
        <tr>
            <th>Cuenta origen</th>
            <td>${cuenta.ibanCuenta}</td>
        </tr>
        <tr>
            <th>Saldo disponible</th>
            <td>${cuenta.saldo}
                ${cuenta.divisaByDivisaId.nombre}</td>
        </tr>
        <tr>
            <th>Divisa</th>
            <td><select name="divisaSelect">
                <%
                    for (int i = 0; i < divisas.size(); i++) {
                %>
                <option value="<%= divisas.get(i).getId() %>"><%= cambios.get(i) %></option>
                <%
                    }
                %>
            </select></td>
        </tr>
    </table>
    <input type="submit" value="Cambiar"/>
    <input type="reset" value="Cancelar" onclick="location.href='/persona'">
</form>
</body>
</html>
