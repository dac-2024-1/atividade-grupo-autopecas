<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>Clientes | Tonhão Autopeças</title>
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
</head>
<body>


<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>

<main>

    <h1>Cadastrar cliente</h1>

    <div id="conteudo">
        <div id="div-form">
            <form action="cliente" method="POST" class="form">
                <label class="texto-basico" for="nome">Nome:</label>
                <input class="input-form" type="text" id="nome" name="nome" required />
                <label class="texto-basico" for="endereco">Endereço:</label>
                <input class="input-form" type="text" id="endereco" name="endereco" required />
                <label class="texto-basico" for="telefone">Telefone:</label>
                <input class="input-form" type="text" id="telefone" name="telefone" required />
                <input class="botao-padrao" type="submit" value="Cadastrar" class="submit" />
            </form>
        </div>
        <div id="div-listar">
            <form action="cliente" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar cliente por id:</label>
                <div id="campo-busca">
                    <input class="input-form" id="id" type="text" placeholder="Buscar cliente por id..." name="id">
                    <button id="botao-buscar" class="search-button" type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                             stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                             class="lucide lucide-search">
                            <circle cx="11" cy="11" r="8" />
                            <path d="m21 21-4.3-4.3" />
                        </svg>
                    </button>
                </div>
            </form>

            <div id="conteudo-listado">
            <c:forEach var="cliente" items="${clientes}">
                <div class="bloco-conteudo">
                    <div class="bloco-info-conteudo">
                        <p class="texto-basico">ID: ${cliente.id}</p>
                        <p class="texto-basico">Nome: ${cliente.nome}</p>
                        <p class="texto-basico">Endereço: ${cliente.endereco}</p>
                        <p class="texto-basico">Telefone: ${cliente.telefone}</p>
                    </div>
                    <div class="bloco-botoes">
                        <form id="botao-excluir" action="mvc" method="POST">
                            <input type="hidden" name="id" value="${cliente.id}">
                            <input type="hidden" name="logica" value="DeletaVeiculo" />
                            <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                        </form>
                        <button class="botao-padrao botao-listar update-button">Atualizar</button>
                    </div>
                    <div class="info-atualizar">
                        <form id="form-atualizar" action="mvc" method="POST" class="update-div hide">
                            <div class="campo-atualizar">
                                <label class="texto-basico" for="endereco-update">Endereço:</label>
                                <input class="input-form" type="text" id="endereco-update" name="endereco" required />
                                <label class="texto-basico" for="telefone-update">Telefone:</label>
                                <input class="input-form" type="text" id="telefone-update" name="telefone" required />
                            </div>
                            <input type="hidden" name="id" value="${cliente.id}">
                            <input type="hidden" name="logica" value="AtualizaCliente" />
                            <input id="botao-atualizar" class="botao-padrao" type="submit" value="Atualizar" class="submit update" />
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