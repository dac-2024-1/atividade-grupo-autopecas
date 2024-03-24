<%--
  Created by IntelliJ IDEA.
  User: gabriella
  Date: 23/03/2024
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
    <meta charset="UTF-8">
    <title>Serviços</title>
    <link rel="stylesheet" href="styleCss.css">
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar serviço</h1>
        <form action="servico" method="POST" class="form">
            <label for="descricao">descrição:</label>
            <input class="input" type="text" id="descricao" name="descricao" required/>
            <label for="preco">Preço:</label>
            <input class="input" type="text" id="preco" name="preco" required/>
            <label for="data">Data:</label>
            <input class="input" type="date" id="data" name="data" required/>
            <label for="idVeiculo">Veiculo:</label>
            <select class="input" id="idVeiculo" name="idVeiculo" required>
                <option value="" selected>Selecione um veículo</option>
                <c:forEach var="veiculo" items="${veiculos}">
                    <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca} ${veiculo.modelo} - Proprietário: ${veiculo.cliente.nome}</option>
                </c:forEach>
            </select><br/><br/>
            <label for="idFuncionario">Funcionario:</label>
            <select class="input" id="idFuncionario" name="idFuncionario" required>
                <option value="" selected>Selecione um funcionário</option>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                </c:forEach>
            </select><br/><br/>

            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
</main>
<script src="script.js"></script>
</body>
</html>
