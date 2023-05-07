<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 22/04/2023
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retirada</title>
</head>
<body>
<a href="/cajero/cuenta?cuenta=${cuenta.id}">Volver</a>
<h1>Realizar una retirada:</h1>
<form method="post" action="/cajero/retirar" onsubmit="comprobarSaldo()">
    <h3>Saldo disponible: <span style="color: ${cuenta.saldo<0?"crimson" : "black"}">${cuenta.saldo}</span> ${cuenta.divisaNombre}</h3>
    <input hidden="true" value="${cuenta.id}" name="cuenta">
    Cantidad:<input id="cantidad" type="number" name="cantidad" required="required">
    <button id="boton">Retirar</button>
</form>
<script>
    function comprobarSaldo(){
        let saldo = ${cuenta.saldo};
        let a = document.getElementById("cantidad").value;
        let boton = document.getElementById("boton");
        const para = document.createElement("p");
        para.innerText = "No tienes saldo suficiente";
        para.id="no";

        if(a>=saldo){
            event.preventDefault();
            if(document.getElementById("no")===null)document.body.appendChild(para);
        }
    }
</script>
</body>
</html>
