<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>Veiculos</title>
    <link rel="stylesheet" href="styleCss.css">
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar veiculo</h1>
        <form action="veiculo" method="POST" class="form">
            <label for="marca">Marca:</label>
            <input class="input" type="text" id="marca" name="marca" required/>
            <label for="modelo">Modelo:</label>
            <input class="input" type="text" id="modelo" name="modelo" required/>
            <label for="ano">Ano:</label>
            <input class="input" type="text" id="ano" name="ano" required/>
            <label for="placa">Placa:</label>
            <input class="input" type="text" id="placa" name="placa" required/>
            <label for="idcliente">Cliente:</label>
            <select class="campo-select input" id="idcliente" name="idcliente" required>
                <option value="" selected>Selecione um cliente</option>
                <c:forEach var="cliente" items="${clientes}">
                    <option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="veiculo" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar veículo por id..." name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="veiculo" items="${veiculos}">
                <div class="block">
                    <div style="display: flex; width: 100%; justify-content: space-between">
                        <div>
                            <p>ID: ${veiculo.id}</p>
                            <p>Marca: ${veiculo.marca}</p>
                            <p>Modelo: ${veiculo.modelo}</p>
                            <p>Ano: ${veiculo.ano}</p>
                            <p>Ano: ${veiculo.placa}</p>
                            <p>Cliente: ${veiculo.cliente.id} - ${veiculo.cliente.nome}</p>
                        </div>
                        <div class="button-group">
                            <form action="mvc" method="POST">
                                <input type="hidden" name="id" value="${veiculo.id}">
                                <input type="hidden" name="logica" value="DeletaVeiculo"/>
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
                        <div style="display: flex; align-items: center; gap: 1rem; flex-wrap: wrap">
                            <label for="idcliente-${veiculo.cliente.id}">Cliente:</label>
                            <select class="campo-select input" id="idcliente-${veiculo.cliente.id}" name="idcliente"
                                    required>
                                <option value="${veiculo.cliente.id}" selected>${veiculo.cliente.id}
                                    - ${veiculo.cliente.nome}</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="id" value="${venda.id}">
                        <input type="hidden" name="logica" value="AtualizaVenda"/>
                        <input type="submit" value="Atualizar" class="submit update"/>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

</main>
<script src="script.js"></script>
</body>