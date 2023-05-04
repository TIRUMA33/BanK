<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.ConversacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: probl
  Date: 04/05/2023
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de conversaciones</title>
</head>
<body>
<%
  List<ConversacionEntity> conversaciones = (List<ConversacionEntity>) request.getAttribute("conversaciones");
%>

<jsp:include page="cabecera.jsp"/>

<h1>Listado de conversaciones</h1>

<%
  if(conversaciones != null && !conversaciones.isEmpty()) {
%>

<table border="1">
  <tr>
    <th>Cliente</th>
    <th>Estado</th>
  </tr>
  <%
    for(ConversacionEntity conversacion : conversaciones) {
  %>
  <tr>
    <td>
      <a href="/asistencia/mensajes?id=<%=conversacion.getUsuarioByEmisor().getId() %>"><%= conversacion.getUsuarioByEmisor().getNif() %></a>
    </td>
    <%
      String status = "Terminada";
    if(conversacion.getTerminada() == 0) {
      status = "En curso";
    %>
    <td>
      <a href="/asistencia/chat?id=<%=conversacion.getId() %>"><%=status%></a>
    </td>
    <%
      }else{
    %>
    <td><%=status%></td>
    <%
      }
    %>
  </tr>
  <%
    }
    }
  %>
</table>


</body>
</html>
