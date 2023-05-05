<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.*" %><%--
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
<%List<CuentaSospechosaEntity> listasospechosos = (List<CuentaSospechosaEntity>) request.getAttribute("listasospechosos");%>

<html>
<head>
    <title>Gestor</title>
</head>
<body>

<h1>LISTA CLIENTES</h1>
<p>⚠ = Tiene alguna acción pendiente de realizar</p>
<p>☢ = Ha realizado alguna acción con cuenta sospechosa</p>
<p>
    <button><a href="/gestor/" style="text-decoration: none"> Volver a Inicio</a></button>
</p>
<form:form action="/gestor/lista/filtrar" method="post" modelAttribute="filtro">
    Ordenar por: <br/>
    Estado: <form:checkbox path="estado"/>
    Nombre:<form:checkbox path="nombre"/>
    <button>Ordenar</button>
</form:form>
<table>
    <tr>
        <td style="vertical-align:top;">
            <h2>Personas</h2>
            <table border="0">
                <tr>
                    <th style="border: black; border-style: solid; border-width: 1px">Id</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Nombre</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Identificador</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Fecha Nacimiento</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Estado</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Info</th>
                    <th style="border: 0"></th>
                </tr>

                <%
                    for (PersonaEntity persona : listaPersonas) {
                %>
                <%
                    Boolean pendiente = false;
                    Boolean cuentasospechosa = false;
                    String estado="";
                    String result="";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == persona.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();

                            for (CuentaBancoEntity cuenta:c.getCuentaBancosById()) {
                                if(cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 4 || cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 5){
                                    pendiente = true;
                                }
                                for (CuentaSospechosaEntity sospechosa:listasospechosos) {
                                    for (TransaccionEntity transaccionorigen: cuenta.getTransaccionsById()) {
                                        if(transaccionorigen.getCuentaBancoByCuentaDestino().getId().equals(sospechosa.getId())){
                                            cuentasospechosa = true;
                                        }
                                    }
                                }
                            }
                        }
                    }%>
                <%if(pendiente== true && cuentasospechosa == false){
                    result = "⚠";
                }else if(pendiente == true && cuentasospechosa == true){
                    result = "⚠☢";
                }else if(pendiente == false && cuentasospechosa == true){
                    result = "☢";
                }else{
                    result = "";
                }%>

                <tr>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=persona.getId()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=persona.getNombre()%> <%=persona.getApellido1()%> <%=persona.getApellido2()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=persona.getDni()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=persona.getFechaNacimiento().toString()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=estado%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><a href="/gestor/infopersona?id=<%=persona.getId()%>">Info Avanzada</a></td>
                    <td style="border: 0"><%=result%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>
        <td style="vertical-align:top;">
            <h2>Empresas</h2>
            <table border="0">
                <tr>
                    <th style="border: black; border-style: solid; border-width: 1px">Id</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Nombre</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Identificador</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Estado</th>
                    <th style="border: black; border-style: solid; border-width: 1px">Info</th>
                    <th style="border: 0"></th>
                </tr>
                <%
                    for (EmpresaEntity empresas : listaEmpresas) {
                %>


                <%
                    Boolean pendiente = false;
                    Boolean cuentasospechosa = false;
                    String estado="";
                    String result= "";
                    for (ClienteEntity c:listaclientes) {
                        if(c.getId() == empresas.getId()){
                            estado = c.getEstadoClienteByEstadoClienteId().getTipo();
                            for (CuentaBancoEntity cuenta:c.getCuentaBancosById()) {
                                if(cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 4 || cuenta.getEstadoCuentaByEstadoCuentaId().getId() == 5){
                                    pendiente = true;
                                }
                                for (CuentaSospechosaEntity sospechosa:listasospechosos) {
                                    for (TransaccionEntity transaccionorigen: cuenta.getTransaccionsById()) {
                                        if(transaccionorigen.getCuentaBancoByCuentaDestino().getId().equals(sospechosa.getId())){
                                            cuentasospechosa = true;
                                        }
                                    }
                                }
                            }
                        }
                }%>
                <%if(pendiente== true && cuentasospechosa == false){
                    result = "⚠";
                }else if(pendiente == true && cuentasospechosa == true){
                    result = "⚠☢";
                }else if(pendiente == false && cuentasospechosa == true){
                    result = "☢";
                }else{
                    result = "";
                }%>



                <tr>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=empresas.getId()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=empresas.getNombre()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=empresas.getCif()%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><%=estado%></td>
                    <td style="border: black; border-style: solid; border-width: 1px"><a href="/gestor/infoempresa?id=<%=empresas.getId()%>">Info Avanzada</a></td>
                    <td style="border: 0"><%=result%></td>
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