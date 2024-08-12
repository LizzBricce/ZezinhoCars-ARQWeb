<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp" />
<!DOCTYPE html>
<html lang="en">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="card p-4 border-warning border-2" style="width: 100%; max-width: 500px;">
            <h2 class="card-title mb-4">Adicionar Usuário</h2>
            <form action="user" method="post">
                <input type="hidden" name="action" value="add">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input type="text" id="nome" name="nome" class="form-control border-yellow" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="form-control border-yellow" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" id="senha" name="senha" class="form-control border-yellow" required>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" id="adm" name="adm" class="form-check-input">
                    <label for="adm" class="form-check-label">Administrador</label>
                </div>
                <button type="submit" class="btn btn-warning">Adicionar</button>
            </form>
        </div>
    </div>
    
    </body>
</html>
