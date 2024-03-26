<%--
  Created by IntelliJ IDEA.
  User: vrsm
  Date: 21/03/2024
  Time: 18:16
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
    <title>Login | Tonhão Autopeças</title>
</head>
<body>

<main>

    <h1>Login de Usuário</h1>

    <c:if test="${mensagem != null and !mensagem.trim().isEmpty()}">
        <p class="texto-basico">${mensagem}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/usuario/login" method="post">
        <label class="texto-basico" for="username">Nome de usuário:</label>
        <input class="input-form" type="text" id="username" name="username">
        <label class="texto-basico" for="password">Senha:</label>
        <input class="input-form" type="password" id="password" name="password">
        <input type="submit" class="botao-padrao" value="Login">
    </form>

    <a href="${pageContext.request.contextPath}/usuario/registro"><button class="botao-padrao">Registre-se</button></a>
    <%--@elvariable id="mensagem" type="java.lang.String"--%>
</main>
</body>
</html>
