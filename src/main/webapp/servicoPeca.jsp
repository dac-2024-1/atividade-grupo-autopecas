<%--
  Created by IntelliJ IDEA.
  User: Marianna
  Date: 25/03/2024
  Time: 07:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Peças usadas em serviços</title>
    <link rel="stylesheet" href="styleCss.css">
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar peças usadas em serviços</h1>
        <form action="servicoPeca" method="POST" class="form">
            <label for="idPeca">Peça:</label>
            <input class="input" type="text" id="idPeca" name="idPeca" required/><br/><br/>
            <label for="idServico">Serviço:</label>
            <input class="input" type="text" id="idServico" name="idServico" required/><br/><br/>

            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="servicoPeca" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar peças usadas em serviço por id..."
                       name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="servicoPeca" items="${servicospecas}">
            <div class="block">
                <div style="display: flex; width: 100%; justify-content: space-between">
                    <div>
                        <p>ID: ${servicoPeca.id}</p>
                        <p>Id peça: ${servicoPeca.peca.id}</p>
                        <p>Id serviço: ${servicoPeca.servico.id}</p>
                    </div>
                    <div class="button-group">
                        <form action="mvc" method="POST">
                            <input type="hidden" name="id" value="${servicoPeca.id}">
                            <input type="hidden" name="logica" value="DeletaServicoPeca"/>
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

</main>
<script src="script.js"></script>
</body>
</html>
