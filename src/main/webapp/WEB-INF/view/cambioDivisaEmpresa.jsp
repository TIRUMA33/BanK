<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.DivisaEntity" %>
<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Óscar Fernández Díaz
  Date: 30/04/2023
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<DivisaEntity> divisas = (List<DivisaEntity>) request.getAttribute("divisas");
    List<String> cambios = (List<String>) request.getAttribute("cambios");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>Cambio de divisa</h1>
<form action="/empresa/${id}/cuenta/${cuentaId}/cambioDivisa/realizar" method="post">
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
                <option value="<%= divisas.get(i).getId() %>"><%= cambios.get(i) %>
                </option>
                <%
                    }
                %>
            </select></td>
        </tr>
    </table>
    <input type="submit" value="Cambiar"/>
    <input type="reset" value="Cancelar" onclick="location.href='/empresa/${id}/persona'">
</form>
</body>
</html>
