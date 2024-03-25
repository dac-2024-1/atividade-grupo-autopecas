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
    <title>Perfil de ${username}</title>
</head>
<body>
<h1>Perfil de ${username}.</h1>

<c:if test="${funcionario.id != null}">
    <h2>Informações do funcionario</h2>
    <p>Nome: ${funcionario.nome}</p>
    <p>Cargo: ${funcionario.cargo}</p>
    <p>Endereço: ${funcionario.endereco}</p>
    <p>Telefone: ${funcionario.telefone}</p>
    <p>Salario: ${funcionario.salario}</p>
    <p>Data de contrataçao: ${funcionario.dataContratacao}</p>
    <a href="funcionario?id=${funcionario.id}">Alterar dados ou remover funcionario na pagina /funcionarios.</a>
</c:if>
<h2>Alterar senha</h2>
<c:if test="${mensagem.contains(\"Erro\")}">
    <p style="color: red">${mensagem}</p>
</c:if>
<form action="${pageContext.request.contextPath}/usuario" method="post">
    <label for="senhaAtual">Senha atual:</label><br>
    <input type="password" id="senhaAtual" name="senhaAtual"><br>
    <label for="newPassword">Nova senha:</label><br>
    <input type="password" id="newPassword" name="newPassword"><br>
    <label for="confirmaNewPassword">Confirme a nova senha:</label><br>
    <input type="password" id="confirmaNewPassword" name="confirmaNewPassword"><br>
    <br>
    <input type="submit" value="Atualizar senha">
</form>

<button id="buttonDelete" onclick="deleteAccount()">Deletar Conta</button>
<br><br>
<a href="${pageContext.request.contextPath}/usuario/logout">Logout</a>
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
