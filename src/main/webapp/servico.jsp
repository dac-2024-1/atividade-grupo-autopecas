<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Serviços | Tonhão Autopeças</title>
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
</head>
<body>

<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>

<main>
    <h1>Cadastrar serviço</h1>

    <div id="conteudo">

        <div id="div-form">
            <form action="servico" method="POST" class="form">
                <label class="texto-basico" for="descricao">Descrição:</label>
                <input class="input-form" type="text" id="descricao" name="descricao" required />
                <label class="texto-basico" for="preco">Preço:</label>
                <input class="input-form" type="text" id="preco" name="preco" required />
                <label class="texto-basico" for="data">Data:</label>
                <input class="input-form" type="date" id="data" name="data" required />
                <label class="texto-basico" for="idVeiculo">Veículo:</label>
                <select class="campo-select input-form" id="idVeiculo" name="idVeiculo" required>
                    <option value="" selected>Selecione um funcionário</option>
                    <c:forEach var="veiculo" items="${veiculos}">
                        <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca} ${veiculo.modelo} - Proprietário: ${veiculo.cliente.nome}</option>
                    </c:forEach>

                </select>
                <label class="texto-basico" for="idFuncionario">Funcionário:</label>
                <select class=" campo-select input-form" id="idFuncionario" name="idFuncionario" required>
                    <option value="" selected>Selecione um funcionário</option>
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                    </c:forEach>
                </select>
                <input class="botao-padrao" type="submit" value="Cadastrar" />
            </form>
        </div>

        <div id="div-listar">
            <form action="servico" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar serviço por id:</label>
                <div id="campo-busca">
                    <input class="input-form" id="id" type="text" placeholder="Buscar serviço por id..." name="id">
                    <button id="botao-buscar" class="search-button" type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                             fill="none" stroke="white" stroke-width="1.5" stroke-linecap="round"
                             stroke-linejoin="round" class="lucide lucide-search">
                            <circle cx="11" cy="11" r="8" />
                            <path d="m21 21-4.3-4.3" />
                        </svg>
                    </button>
                </div>
            </form>
            <div id="conteudo-listado">
                <c:forEach var="servico" items="${servicos}">
                <div class="bloco-conteudo">
                    <div class="bloco-info-conteudo">
                        <p class="texto-basico">ID: ${servico.id}</p>
                        <p class="texto-basico">Descrição: ${servico.descricao}</p>
                        <p class="texto-basico">Preço: ${servico.preco}</p>
                        <p class="texto-basico">Data: ${servico.data}</p>
                        <p class="texto-basico">Veiculo: ${servico.veiculo.id} - ${servico.veiculo.marca}
                            ${servico.veiculo.modelo} </p>
                        <p class="texto-basico">Funcionario: ${servico.funcionario.nome}</p>
                    </div>
                    <div class="bloco-botoes">
                        <form id="botao-excluir" action="mvc" method="POST">
                            <input type="hidden" name="id" value="${servico.id}">
                            <input type="hidden" name="logica" value="DeletaServico" />
                            <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                        </form>
                        <button class="botao-padrao botao-listar update-button">Atualizar</button>
                    </div>
                    <div class="info-atualizar">
                        <form id="form-atualizar" action="mvc" method="POST" class="update-div hide">

                            <div class="campo-atualizar">
                                <label class="texto-basico" for="descricao">Descrição:</label>
                                <input class="input-form" type="text" id="descricao" name="descricao" required />
                                <label class="texto-basico" for="preco">Preço:</label>
                                <input class="input-form" type="text" id="preco" name="preco" required />
                                <label class="texto-basico" for="data">Data:</label>
                                <input class="input-form" type="date" id="data" name="data" required />
                                <label class="texto-basico" for="idVeiculo">Veículo:</label>
                                <select class="campo-select input-form" id="idVeiculo" name="idVeiculo" required>
                                    <option value="" selected>Selecione um veículo:</option>
                                    <c:forEach var="veiculo" items="${veiculos}">
                                        <c:if test="${veiculo.id != servico.veiculo.id}">
                                            <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca}
                                                    ${veiculo.modelo} -
                                                Proprietário: ${veiculo.cliente.nome}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <label class="texto-basico" for="idFuncionario">Funcionário:</label>
                                <select class=" campo-select input-form" id="idFuncionario" name="idFuncionario"
                                        required>
                                    <option value="" selected>Selecione um funcionário</option>
                                    <c:forEach var="funcionario" items="${funcionarios}">
                                        <c:if test="${funcionario.id != servico.funcionario.id}">
                                            <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="id" value="${servico.id}">
                                <input type="hidden" name="logica" value="AtualizaCliente" />
                                <input id="botao-atualizar" class="botao-padrao" type="submit" value="Atualizar"
                                       class="submit update" />
                            </div>
                    </div>
                    </form>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </div>
</main>

<script src="script.js"></script>
</body>
</html>
