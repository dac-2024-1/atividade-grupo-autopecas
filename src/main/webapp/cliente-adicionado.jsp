<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cliente adicionado</title>
</head>
<body>
    <jsp:useBean id="dao" class="br.com.autopecas.projetogrupo.dao.ClienteDao" />
    <table>
        <c:forEach var="cliente" items="${dao.buscaTodosClientes()}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.endereco}</td>
                <td>${cliente.telefone}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
