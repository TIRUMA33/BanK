<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %><%--
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
PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");
%>
<head>
    <title>Cajero</title>
</head>
<body>
<h1>Bienvenido, <%= persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2() %></h1>
<br>
<p><a href="/cajero/datos?cliente=<%= persona.getId() %>">Modificar mis datos</a></p>
<h2>Tus cuentas:</h2><br>
<table border="1">
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
            <a href="/cajero/cuenta?cuenta=<%= c.getId() %>"> <%= c.getIbanCuenta() %> </a>
        </td>
        <td>
            <%= c.getSaldo() %>
        </td>
    </tr>
<%
    }
%>
</table>

</body>
</html>
