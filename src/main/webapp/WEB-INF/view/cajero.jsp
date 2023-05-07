<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %>
<%@ page import="es.uma.taw.bank.dto.CuentaDTO" %>
<%@ page import="es.uma.taw.bank.dto.ClienteDTO" %>
<%@ page import="es.uma.taw.bank.dto.PersonaDTO" %><%--
  Created by IntelliJ IDEA.
  User: Alejandro Guerra
  Date: 20/04/2023
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentas");
ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
PersonaDTO persona = (PersonaDTO) request.getAttribute("persona");
%>
<head>
    <title>Cajero</title>
</head>
<body>
<%
if(persona!=null){
%>
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
    for(CuentaDTO c: cuentas){
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
<%
}else{
%>
    <h1>Lo sentimos, el cajero solo está disponible para personas, no para empresas.</h1>
<%
    }
%>
</table>

</body>
</html>
