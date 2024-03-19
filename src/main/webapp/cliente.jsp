<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>Adiciona cliente</title>
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
            margin: 3rem 10rem;
            gap: 5rem;
        }
        .form {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }
        .input {
            padding: 0.25rem 0.5rem;
            border: 1px solid gray;
            border-radius: 5px;
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
            border: 1px solid gray;
            padding: 1rem 2rem;
            border-radius: 30px;
            margin: 0.5rem 0px;
        }
        #col2 {
            max-height: 80vh;
            overflow: auto;
            padding-right: 1rem;
        }
    </style>
</head>
<body>
    <main class="container">
        <div class="content">
            <h1 class="title">Cadastrar cliente</h1>
            <form action="mvc" method="POST" class="form">
                <label for="nome">Nome:</label>
                <input class="input" type="text" name="nome" required/>
                <label for="endereco">Endereço:</label>
                <input class="input" type="text" name="endereco" required/>
                <label for="telefone">Telefone:</label>
                <input class="input" type="text" name="telefone" required/>
                <input type="hidden" name="logica" value="AdicionaCliente" />
                <input type="submit" value="Cadastrar" class="submit"/>
            </form>
        </div>
        <div class="content" id="col2">
            <jsp:useBean id="dao" class="br.com.autopecas.projetogrupo.dao.ClienteDao" />
                <c:forEach var="cliente" items="${dao.buscaTodosClientes()}">
                <div class="block">
                    <p>ID: ${cliente.id}</p>
                    <p>Nome: ${cliente.nome}</p>
                    <p>Endereço: ${cliente.endereco}</p>
                    <p>Telefone: ${cliente.telefone}</p>
                </div>
                </c:forEach>
        </div>

    </main>

</body>
</html>