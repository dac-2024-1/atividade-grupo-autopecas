<%--
  Created by IntelliJ IDEA.
  User: Marianna
  Date: 24/03/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>Venda de Peças | Tonhão Autopeças</title>
        <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
    </head>
</head>
<body>
<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>
<main >
    <h1>Declarar Venda</h1>
    <div id="conteudo">
        <div id="div-form"> 
            <form action="/vendaPeca" method="POST" class="form">
                <label for="idVenda" class="texto-basico">Venda:</label>
                <select class="campo-select input-form" id="idVenda" name="idVenda" required>
                    <option value="" selected>Selecione uma venda</option>
                    <c:forEach var="venda" items="${vendas}">
                        <option value="${venda.id}">Funcionário responsável: ${venda.funcionario.nome}
                            - Cliente: ${venda.cliente.nome}</option>
                    </c:forEach>
                </select>
                <label for="idPeca" class="texto-basico">Peça:</label>
                <select class="campo-select input-form" id="idPeca" name="idPeca" required>
                    <option value="" selected>Selecione uma peça</option>
                    <c:forEach var="peca" items="${pecas}">
                        <option value="${peca.id}">${peca.id} - Descrição: ${peca.descricao} | ${peca.preco} R$ | ${peca.quantidadeEstoque} unidades</option>
                    </c:forEach>
                </select>
                <input class="botao-padrao" type="submit" value="Cadastrar"/>
            </form>
        </div>
    <div id="div-listar">
            <form action="vendaPeca" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar venda de peça por id:</label>
                <div id="campo-busca">
                <input type="text" class="input-form" id="id" placeholder="Buscar venda por id..." name="id">
                <button id="botao-buscar" class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
                </div>
            </form>
            <div id="conteudo-listado">
                <c:forEach var="vendaPeca" items="${vendaPecas}">
                    <div class="bloco-conteudo">
                            <div class="bloco-info-conteudo">
                                <p class="texto-basico">ID: ${vendaPeca.id}</p>
                                <p class="texto-basico">ID da Venda: ${vendaPeca.venda.id}</p>
                                <p class="texto-basico">Data da venda: ${vendaPeca.venda.data}</p>
                                <p class="texto-basico">Valor total da venda: ${vendaPeca.venda.totalVenda}</p>
                                <p class="texto-basico">ID da Peça: ${vendaPeca.peca.id}</p>
                                <p class="texto-basico">Nome da peça: ${vendaPeca.peca.nome}</p>
                                <p class="texto-basico">Descrição da peça: ${vendaPeca.peca.descricao}</p>
                            </div>
                            <div class="bloco-botoes">
                                <form id="botao-excluir" action="mvc" method="POST">
                                    <input type="hidden" name="id" value="${venda.id}">
                                    <input type="hidden" name="logica" value="DeletaVenda"/>
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

