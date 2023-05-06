<%--
  Created by IntelliJ IDEA.
  User: aleja
  Date: 23/04/2023
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retira tu dinero en otra divisa</title>
</head>
<body>
<a href="/cajero/cuenta?cuenta=${cuenta.id}">Volver</a><br>
<a href="/cajero/cambioDivisa?cuenta=${cuenta.id}">Seleccionar otra divisa</a>
<h1>Realizar una retirada en:</h1>
<form method="post" action="/cajero/retirarCambio" onsubmit="comprobarSaldo()">
    <h3>Saldo disponible: <span style="color: ${cuenta.saldo<0?"crimson" : "black"}">${cuenta.saldo}</span> ${cuenta.divisa}</h3>
    <input hidden="true" value="${cuenta.id}" name="cuenta">
    Cantidad en ${origen}:<input id="origen" type="number" name="cantidad" onkeyup="cambiar()" required="required">
    Cantidad en ${destino}:<input id="destino" type="number" disabled>
    <button id="boton">Retirar</button>
</form>
<script>
    function cambiar(){
        let origen = document.getElementById("origen").value;
        let destino = document.getElementById("destino");
        if(origen!==null && origen!=="")destino.value=origen*${equivalencia};
        else destino.value="";
    }

        function comprobarSaldo(){
            let saldo = ${cuenta.saldo};
            let a = document.getElementById("origen").value;
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
