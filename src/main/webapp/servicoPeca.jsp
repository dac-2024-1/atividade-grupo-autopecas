<%--
  Created by IntelliJ IDEA.
  User: Marianna
  Date: 25/03/2024
  Time: 07:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
    <title>Peças usadas em Serviços | Tonhão Autopeças</title>>
</head>
<body>
<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>

<main>

    <h1>Cadastrar Peças usadas em Serviços</h1>

    <div id="conteudo">

        <div id="div-form">

            <form action="servicoPeca" method="POST" class="form">
                <label class="texto-basico" for="idPeca">Peça:</label>
                <select class="campo-select input-form" id="idPeca" name="idPeca" required>
                    <option value="" selected>Selecione uma peça</option>
                    <c:forEach var="peca" items="${pecas}">
                        <option value="${peca.id}">${peca.id} - Descrição: ${peca.descricao} | ${peca.preco} R$ |
                                ${peca.quantidadeEstoque} unidades</option>
                    </c:forEach>
                </select>
                <label class="texto-basico" for="idServico">Serviço:</label>
                <select class="campo-select input-form" id="idServico" name="idServico" required>
                    <option value="" selected>Selecione um serviço</option>
                    <c:forEach var="servico" items="${servicos}">
                        <option value="${servico.id}">${servico.id} - Descrição: ${servico.descricao} |
                                ${servico.preco}
                            R$ </option>
                    </c:forEach>
                </select>
                <input class="botao-padrao" type="submit" value="Cadastrar" class="submit" />
            </form>

        </div>

        <div id="div-listar">

            <form action="servicoPeca" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar por id:</label>
                <div id="campo-busca">
                    <input class="input-form" id="id" type="text"
                           placeholder="Buscar peças usadas em serviço por id..." name="id">
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
                <c:forEach var="servicoPeca" items="${servicospecas}">

                    <div class="bloco-conteudo">
                        <div class="bloco-info-conteudo">
                            <p class="texto-basico">ID: ${servicoPeca.id}</p>
                            <p class="texto-basico">Id peça: ${servicoPeca.peca.id}</p>
                            <p class="texto-basico">Nome da Peça: ${servicoPeca.peca.nome}</p>
                            <p class="texto-basico">Descrição da Peça: ${servicoPeca.peca.descricao}</p>
                            <p class="texto-basico">Id serviço: ${servicoPeca.servico.id}</p>
                            <p class="texto-basico">Descrição do Serviço: ${servicoPeca.servico.descricao}</p>
                        </div>
                        <div class="bloco-botoes">
                            <form id="botao-excluir" action="mvc" method="POST">
                                <input type="hidden" name="id" value="${servicoPeca.id}">
                                <input type="hidden" name="logica" value="DeletaServicoPeca" />
                                <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<script src="script.js"></script>
</body>
</html>
