<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Serviços</title>
    <link rel="stylesheet" href="styleCss.css">
    <link rel="stylesheet" href="servico.css">
</head>
<body>
<main class="container">
    <div class="content">
        <h1 class="title">Cadastrar serviço</h1>
        <form action="servico" method="POST" class="form">
            <label for="descricao">Descrição:</label>
            <input class="input" type="text" id="descricao" name="descricao" required/><br/><br/>
            <label for="preco">Preço:</label>
            <input class="input" type="text" id="preco" name="preco" required/><br/><br/>
            <label for="data">Data:</label>
            <input class="input" type="date" id="data" name="data" required/><br/><br/>
            <label for="idVeiculo">Veículo:</label>
            <select class="campo-select input" id="idVeiculo" name="idVeiculo" required>
                <option value="" selected>Selecione um veículo</option>
                <c:forEach var="veiculo" items="${veiculos}">
                    <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca} ${veiculo.modelo} - Proprietário: ${veiculo.cliente.nome}</option>
                </c:forEach>
            </select><br/><br/>
            <label for="idFuncionario">Funcionário:</label>
            <select class=" campo-select input" id="idFuncionario" name="idFuncionario" required>
                <option value="" selected>Selecione um funcionário</option>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Cadastrar" class="submit"/>
        </form>
        <a href="servicoPeca.jsp">Deseja cadastrar as peças incluídas na venda?</a>
    </div>
    <div class="content" id="col2">
        <div>
            <form action="servico" method="GET" class="search-form">
                <input type="text" class="search-input" placeholder="Buscar serviço por id..." name="id">
                <button class="search-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="white" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"
                         class="lucide lucide-search">
                        <circle cx="11" cy="11" r="8"/>
                        <path d="m21 21-4.3-4.3"/>
                    </svg>
                </button>
            </form>
            <c:forEach var="servico" items="${servicos}">
                <div class="block">
                    <div style="display: flex; width: 100%; justify-content: space-between">
                        <div>
                            <p>ID: ${servico.id}</p>
                            <p>Descrição: ${servico.descricao}</p>
                            <p>Preço: ${servico.preco}</p>
                            <p>Data: ${servico.data}</p>
                            <p>Veiculo: ${servico.veiculo.id} - ${servico.veiculo.marca} ${servico.veiculo.modelo} </p>
                            <p>Funcionario: ${servico.funcionario.nome}</p>
                        </div>
                        <div class="button-group">
                            <form action="mvc" method="POST">
                                <input type="hidden" name="id" value="${servico.id}">
                                <input type="hidden" name="logica" value="DeletaServico"/>
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
                            <label for="descricao-${servico.id}">Descrição:
                            <input class="input" type="text" id="descricao-${servico.id}" name="descricao" value="${servico.descricao}" required/>
                            </label>
                            <label for="preco-${servico.id}">Preço:
                            <input class="input" type="text" id="preco-${servico.id}" name="preco"  value="${servico.preco}" required/>
                            </label>
                            <label class="label-style" for="data-${servico.id}">Data:
                            <input class="input" type="date" id="data-${servico.id}" name="data" value="${servico.data}" required/><br/><br/>
                            </label>
                            <label class="label-style" for="idVeiculo-${servico.id}">Veículo:
                            <select class="campo-select input" id="idVeiculo-${servico.id}" name="idVeiculo" required>
                                <option value="${servico.veiculo.id}" selected>${servico.veiculo.id} - ${servico.veiculo.marca} ${servico.veiculo.modelo} - Proprietário: ${servico.veiculo.cliente.nome}</option>
                                <c:forEach var="veiculo" items="${veiculos}">
                                    <c:if test="${veiculo.id != servico.veiculo.id}">
                                    <option value="${veiculo.id}">${veiculo.id} - ${veiculo.marca} ${veiculo.modelo} - Proprietário: ${veiculo.cliente.nome}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            </label>
                            <label for="idFuncionario-${servico.id}">Funcionário:
                            <select class=" campo-select input" id="idFuncionario-${servico.id}" name="idFuncionario" required>
                                <option value="${servico.funcionario.id}" selected>${servico.funcionario.id} - ${servico.funcionario.nome}</option>
                                <c:forEach var="funcionario" items="${funcionarios}">
                                    <c:if test="${funcionario.id != servico.funcionario.id}">
                                    <option value="${funcionario.id}">${funcionario.id} - ${funcionario.nome}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            </label>
                        </div>
                        <input type="hidden" name="id" value="${servico.id}">
                        <input type="hidden" name="logica" value="AtualizaServico"/>
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
