<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.ConversacionEntity" %>
<%@ page import="es.uma.taw.bank.dto.ConversacionDTO" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 04/05/2023
  Time: 18:13
  To change this template use File | Settings | File Templates.
  //Autor Pablo Robles Mansilla
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de conversaciones</title>
</head>
<body>
<%
  List<ConversacionDTO> conversaciones = (List<ConversacionDTO>) request.getAttribute("conversaciones");
%>

<jsp:include page="cabecera.jsp"/>

<h1>Bienvenido</h1>
<h2>Lista de conversaciones almacenadas</h2>

<form:form action="/asistencia/filtrar" method="post" modelAttribute="filtro">
  Buscar por: <br/>
  DNI/NIF: <form:input path="dni"/>
  Estado de la conversación:
  <form:select path="estado">
    <form:option value=""></form:option>
    <form:option value="0">En curso</form:option>
    <form:option value="1">Terminada</form:option>
  </form:select> <br/>
  Ordenar por: <br/>
  <form:checkbox path="pendientes" label="Estado"/>
  <form:checkbox path="fecha" label="Fecha de creación"/>
  <br/>
  
  <button>Filtrar</button>
</form:form>

<%
  if(conversaciones != null && !conversaciones.isEmpty()) {
%>

<table border="1">
  <tr>
    <th>Cliente</th>
    <th>Estado</th>
    <th>Fecha de creación</th>
  </tr>
  <%
    for(ConversacionDTO conversacion : conversaciones) {
  %>
  <tr>
    <td>
      <a href="/asistencia/mensajes?id=<%=conversacion.getEmisor() %>"><%= conversacion.getEmisorNif() %></a>
    </td>
    <%
      String status = "Terminada";
    if(conversacion.getTerminada() == 0) {
      status = "En curso";
    %>
    <td>
      <a href="/asistencia/asistir?id=<%=conversacion.getId() %>"><%=status%></a>
    </td>
    <%
      }else{
    %>
    <td><%=status%></td>
    <%
        }
    %>
    <td><%= conversacion.getFechaCreacion() %></td>
    <%
        }
    %>
  </tr>
</table>
<%
    }else{
%>
<p>No hay conversaciones almacenadas</p>
<%
  }
%>


</body>
</html>
