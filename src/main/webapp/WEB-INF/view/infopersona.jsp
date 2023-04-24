<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw.bank.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: itsso
  Date: 23/04/2023
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%PersonaEntity persona = (PersonaEntity) request.getAttribute("personas");%>
<%ClienteEntity cliente = (ClienteEntity) request.getAttribute("clientes");%>
<%List<CuentaBancoEntity> listacuentas = (List<CuentaBancoEntity>) request.getAttribute("listacuentas");%>
<%List<TransaccionEntity> listatransa = (List<TransaccionEntity>) request.getAttribute("listatransa");%>


<html>
<head>
    <title>Datos Cliente</title>
</head>
<body>
<h1>Datos del cliente:</h1>
    <h2>Persona:</h2>
        <ul>
            <li>Nombre: <%=persona.getNombre()%></li>
            <li>Apellidos: <%=persona.getApellido1()%> <%=persona.getApellido2()%></li>
            <li>DNI: <%=persona.getDni()%></li>
            <li>Fecha Nacimiento: <%=persona.getFechaNacimiento()%></li>
            <li>Fecha de Inicio: <%=cliente.getFechaInicio()%></li>
            <li>Estado: <%=cliente.getEstadoClienteByEstadoClienteId().getTipo()%></li>
        </ul>

<h3>Movimientos:</h3>
<%for (CuentaBancoEntity c:listacuentas) {%>
<h3>Cuenta: <%=c.getIbanCuenta()%></h3>
    <table border="1">
        <tr>
            <th>Fecha Instrucción</th>
            <th>Fecha Ejecución</th>
            <th>Cantidad</th>
            <th>Cuenta Destino</th>
            <th>Tipo de Movimiento</th>
        </tr>
        <%for (TransaccionEntity trans: listatransa){
        if(trans.getCuentaBancoByCuentaOrigen().getId() == c.getId() || trans.getCuentaBancoByCuentaDestino().getId() == c.getId()) {%>
        <tr>
            <td style="text-align: center"><%=trans.getFechaInstruccion()%></td>
            <td style="text-align: center"><%=trans.getFechaEjecucion()%></td>
            <td style="text-align: center"><%=trans.getCantidad()%></td>
            <td style="text-align: center"><%=trans.getCuentaBancoByCuentaDestino().getIbanCuenta()%></td>
            <td style="text-align: center"><%if(c.getId() == trans.getCuentaBancoByCuentaOrigen().getId()){%>⇒<%}else{%>⇐<%}%></td>
        </tr>
        <%}
        }%>
    </table><br>
    <%}%>
</body>
</html>

