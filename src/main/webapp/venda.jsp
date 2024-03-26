<%--
  Created by IntelliJ IDEA.
  User: Marianna
  Date: 23/03/2024
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Vendas | Tonhão Autopeças</title>
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/paginasConteudo.css">
</head>
<body>
<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
</nav>
<main>

    <h1>Declarar Venda</h1>
    <div id="conteudo">
        <div id="div-form">
            <form action="venda" method="POST" class="form">
                <label class="texto-basico" for="data">Data:</label>
                <input class="input-form" type="date" id="data" name="data" required/>
                <label class="texto-basico" for="totalVenda">Total da venda:</label>
                <input class="input-form" type="number" id="totalVenda" name="totalVenda" required/>
                <label class="texto-basico" for="idCliente">Cliente:</label>
                <select class="campo-select input-form" id="idCliente" name="idCliente" required>
                    <option value="" selected>Selecione um cliente</option>
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>
                    </c:forEach>
                </select>
                <label for="idFuncionario">Funcionário:</label>
                <select class="campo-select input-form" id="idFuncionario" name="idFuncionario" required>
                    <option value="" selected>Selecione um funcionário</option>
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                    </c:forEach>
                </select>
                <input class="botao-padrao" type="submit" value="Cadastrar"/>
            </form>
        </div>
        <div id="div-listar">
            <form action="venda" method="GET" class="search-form">
                <label class="texto-basico" for="id">Buscar venda por id:</label>
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
                <c:forEach var="venda" items="${vendas}">
                    <div class="bloco-conteudo">
                        <div class="bloco-info-conteudo">
                            <p class="texto-basico">ID: ${venda.id}</p>
                            <p class="texto-basico">Data: ${venda.data}</p>
                            <p class="texto-basico">Total da venda: ${venda.totalVenda}</p>
                            <p class="texto-basico">Cliente: ${venda.cliente.id} - ${venda.cliente.nome}</p>
                            <p class="texto-basico">Funcionário: ${venda.funcionario.id} - ${venda.funcionario.nome}</p>
                        </div>
                        <div class="bloco-botoes">
                            <form id="botao-excluir" action="mvc" method="POST">
                                <input type="hidden" name="id" value="${venda.id}">
                                <input type="hidden" name="logica" value="DeletaVenda"/>
                                <button class="botao-padrao botao-listar" type="submit">Excluir</button>
                            </form>
                            <button class="botao-padrao botao-listar update-button">Atualizar</button>
                        </div>
                        <div class="info-atualizar">
                            <form id="form-atualizar" action="mvc" method="POST" class="update-div hide">
                                <div class="campo-atualizar">
                                    <label class="texto-basico" for="data-update">Data:</label>
                                    <input class="input-form" type="date" id="data-update" name="data" required/>
                                    <label class="texto-basico" for="total-update">Total:</label>
                                    <input class="input-form" type="number" id="total-update" name="totalVenda"
                                           required/>
                                <input type="hidden" name="id" value="${venda.id}">
                                <input type="hidden" name="logica" value="AtualizaVenda"/>
                                <input id="botao-atualizar" class="botao-padrao" type="submit" value="Atualizar"/>
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