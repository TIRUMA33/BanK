<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 20/04/2023
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
List<CuentaBancoEntity> cuentas = (List<CuentaBancoEntity>) request.getAttribute("cuentas");
ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
%>
<head>
    <title>Cajero</title>
</head>
<body>
<h1>Bienvenido, <%= cliente.getPersonaById().getNombre() %></h1>
<br>
<h2>Tus cuentas:</h2><br>
<table>
    <tr>
        <th>
            IBAN
        </th>
        <th>
            SALDO
        </th>
    </tr>

<%
    for(CuentaBancoEntity c: cuentas){
%>
    <tr>
        <td>
            <%= c.getIbanCuenta() %>
        </td>
        <td>
            <%= c.getSaldo() %>
        </td>
    </tr>
</table>
<p>
    <a href="/cajero/datos"> Modificar mis datos </a>
</p>
<%
    }
%>

</body>
</html>
