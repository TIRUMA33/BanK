<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 27/03/2023
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    int empresaId = (int) request.getAttribute("empresaId");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>GESTIÓN DE PERSONAS RELACIONADAS CON LA EMPRESA</h1>
<form:form action="/empresa/${empresaId}/persona/anadir" modelAttribute="empresaPersona" method="post">
    <form:hidden path="empresaPersona.empresaByIdEmpresa"/>
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
            <td><form:input path="persona.nombre" size="45" maxlength="45"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="persona.apellido1">Primer Apellido (*)</form:label></td>
            <td><form:input path="persona.apellido1" size="45" maxlength="45"/></td>
            <td><form:label path="persona.apellido2">Segundo Apellido</form:label></td>
            <td><form:input path="persona.apellido2" size="45" maxlength="45"/></td>
        </tr>
        <tr>
            <td><form:label path="fechaNacimiento">Fecha nacimiento (*)</form:label></td>
            <td><form:input path="fechaNacimiento" type="date"/></td>
            <td><form:label path="empresaPersona.tipoPersonaRelacionadaByIdTipo">Tipo (*)</form:label></td>
            <td><form:select path="empresaPersona.tipoPersonaRelacionadaByIdTipo" items="${tipoPersonasRelacionadas}"
                             itemLabel="tipo" itemValue="id"/></td>
        </tr>
    </table>
    <h2>Dirección</h2>
    <table>
        <tr>
            <td><form:label path="direccion.calle">Calle (*)</form:label></td>
            <td><form:input path="direccion.calle" size="45" maxlength="45"/></td>
            <td><form:label path="direccion.numero">Número (*)</form:label></td>
            <td><form:input path="direccion.numero"/></td>
        </tr>
        <tr>
            <td><form:label path="direccion.plantaPuertaOficina">Planta/Puerta/Oficina (*)</form:label></td>
            <td><form:input path="direccion.plantaPuertaOficina" size="45" maxlength="45"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="direccion.ciudad">Ciudad (*)</form:label></td>
            <td><form:input path="direccion.ciudad" size="45" maxlength="45"/></td>
            <td><form:label path="direccion.region">Región</form:label></td>
            <td><form:input path="direccion.region" size="45" maxlength="45"/></td>
        </tr>
        <tr>
            <td><form:label path="direccion.pais">País (*)</form:label></td>
            <td><form:input path="direccion.pais" size="45" maxlength="45"/></td>
            <td><form:label path="direccion.codigoPostal">C.P. (*)</form:label></td>
            <td><form:input path="direccion.codigoPostal"/></td>
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
        <tr>
            <td></td>
            <td><input type="submit" value="Añadir"/></td>
            <td><input type="reset" value="Cancelar"/></td>
            <td></td>
        </tr>
    </table>
    <br/>
</form:form>
<jsp:include page="listaEmpresaPersonas.jsp">
    <jsp:param name="personas" value="${personas}"/>
</jsp:include>
</body>
</html>
