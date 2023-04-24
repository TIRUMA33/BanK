<%@ page import="es.uma.taw.bank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.EmpresaEntity" %>
<%@ page import="es.uma.taw.bank.entity.PersonaEntity" %>
<%@ page import="es.uma.taw.bank.entity.EstadoClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: itsso
  Date: 22/04/2023
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<ClienteEntity> listaclientes = (List<ClienteEntity>) request.getAttribute("listaclientes");%>
<%List<EmpresaEntity> listaEmpresas = (List<EmpresaEntity>) request.getAttribute("listaempresas");%>
<%List<PersonaEntity> listaPersonas = (List<PersonaEntity>) request.getAttribute("listapersonas");%>

<html>
<head>
    <title>Gestor</title>
</head>
<body>
<h1>LISTA CLIENTES</h1>

<table>
    <tr>
        <td>
            <h2>Personas</h2>
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Identificador</th>
                    <th>Fecha Nacimiento</th>
                    <th>Estado</th>
                    <th>Info</th>

                </tr>

                <%
                    for (PersonaEntity persona : listaPersonas) {
                %>
                <%
                    String estado="";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == persona.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();
                        }
                    }%>
                <tr>
                    <td><%=persona.getId()%></td>
                    <td><%=persona.getNombre()%> <%=persona.getApellido1()%> <%=persona.getApellido2()%></td>
                    <td><%=persona.getDni()%></td>
                    <td><%=persona.getFechaNacimiento().toString()%></td>
                    <td><%=estado%></td>
                    <td><a href="/gestor/infopersona?id=<%=persona.getId()%>">Info Avanzada</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>

        <td>
            <h2>Empresas</h2>
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Identificador</th>
                    <th>Estado</th>
                    <th>Info</th>
                </tr>
                <%
                    for (EmpresaEntity empresas : listaEmpresas) {
                %>


                <%
                    String estado="";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == empresas.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();
                        }
                }%>



                <tr>
                    <td><%=empresas.getId()%></td>
                    <td><%=empresas.getNombre()%></td>
                    <td><%=empresas.getCif()%></td>
                    <td><%=estado%></td>
                    <td><a href="/gestor/infoempresa?id=<%=empresas.getId()%>">Info Avanzada</a></td>
                </tr>
                <%
                    }
                %>

                </td>
            </tr>
            </table>
</table>
</body>
</html>