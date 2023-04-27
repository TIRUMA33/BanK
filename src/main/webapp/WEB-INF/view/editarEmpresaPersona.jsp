<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 25/04/2023
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>Editar información</h1>
<form:form action="/empresa/${registroEmpresaPersona.empresaPersona.empresaByIdEmpresa.id}/persona/${registroEmpresaPersona.empresaPersona.personaByIdPersona.id}/guardar"
           modelAttribute="registroEmpresaPersona" method="post">
    <h2>Datos personales</h2>
    <table>
        <tr>
            <td><form:label path="persona.dni">NIF (*)</form:label></td>
            <td><form:input path="persona.dni" size="9" maxlength="9" required="required"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="persona.nombre">Nombre (*)</form:label></td>
            <td><form:input path="persona.nombre" size="45" maxlength="45" required="required"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="persona.apellido1">Primer Apellido (*)</form:label></td>
            <td><form:input path="persona.apellido1" size="45" maxlength="45" required="required"/></td>
            <td><form:label path="persona.apellido2">Segundo Apellido</form:label></td>
            <td><form:input path="persona.apellido2" size="45" maxlength="45"/></td>
        </tr>
        <tr>
            <td><form:label path="persona.fechaNacimiento">Fecha nacimiento (*)</form:label></td>
            <td><form:input path="persona.fechaNacimiento" type="date" required="required"/></td>
            <td><form:label path="empresaPersona.tipoPersonaRelacionadaByIdTipo">Tipo (*)</form:label></td>
            <td><form:select path="empresaPersona.tipoPersonaRelacionadaByIdTipo" items="${tipoPersonasRelacionadas}"
                             itemLabel="tipo" itemValue="id" required="required"/></td>
        </tr>
    </table>
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
            <td></td>
            <td></td>
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
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="usuario.contrasena">Contraseña (*)</form:label></td>
            <td><form:password path="usuario.contrasena" size="45" maxlength="45"/></td>
            <td><form:label path="rcontrasena">Contraseña. Repetir (*)</form:label></td>
            <td><form:password path="rcontrasena" size="45" maxlength="45"/></td>
        </tr>
    </table>
    <input type="submit" value="Guardar cambios">
    <input type="reset" value="Cancelar" onclick="location.href='/'">
</form:form>
</body>
</html>
