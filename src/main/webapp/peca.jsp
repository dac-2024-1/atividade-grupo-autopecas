<%--
  Created by IntelliJ IDEA.
  User: gabriella
  Date: 22/03/2024
  Time: 22:16
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
    <title>Peças | Tonhão Autopeças</title>
</head>
<body>


<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>

<main>
    <h1>Cadastrar peças</h1>

    <div id="conteudo">

        <div id="div-form">

            <form action="peca" method="POST" class="form">
                <label class="texto-basico" for="nome">Nome:</label>
                <input class="input-form" type="text" id="nome" name="nome" required />
                <label class="texto-basico" for="descricao">Descrição:</label>
                <input class="input-form" type="text" id="descricao" name="descricao" required />
                <label class="texto-basico" for="preco">Preço:</label>
                <input class="input-form" type="text" id="preco" name="preco" required />
                <label class="texto-basico" for="preco">Quantidade em estoque:</label>
                <input class="input-form" type="text" id="quantidadeEstoque" name="quantidadeEstoque" required />
                <input class="botao-padrao" type="submit" value="Cadastrar" class="submit" />
            </form>

        </div>

        <div id="div-listar">

            <form action="peca" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar peça por id:</label>
                <div id="campo-busca">
                    <input class="input-form" id="id" type="text" placeholder="Buscar peça por id..." name="id">
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
                <c:forEach var="peca" items="${pecas}">
                <div class="bloco-conteudo">
                    <div class="bloco-info-conteudo">
                        <p class="texto-basico">ID: ${peca.id}</p>
                        <p class="texto-basico">Nome: ${peca.nome}</p>
                        <p class="texto-basico">Descrição: ${peca.descricao}</p>
                        <p class="texto-basico">Preço: ${peca.preco}</p>
                        <p class="texto-basico">Quantidade em estoque: ${peca.quantidadeEstoque}</p>
                    </div>
                    <div class="bloco-botoes">
                        <form id="botao-excluir" action="mvc" method="POST">
                            <input type="hidden" name="id" value="${peca.id}">
                            <input type="hidden" name="logica" value="DeletaPeca" />
                            <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                        </form>
                        <button class="botao-padrao botao-listar update-button">Atualizar</button>
                    </div>
                    <div class="info-atualizar">
                        <form id="form-atualizar" action="mvc" method="POST" class="update-div hide">
                            <div class="campo-atualizar">
                                <label for="preco-update">Preço:</label>
                                <input class="input" type="text" id="preco-update" name="preco" required />
                                <label for="quantidade-update">Quantidade em estoque:</label>
                                <input class="input" type="text" id="quantidade-update" name="quantidadeEstoque"
                                       required />
                                <input type="hidden" name="id" value="${peca.id}">
                                <input type="hidden" name="logica" value="AtualizaPeca" />
                                <input id="botao-atualizar" class="botao-padrao" type="submit" value="Atualizar"
                                       class="submit update" />
                            </div>
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
