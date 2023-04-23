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
<h1>Realizar una retirada en:</h1>
<form method="post" action="/cajero/retirarCambio">
    <input hidden="true" value="${cuenta.id}" name="cuenta">
    Cantidad en ${origen}:<input id="origen" type="number" name="cantidad" onkeyup="cambiar()">
    Cantidad en ${destino}:<input id="destino" type="numbre" value="${equivalencia}" disabled>
    <button>Retirar</button>
</form>
<script>
    function cambiar(){
        let origen = document.getElementById("origen").value;
        let destino = document.getElementById("destino");
        destino.value=origen*${equivalencia};
    }
</script>
</body>
</html>
