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
    <div class="content" id="col2">
        <div>
            <form action="peca" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar peça por id..." name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="peca" items="${pecas}">
                <div class="block">
                    <div style="display: flex; width: 100%; justify-content: space-between">
                        <div>
                            <p>ID: ${peca.id}</p>
                            <p>Nome: ${peca.nome}</p>
                            <p>Descrição: ${peca.descricao}</p>
                            <p>Preço: ${peca.preco}</p>
                            <p>Quantidade em estoque: ${peca.quantidadeEstoque}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
