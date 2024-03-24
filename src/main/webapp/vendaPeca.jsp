<%--
  Created by IntelliJ IDEA.
  User: Marianna
  Date: 24/03/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>Venda de Peças</title>
        <link rel="stylesheet" href="styleCss.css">
    </head>
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar peça</h1>
        <form action="/vendaPeca" method="POST" class="form">
            <label for="idVenda">Id da venda:</label>
            <input class="input" type="text" id="idVenda" name="idVenda" required/>
            <label for="idPeca">Id da peça:</label>
            <input class="input" type="text" id="idPeca" name="idPeca" required/>

            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="vendaPeca" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar venda de peça por id..." name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="vendaPeca" items="${vendaPecas}">
                <div class="block">
                    <div style="display: flex; width: 100%; justify-content: space-between">
                        <div>
                            <p>ID: ${vendaPeca.id}</p>
                            <p>ID da venda: ${vendaPeca.venda.id}</p>
                            <p>ID da peça: ${vendaPeca.peca.id}</p>
                        </div>
                        <div class="button-group">
                            <form action="mvc" method="POST">
                                <input type="hidden" name="id" value="${vendaPeca.id}">
                                <input type="hidden" name="logica" value="DeletaVendaPeca"/>
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
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script src="script.js"></script>
</body>
</html>

