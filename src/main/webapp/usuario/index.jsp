<jsp:useBean id="username" scope="session" type="java.lang.String"/>
<%--@elvariable id="funcionario" type="br.com.autopecas.projetogrupo.entidades.Funcionario"--%>
<%--
  Created by IntelliJ IDEA.
  User: vrsm
  Date: 24/03/2024
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/base.css">
    <link rel="stylesheet" href="style/perfilUsuario.css">
    <title>Perfil de ${username} | Tonhão Autopeças</title>
</head>
<body>

<nav id="menu-usuario">
    <a class="link-menu" href="paginaInicial.html">Pagina Inicial</a>
    <a class="link-menu" href="/usuario/logout">Logout</a>
    </span>
</nav>

<main>

    <h1>Perfil de ${username}</h1>

    <div id="conteudo">

        <c:if test="${funcionario.id != null}">
        <div id="perfil" style="display: flex;">
            <h2>Informações do funcionario</h2>
            <div id="info-funcionario">
                <p class="texto-basico">Nome: ${funcionario.nome}</p>
                <p class="texto-basico">Cargo: ${funcionario.cargo}</p>
                <p class="texto-basico">Endereço: ${funcionario.endereco}</p>
                <p class="texto-basico">Telefone: ${funcionario.telefone}</p>
                <p class="texto-basico">Salario: ${funcionario.salario}</p>
                <p class="texto-basico">Data de contrataçao: ${funcionario.dataContratacao}</p>
            </div>
            <a class="botao-padrao" id="botao-funcionario" href="funcionario?id=1">Alterar dados do Funcionario</a>

        </div>
        </c:if>

        <div id="conteudo-alterar">
            <div id="alterar-senha">

                <h2>Alterar senha</h2>

                <form action="${pageContext.request.contextPath}/usuario" method="post">
                    <label class="texto-basico" for="senhaAtual">Senha atual:</label>
                    <input class="input-form" type="password" id="senhaAtual" name="senhaAtual">
                    <label class="texto-basico" for="newPassword">Nova senha:</label>
                    <input class="input-form" type="password" id="newPassword" name="newPassword">
                    <label class="texto-basico" for="confirmaNewPassword">Confirme a nova senha:</label>
                    <input class="input-form" type="password" id="confirmaNewPassword" name="confirmaNewPassword">
                    <input class="botao-padrao" id="botao-atualizar" type="submit" value="Atualizar senha">
                </form>
            </div>
            <button class="botao-padrao" id="buttonDelete" onclick="deleteAccount()">Deletar Conta</button>
        </div>
    </div>
</main>
</body>
<script>
    function deleteAccount() {
        const url = "${pageContext.request.contextPath}/usuario"
        if (confirm('Tem certeza que deseja deletar sua conta?')) {
            fetch(url, {
                method: 'DELETE'
            })
                .then(response => {
                    console.log(url)
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    alert(data);
                    window.location.href = '/usuario/login.jsp';
                })
                .catch(error => {
                    alert('Erro ao deletar conta. Verifique se nao ha um funcionario associado a esta conta.');
                    console.error(error);
                });
        }
    }
</script>
</html>
