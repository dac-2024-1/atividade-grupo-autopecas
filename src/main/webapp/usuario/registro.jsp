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
    <title>Registro de Usuário</title>
</head>
<body>
<h1>Registro de Usuário</h1>
<a href="${pageContext.request.contextPath}/usuario/login">Login</a>
<%--@elvariable id="mensagem" type="java.lang.String"--%>
<c:if test="${mensagem != null and !mensagem.trim().isEmpty()}">
    <p style="color: red;">
        Falha ao registrar usuário. ${mensagem}
    </p>
</c:if>
<div style="display: flex; flex-direction: row">
    <form action="${pageContext.request.contextPath}/usuario/registro" method="POST">
        <label for="username">Nome de usuário:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Senha:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="confirmaSenha">Confrime sua senha:</label><br>
        <input type="password" id="confirmaSenha" name="confirmaSenha"> <br>
        <div class="content" id="col2">
            <div>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <div class="block">
                        <div style="display: flex; width: 100%; justify-content: space-between">
                            <div style=" border: solid">
                                <p>ID: ${funcionario.id}</p>
                                <p>Nome: ${funcionario.nome}</p>
                                <p>Cargo: ${funcionario.cargo}</p>
                                <label for="idFuncionario">Selecionar</label>
                                <input type="radio" id="idFuncionario" name="idFuncionario" value="${funcionario.id}">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <br>
        <br>
        <input type="submit" value="Registrar">
    </form>
    <%--@elvariable id="funcionarios" type="List<br.com.autopecas.projetogrupo.entidades.Funcionario>"--%>
    <c:if test="${!funcionarios.isEmpty()}">
        <form action="${pageContext.request.contextPath}/usuario/registro/funcionario" method="GET" class="search-form">
            <label for="id">Buscar funcionário por id:</label><br>
            <input id="id" type="text" class="search-input" placeholder="Buscar funcionario por id..." name="id">
            <button class="search-button" type="submit">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                     stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                     class="lucide lucide-search">
                    <circle cx="11" cy="11" r="8"/>
                    <path d="m21 21-4.3-4.3"/>
                </svg>
            </button>
        </form>
    </c:if>
</div>
</body>
</html>
