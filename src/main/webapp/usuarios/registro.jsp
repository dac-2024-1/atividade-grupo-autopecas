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
    <c:if test="${mensagem != null and !mensagem.trim().isEmpty()}">
        <p style="color: red;">
            Falha ao registrar usuário. ${mensagem}
        </p>
    </c:if>
    <form action="registraUsuario" method="POST">
        <label for="username">Nome de usuário:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Senha:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="confirmaSenha">Confrime sua senha:</label><br>
        <input type="password" id="confirmaSenha" name="confirmaSenha"><br>
        <br>
        <input type="submit" value="Registrar">
    </form>
</body>
</html>
