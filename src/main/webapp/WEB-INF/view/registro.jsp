<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 20/04/2023
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<p>Seleccione si quiere crear una cuenta para una persona física o para una empresa</p>
<form action="/persona/">
  <input type="submit" value="Persona física">
</form>
<form action="/empresa/">
  <input type="submit" value="Empresa">
</form>
</body>
</html>
