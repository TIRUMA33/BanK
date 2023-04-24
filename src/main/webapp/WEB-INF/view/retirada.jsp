<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 22/04/2023
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retirada</title>
</head>
<body>
<h1>Realizar una retirada:</h1>
<form method="post" action="/cajero/retirar">
    <input hidden="true" value="${cuenta.id}" name="cuenta">
    Cantidad:<input type="number" name="cantidad">
    <button>Retirar</button>
</form>
</body>
</html>
