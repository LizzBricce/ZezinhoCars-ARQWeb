<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usu�rios</title>
</head>
<body>
    <h2>Lista de Usu�rios</h2>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Administrador</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.nome}</td>
                <td>${user.email}</td>
                <td>${user.adm ? 'Sim' : 'N�o'}</td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="user-form.jsp">Adicionar Novo Usu�rio</a>
</body>
</html>