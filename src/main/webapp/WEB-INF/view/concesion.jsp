<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="es.uma.taw.bank.entity.DireccionEntity" %>
<%@ page import="es.uma.taw.bank.entity.EmpresaEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %><%--
  Created by IntelliJ IDEA.
  User: itsso
  Date: 24/04/2023
  Time: 17:51
  To change this template use File | Settings | File Templates.
  @author: David Castaños Benedicto
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");%>
<%EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");%>
<%PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");%>
<html>
<head>
    <title>Concesión</title>
</head>
<body>
<h1>Visto bueno de la cuenta</h1>
<%if (empresa != null && cliente.getId() == empresa.getId()) {%>
<ul>
    <li>Nombre: <%=empresa.getNombre()%>
    </li>
    <li>CIF: <%=empresa.getCif()%>
    </li>
</ul>
<%} else {%>
<ul>
    <li>Nombre: <%=persona.getNombre()%> <%=persona.getApellido1()%> <%=persona.getApellido2()%>
    </li>
    <li>DNI: <%=persona.getDni()%>
    </li>
    <li>Fecha Nacimiento: <%=persona.getFechaNacimiento()%>
    </li>
</ul>
<%}%>

<%
    for (DireccionEntity direccion : cliente.getDireccionsById()) {
        if (cliente.getId() == direccion.getClienteByClienteId().getId()) {
%>
<ul>
    <li>Calle: <%=direccion.getCalle()%>,<%=direccion.getNumero()%>
    </li>
    <li>Planta/Puerta: <%=direccion.getPlantaPuertaOficina()%>
    </li>
    <li>CP: <%=direccion.getCodigoPostal()%>
    </li>
    <li>Región: <%=direccion.getRegion()%>
    </li>
    <li>Ciudad: <%=direccion.getCiudad()%>
    </li>
    <li>País: <%=direccion.getPais()%>
    </li>
</ul>
<%
        }
    }
%>
<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="/gestor/peticioncuenta?id=<%=cliente.getId()%>">Aceptar</a>
<a>&nbsp;&nbsp;&nbsp;&nbsp;</a><a href="/gestor/rechazocuenta">Rechazar</a>
</body>
</html>
