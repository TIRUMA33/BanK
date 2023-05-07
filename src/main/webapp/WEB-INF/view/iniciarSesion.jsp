<%--
  Created by IntelliJ IDEA.
  User: Óscar Fernández Díaz
  Date: 23/04/2023
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h2>Iniciar sesión</h2>
<form action="/iniciarSesion/" method="post">
    <table>
        <tr>
            <td>NIF/CIF:</td>
            <td><input type="text" name="nif" size="9" maxlength="9" required="required"></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="contrasena" size="45" maxlength="45" required="required"></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Iniciar sesión</button>
            </td>
            <td>
                <%
                    if (error != null) {
                %>
                <p style="color:red;">
                    ${error}
                </p>
                <%
                    }
                %>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
