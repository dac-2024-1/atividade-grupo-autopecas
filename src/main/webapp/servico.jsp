<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
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
            <label for="descricao">Descrição:</label>
            <input class="input" type="text" id="descricao" name="descricao" required/><br/><br/>
            <label for="preco">Preço:</label>
            <input class="input" type="text" id="preco" name="preco" required/><br/><br/>
            <label for="data">Data:</label>
            <input class="input" type="date" id="data" name="data" required/><br/><br/>
            <label for="idVeiculo">Veículo:</label>
            <select class="input" id="idVeiculo" name="idVeiculo" required>
                <option value="" selected>Selecione um veículo</option>
                <c:forEach var="veiculo" items="${veiculos}">
                    <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca} ${veiculo.modelo} - Proprietário: ${veiculo.cliente.nome}</option>
                </c:forEach>
            </select><br/><br/>
            <label for="idFuncionario">Funcionário:</label>
            <select class="input" id="idFuncionario" name="idFuncionario" required>
                <option value="" selected>Selecione um funcionário</option>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                </c:forEach>
            </select><br/><br/>
            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="servico" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar serviço por id..." name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="servico" items="${servicos}">
                <div class="block">
                    <div style="display: flex; width: 100%; justify-content: space-between">
                        <div>
                            <p>ID: ${servico.id}</p>
                            <p>Descrição: ${servico.descricao}</p>
                            <p>Preço: ${servico.preco}</p>
                            <p>Data: ${servico.data}</p>
                            <p>Veiculo: ${servico.veiculo.id} - ${servico.veiculo.marca} ${servico.veiculo.modelo} </p>
                            <p>Funcionario: ${servico.funcionario.nome}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script src="script.js"></script>
</body>
</html>
