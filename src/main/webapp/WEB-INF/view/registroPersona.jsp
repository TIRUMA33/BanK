<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 27/03/2023
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>REGISTRO DE PERSONAS FISICAS</h1>
<form:form action="/registro/persona/" modelAttribute="persona" method="post">
<h2>Datos personales</h2>
<table>
    <tr>
        <td><form:label path="persona.dni">NIF (*)</form:label></td>
        <td><form:input path="persona.dni" size="9" maxlength="9"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td><form:label path="persona.nombre">Nombre (*)</form:label></td>
        <td><form:input path="persona.nombre" required="required" size="45" maxlength="45"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td><form:label path="persona.apellido1">Primer Apellido (*)</form:label></td>
        <td><form:input path="persona.apellido1" required="required" size="45" maxlength="45"/></td>
        <td><form:label path="persona.apellido2">Segundo Apellido</form:label></td>
        <td><form:input path="persona.apellido2" size="45" maxlength="45"/></td>
    </tr>
    <tr>
        <td><form:label path="persona.fechaNacimiento">Fecha nacimiento (*)</form:label></td>
        <td><form:input path="persona.fechaNacimiento" required="required" type="date"/></td>
    </tr>
</table>
<h2>Dirección</h2>
<table>
    <tr>
        <td><form:label path="direccion.calle">Calle (*)</form:label></td>
        <td><form:input path="direccion.calle" required="required" size="45" maxlength="45"/></td>
        <td><form:label path="direccion.numero" >Número (*)</form:label></td>
        <td><form:input path="direccion.numero" required="required"/></td>
    </tr>
    <tr>
        <td><form:label path="direccion.plantaPuertaOficina">Planta/Puerta/Oficina (*)</form:label></td>
        <td><form:input path="direccion.plantaPuertaOficina" required="required" size="45" maxlength="45"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td><form:label path="direccion.ciudad">Ciudad (*)</form:label></td>
        <td><form:input path="direccion.ciudad" required="required" size="45" maxlength="45"/></td>
        <td><form:label path="direccion.region">Región</form:label></td>
        <td><form:input path="direccion.region" size="45" maxlength="45"/></td>
    </tr>
    <tr>
        <td><form:label path="direccion.pais">País (*)</form:label></td>
        <td><form:input path="direccion.pais" required="required" size="45" maxlength="45"/></td>
        <td><form:label path="direccion.codigoPostal">C.P. (*)</form:label></td>
        <td><form:input path="direccion.codigoPostal" required="required"/></td>
    </tr>
    <tr>
        <td></td>
        <td><form:checkbox path="valida" label="Válida (dirección actual)"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td><form:label path="usuario.contrasena">Contraseña (*)</form:label></td>
        <td><form:password path="usuario.contrasena" required="required" size="45" maxlength="45"/></td>
        <td><form:label path="rcontrasena">Contraseña. Repetir (*)</form:label></td>
        <td><form:password path="rcontrasena" required="required" size="45" maxlength="45"/></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="submit" value="Registrar"/></td>
        <td><input type="reset" value="Cancelar" onclick="location.href='/'"></td>
        <td></td>
    </tr>
</table>
</form:form>
</body>
</html>