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
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <a href="${pageContext.request.contextPath}/usuario/registro">Registre-se</a>
    <%--@elvariable id="mensagem" type="java.lang.String"--%>
    <c:if test="${mensagem != null and !mensagem.trim().isEmpty()}">
        <p>${mensagem}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/usuario/login" method="post">
        <label for="username">Nome de usuário:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Senha:</label><br>
        <input type="password" id="password" name="password"><br>
        <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
