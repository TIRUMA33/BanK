<%@ page import="es.uma.taw.bank.ui.Cambio" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Alejandro Guerra
  Date: 23/04/2023
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<Cambio> cambios = (List<Cambio>) request.getAttribute("cambios");
%>
<head>
    <title>Cambio de divisa</title>
</head>
<body>
<a href="/cajero/cuenta?cuenta=${cuenta.id}">Volver</a>
<h1>Cambia de divisa</h1>
<h3>Tu cuenta contiene ${cuenta.divisaNombre}</h3>
<form method="post" action="/cajero/cambiarA">
    <select name="moneda">
        <%
            for(Cambio c: cambios){
        %>
        <option value="<%= c.getMonedaDestino() %>"><%= c.getMonedaDestino() %></option>
        <%
            }
        %>
    </select>
  <input hidden="true" value="${cuenta.id}" name="cuenta">
  <button>Seleccionar esta divisa</button>
</form>
</body>
</html>
