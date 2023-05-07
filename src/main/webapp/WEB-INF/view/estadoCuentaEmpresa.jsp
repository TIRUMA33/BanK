<%@ page import="es.uma.taw.bank.entity.CuentaBancoEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: oscfd
  Date: 30/04/2023
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<CuentaBancoEntity> cuentas = (List<CuentaBancoEntity>) request.getAttribute("cuentas");
%>
<html>
<head>
    <title>BanK</title>
</head>
<body>
<h1>Cuentas</h1>
<form action="/empresa/${id}/cuentas" method="post">
    <table border="1">
        <tr>
            <th>IBAN</th>
            <th>SWIFT</th>
            <th>SALDO</th>
            <th>DIVISA</th>
            <th>ENTIDAD</th>
            <th>PAIS</th>
            <th>FECHA APERTURA</th>
            <th>FECHA CIERRE</th>
            <th>ESTADO</th>
        </tr>
        <%
            for (CuentaBancoEntity cuentaBanco : cuentas) {
        %>
        <tr>
            <td><%= cuentaBanco.getIbanCuenta() %>
            </td>
            <td><%= cuentaBanco.getSwift() %>
            </td>
            <td><%= cuentaBanco.getSaldo() %>
            </td>
            <td><%= cuentaBanco.getDivisaByDivisaId().getNombre() %>
            </td>
            <td><%= cuentaBanco.getEntidadBancariaByEntidadBancariaId().getNombre() %>
            </td>
            <td><%= cuentaBanco.getPais() %>
            </td>
            <td><%= cuentaBanco.getFechaApertura().toLocalDateTime().toLocalDate() %>
            </td>
            <td><%= cuentaBanco.getFechaCierre() == null ? "" : cuentaBanco.getFechaCierre() %>
            </td>
            <td>
                <%
                    if (cuentaBanco.getEstadoCuentaByEstadoCuentaId().getTipo().startsWith("Pendiente")) {
                %>
                <%= cuentaBanco.getEstadoCuentaByEstadoCuentaId().getTipo() %>
                <%
                } else {
                %>
                <a href="/empresa/${id}/cuenta/<%= cuentaBanco.getId() %>/permiso/<%= cuentaBanco.getId() %>"><%=
                cuentaBanco.getEstadoCuentaByEstadoCuentaId().getTipo() %>
                </a>
                <%
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>
</html>
