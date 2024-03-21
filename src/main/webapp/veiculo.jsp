<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>Veiculos</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: Verdana;
        }

        .title {
            color: teal;
            margin-bottom: 2rem;
        }

        .container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            margin: 3rem 8rem;
            gap: 5rem;
        }

        .form {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .input {
            padding: 0.5rem 0.75rem;
            border: 1px solid lightgrey;
            border-radius: 5px;
            box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
        }

        .submit {
            background-color: teal;
            border: none;
            padding: 0.75rem 2rem;
            align-self: start;
            border-radius: 20px;
            color: white;
            font-weight: bold;
            margin-top: 1rem;
            text-transform: uppercase;
            cursor: pointer;
        }

        .block {
            border: 1px solid teal;
            padding: 1rem 2rem;
            border-radius: 30px;
            margin: 0.75rem 0;
            display: flex;
            justify-content: space-between;
            box-shadow: rgba(0, 0, 0, 0.16) 0 1px 4px;
        }

        #col2 {
            max-height: 80vh;
            overflow: auto;
            padding-right: 1rem;
        }

        .delete-button, .search-button {
            border: 1px solid teal;
            background-color: transparent;
            padding: 0.25rem;
            border-radius: 30px;
            cursor: pointer;
        }

        .search-button {
            background-color: teal;
        }
        .delete-button:hover {
            background-color: teal;
        }

        .delete-button:hover svg {
            stroke: white;
        }

        .search-form {
            display: flex;
            gap: 1rem;
            align-items: center;
            row-gap: 2;
            padding: 0.5rem 0.75rem;
            border: 1px solid lightgrey;
            border-radius: 5px;
            box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
        }

        .search-input {
            border: none;
            width: 100%;
        }
    </style>
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar veiculo</h1>
        <form action="mvc" method="POST" class="form">
            <label for="marca">Marca:</label>
            <input class="input" type="text" id="marca" name="marca" required/>
            <label for="modelo">Modelo:</label>
            <input class="input" type="text" id="modelo" name="modelo" required/>
            <label for="ano">Ano:</label>
            <input class="input" type="text" id="ano" name="ano" required/>
            <label for="placa">Placa:</label>
            <input class="input" type="text" id="placa" name="placa" required/>
            <label for="idcliente">Id do cliente:</label>
            <input class="input" type="text" id="idcliente" name="idcliente" required/>
            <input type="hidden" name="logica" value="AdicionaVeiculo"/>
            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="mvc" method="POST" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar veículo por id...">
                <input type="hidden" name="id" value="${veiculo.id}">
                <input type="hidden" name="logica" value="BuscaVeiculo"/>
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:choose>
                <c:when test="${param}">
                    pizza.
                    <br />
                </c:when>
                <c:otherwise>
                    pizzas.
                    <br />
                </c:otherwise>
            </c:choose>
            <jsp:useBean id="dao" class="br.com.autopecas.projetogrupo.dao.VeiculoDao"/>

            <c:forEach var="veiculo" items="${dao.buscaTodosVeiculos()}">
                <div class="block">
                    <div>
                        <p>ID: ${veiculo.id}</p>
                        <p>Marca: ${veiculo.marca}</p>
                        <p>Modelo: ${veiculo.modelo}</p>
                        <p>Ano: ${veiculo.ano}</p>
                        <p>Ano: ${veiculo.placa}</p>
                        <p>Cliente: ${veiculo.cliente.id}</p>
                    </div>
                    <form action="mvc" method="POST">
                        <input type="hidden" name="id" value="${veiculo.id}">
                        <input type="hidden" name="logica" value="DeletaVeiculo"/>
                        <button class="delete-button" type="submit">
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
            </c:forEach>
        </div>
    </div>

</main>

</body>