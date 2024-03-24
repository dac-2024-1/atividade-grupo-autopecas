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
        <meta charset="UTF-8">
        <title>Peças</title>
        <link rel="stylesheet" href="styleCss.css">
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
                        <div class="button-group">
                            <form action="mvc" method="POST">
                                <input type="hidden" name="id" value="${peca.id}">
                                <input type="hidden" name="logica" value="DeletaPeca"/>
                                <button class="icon-button" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                         fill="none"
                                         stroke="rgb(0, 128, 128)" stroke-width="1" stroke-linecap="round"
                                         stroke-linejoin="round"
                                         class="lucide lucide-trash-2">
                                        <path d="M3 6h18"/>
                                        <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"/>
                                        <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"/>
                                        <line x1="10" x2="10" y1="11" y2="17"/>
                                        <line x1="14" x2="14" y1="11" y2="17"/>
                                    </svg>
                                </button>
                            </form>
                            <button class="icon-button update-button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                     fill="none" stroke="rgb(0, 128, 128)" stroke-width="1" stroke-linecap="round"
                                     stroke-linejoin="round" class="lucide lucide-square-pen">
                                    <path d="M12 3H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"/>
                                    <path d="M18.375 2.625a2.121 2.121 0 1 1 3 3L12 15l-4 1 1-4Z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <form action="mvc" method="POST" class="update-div hide">
                        <div style="display: flex; align-items: center; gap: 1rem;">
                            <label for="preco-update">Preço:</label>
                            <input class="input" type="text" id="preco-update" name="preco" required/>
                            <label for="quantidade-update">Quantidade em estoque:</label>
                            <input class="input" type="text" id="quantidade-update" name="quantidadeEstoque" required/>
                        </div>
                        <input type="hidden" name="id" value="${peca.id}">
                        <input type="hidden" name="logica" value="AtualizaPeca"/>
                        <input type="submit" value="Atualizar" class="submit update"/>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script src="script.js"></script>
</body>
</html>
