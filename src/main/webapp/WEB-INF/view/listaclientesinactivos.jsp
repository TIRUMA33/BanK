<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: itsso
  Date: 22/04/2023
  Time: 12:13
  To change this template use File | Settings | File Templates.
  @author: David Castaños Benedicto
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
<p>
    <button><a href="/gestor/" style="text-decoration: none"> Volver a Inicio</a></button>
</p>
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
                    <th>Gestionar</th>

                </tr>

                <%
                    for (PersonaEntity persona : listaPersonas) {
                %>
                <%
                    String estado="";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == persona.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();%>
                <tr>
                    <td><%=persona.getId()%></td>
                    <td><%=persona.getNombre()%> <%=persona.getApellido1()%> <%=persona.getApellido2()%></td>
                    <td><%=persona.getDni()%></td>
                    <td><%=persona.getFechaNacimiento().toString()%></td>
                    <td><%=estado%></td>
                    <td><a href="/gestor/infopersona?id=<%=persona.getId()%>">Info Avanzada</a></td>
                    <td><a href="/gestor/desactivarcliente?id=<%=persona.getId()%>"><%= (c.getEstadoClienteByEstadoClienteId().getId().equals(2)) ? "ACTIVAR" : "DESACTIVAR"%></a></td>
                </tr>
                <%
                        }
                    }
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
                    <th>Gestionar</th>
                </tr>
                <%
                    for (EmpresaEntity empresas : listaEmpresas) {
                %>


                <%
                    String estado="";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == empresas.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();%>
                <tr>
                    <td><%=empresas.getId()%></td>
                    <td><%=empresas.getNombre()%></td>
                    <td><%=empresas.getCif()%></td>
                    <td><%=estado%></td>
                    <td><a href="/gestor/infoempresa?id=<%=empresas.getId()%>">Info Avanzada</a></td>
                    <td><a href="/gestor/desactivarcliente?id=<%=empresas.getId()%>"><%= (c.getEstadoClienteByEstadoClienteId().getId().equals(2)) ? "ACTIVAR" : "DESACTIVAR"%></a></td>
                </tr>
                <%
                            }
                        }
                    }
                %>
        </table>
</table>
</body>
</html>