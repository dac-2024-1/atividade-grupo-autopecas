<%--
  Created by IntelliJ IDEA.
  User: vrsm
  Date: 21/03/2024
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/style/base.css">
    <link rel="stylesheet" href="/style/loginRegistro.css">
    <link rel="stylesheet" href="/style/registro.css">
    <title>Registro | Tonhão Autopeças</title>
</head>
<body>

<main>
    <h1>Registro de Usuário</h1>

    <%--@elvariable id="mensagem" type="java.lang.String"--%>
    <c:if test="${mensagem != null and !mensagem.trim().isEmpty()}">
        <p class="texto-basico">
            Falha ao registrar usuário. ${mensagem}
        </p>
    </c:if>

    <div id="conteudo">

        <div id="div-form">
            <form action="${pageContext.request.contextPath}/usuario/registro" method="POST">
                <label class="texto-basico" for="username">Nome de usuário:</label>
                <input class="input-form" type="text" id="username" name="username">
                <label class="texto-basico" for="password">Senha:</label>
                <input class="input-form" type="password" id="password" name="password">
                <label class="texto-basico" for="confirmaSenha">Confrime sua senha:</label>
                <input class="input-form" type="password" id="confirmaSenha" name="confirmaSenha">
                <c:forEach var="funcionario" items="${funcionarios}">
                    <label class="texto-basico" for="lista-funcionarios">Selecione o funcionario:</label>
                    <div id="lista-funcionarios">
                        <div class="bloco-funcionario">
                            <p class="info-funcionario">ID: ${funcionario.id}</p>
                            <p class="info-funcionario">Nome: ${funcionario.nome}</p>
                            <p class="info-funcionario">Cargo: ${funcionario.cargo}</p>
                            <div id="selecionar-funcionario">
                                <label class="texto-basico" for="idFuncionario">Selecionar</label>
                                <input type="radio" id="idFuncionario" name="idFuncionario" value="${funcionario.id}">
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div id="botoes">
                    <input class="botao-padrao" type="submit" value="Registrar">
                    <a href="${pageContext.request.contextPath}/usuario/login">
                        <div id="botao-login" class="botao-padrao">Login</div>
                    </a>
                </div>

            </form>
        </div>

        <%--@elvariable id="funcionarios" type="List<br.com.autopecas.projetogrupo.entidades.Funcionario>"--%>
        <c:if test="${!funcionarios.isEmpty()}">
            <div id="div-filtro">

                <form action="${pageContext.request.contextPath}/usuario/registro/funcionario" method="GET"
                      class="search-form">
                    <label class="texto-basico" for="id">Buscar funcionário por id:</label>
                    <div id="campo-busca">
                        <input class="input-form" id="id" type="text" class="search-input"
                               placeholder="Buscar funcionario por id..." name="id">
                        <button id="botao-buscar" class="search-button" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                                 fill="none"
                                 stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                                 class="lucide lucide-search">
                                <circle cx="11" cy="11" r="8"/>
                                <path d="m21 21-4.3-4.3"/>
                            </svg>
                        </button>
                    </div>
                </form>
            </div>
        </c:if>

    </div>
</main>
</body>
</html>
