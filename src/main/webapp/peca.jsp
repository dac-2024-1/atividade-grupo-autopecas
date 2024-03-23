<%--
  Created by IntelliJ IDEA.
  User: gabriella
  Date: 22/03/2024
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>Peças</title>
        <link rel="stylesheet" href="styleCss.css">
    </head>
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar peça</h1>
        <form action="peca" method="POST" class="form">
            <label for="nome">Nome:</label>
            <input class="input" type="text" id="nome" name="nome" required/>
            <label for="descricao">Descrição:</label>
            <input class="input" type="text" id="descricao" name="descricao" required/>
            <label for="preco">Preço:</label>
            <input class="input" type="text" id="preco" name="preco" required/>
            <label for="preco">Quantidade em estoque:</label>
            <input class="input" type="text" id="quantidadeEstoque" name="quantidadeEstoque" required/>
            <input type="submit" value="Cadastrar" class="submit"/>

        </form>
    </div>
</main>
</body>
</html>
