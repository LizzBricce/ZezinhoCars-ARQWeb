<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Adicionar Usuário</title>
</head>
<body>
    <h2>Adicionar Usuário</h2>
    
    <form action="user" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" required><br><br>
        
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="senha">Senha:</label><br>
        <input type="password" id="senha" name="senha" required><br><br>
        
        <label for="adm">Administrador:</label><br>
        <input type="checkbox" id="adm" name="adm"><br><br>
        
        <input type="submit" value="Adicionar">
    </form>
</body>
</html>