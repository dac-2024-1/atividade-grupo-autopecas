<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
    <title>Veiculo | Tonhão Autopeças</title>
</head>
<body>

<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>

<main>
    <h1>Cadastrar veiculo</h1>

    <div id="conteudo">

        <div id="div-form">

            <form action="veiculo" method="POST" class="form">
                <label class="texto-basico" for="marca">Marca:</label>
                <input class="input-form" type="text" id="marca" name="marca" required />
                <label class="texto-basico" for="modelo">Modelo:</label>
                <input class="input-form" type="text" id="modelo" name="modelo" required />
                <label class="texto-basico" for="ano">Ano:</label>
                <input class="input-form" type="text" id="ano" name="ano" required />
                <label class="texto-basico" for="placa">Placa:</label>
                <input class="input-form" type="text" id="placa" name="placa" required />
                <label class="texto-basico" for="idCliente">Cliente:</label>
                <select class="campo-select input-form" id="idCliente" name="idCliente" required>
                    <option value="" selected>Selecione um cliente</option>
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>
                    </c:forEach>
                </select>
                <input class="botao-padrao" type="submit" value="Cadastrar" class="submit" />
            </form>

        </div>

        <div id="div-listar">

            <form action="veiculo" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar veiculo por id:</label>
                <div id="campo-busca">
                    <input class="input-form" id="id" type="text" placeholder="Buscar veículo por id..." name="id">
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
                <c:forEach var="veiculo" items="${veiculos}">
                <div class="bloco-conteudo">
                    <div class="bloco-info-conteudo">
                        <p class="texto-basico">ID: ${veiculo.id}</p>
                        <p class="texto-basico">Marca: ${veiculo.marca}</p>
                        <p class="texto-basico">Modelo: ${veiculo.modelo}</p>
                        <p class="texto-basico">Ano: ${veiculo.ano}</p>
                        <p class="texto-basico">Placa: ${veiculo.placa}</p>
                        <p class="texto-basico">Cliente: ${veiculo.cliente.id} - ${veiculo.cliente.nome}</p>
                    </div>
                    <div class="bloco-botoes">
                        <form id="botao-excluir" action="mvc" method="POST">
                            <input type="hidden" name="id" value="${veiculo.id}">
                            <input type="hidden" name="logica" value="DeletaVeiculo" />
                            <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                        </form>
                        <button class="botao-padrao botao-listar update-button">Atualizar</button>
                    </div>
                    <div class="info-atualizar">
                        <form id="form-atualizar" action="mvc" method="POST" class="update-div hide">
                            <div class="campo-atualizar">
                                <label class="texto-basico" for="idCliente-4">Cliente:</label>
                                <select class="campo-select input-form" id="idCliente-4" name="idCliente" required>
                                    <option value="4" selected>4 - Mauricio Bernardo Dantas</option>
                                    <option value="3">3 - aa</option>
                                    <option value="5">5 - Vibes</option>
                                </select>
                                <input type="hidden" name="id" value="${veiculo.id}">
                                <input type="hidden" name="logica" value="AtualizaVeiculo" />
                                <input id="botao-atualizar" class="botao-padrao" type="submit" value="Atualizar" class="submit update" />
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