<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Óscar Fernández Díaz
  Date: 20/04/2023
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>REGISTRO DE EMPRESA</h1>
<form:form action="/registro/empresa/" modelAttribute="empresa" method="post">
    <h2>Datos de la empresa</h2>
    <form:label path="empresa.cif">CIF(*)</form:label>
    <form:input path="empresa.cif" size="9" maxlength="9" required="required"/> <br/>
    <form:label path="empresa.nombre">Nombre de la empresa (*)</form:label>
    <form:input path="empresa.nombre" required="required"/>
    <h2>Dirección</h2>
    <table>
        <tr>
            <td><form:label path="direccion.calle">Calle (*)</form:label></td>
            <td><form:input path="direccion.calle" size="45" maxlength="45" required="required"/></td>
            <td><form:label path="direccion.numero">Número (*)</form:label></td>
            <td><form:input path="direccion.numero" required="required"/></td>
        </tr>
        <tr>
            <td><form:label path="direccion.plantaPuertaOficina">Planta/Puerta/Oficina (*)</form:label></td>
            <td><form:input path="direccion.plantaPuertaOficina" size="45" maxlength="45" required="required"/></td>
        </tr>
        <tr>
            <td><form:label path="direccion.ciudad">Ciudad (*)</form:label></td>
            <td><form:input path="direccion.ciudad" size="45" maxlength="45" required="required"/></td>
            <td><form:label path="direccion.region">Región</form:label></td>
            <td><form:input path="direccion.region" size="45" maxlength="45"/></td>
        </tr>
        <tr>
            <td><form:label path="direccion.pais">País (*)</form:label></td>
            <td><form:input path="direccion.pais" size="45" maxlength="45" required="required"/></td>
            <td><form:label path="direccion.codigoPostal">C.P. (*)</form:label></td>
            <td><form:input path="direccion.codigoPostal" required="required"/></td>
        </tr>
        <tr>
            <td></td>
            <td><form:checkbox path="valida" label="Válida (dirección actual)"/></td>
        </tr>
        <tr>
            <td><form:label path="usuario.contrasena">Contraseña (*)</form:label></td>
            <td><form:password path="usuario.contrasena" size="45" maxlength="45" required="required"/></td>
            <td><form:label path="rcontrasena">Contraseña. Repetir (*)</form:label></td>
            <td><form:password path="rcontrasena" size="45" maxlength="45" required="required"/></td>
        </tr>
    </table>
    <input type="submit" value="Registrar">
    <input type="reset" value="Cancelar" onclick="location.href='/'">
</form:form>
</body>
</html>
