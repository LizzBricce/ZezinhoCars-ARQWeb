<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulário de Carro</title>
</head>
<body>
    <h2>${carro != null ? 'Editar Carro' : 'Adicionar Carro'}</h2>
    
    <form action="carro" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="${carro != null ? 'edit' : 'add'}">
        <c:if test="${carro != null}">
            <input type="hidden" name="id" value="${carro.id}">
        </c:if>

        <label for="avaliacao">Avaliação:</label>
        <input type="number" id="avaliacao" name="avaliacao" value="${carro != null ? carro.avaliacao : ''}" required><br>

        <label for="preco">Preço:</label>
        <input type="number" step="0.01" id="preco" name="preco" value="${carro != null ? carro.preco : ''}" required><br>

        <label for="km">Quilometragem:</label>
        <input type="number" id="km" name="km" value="${carro != null ? carro.km : ''}" required><br>

        <label for="categoria">Categoria:</label>
        <select id="categoria" name="categoria" required>
            <option value="0">SUV</option>
            <option value="1">HATCHBACK</option>
            <option value="2">SEDAN</option>
        </select><br>

        <label for="marca">Marca:</label>
        <input type="text" id="marca" name="marca" value="${carro != null ? carro.marca : ''}" required><br>

        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo" value="${carro != null ? carro.modelo : ''}" required><br>

        <label for="anoFabricacao">Ano de Fabricação:</label>
        <input type="text" id="anoFabricacao" name="anoFabricacao" value="${carro != null ? carro.anoFabricacao : ''}" required><br>

        <label for="cor">Cor:</label>
        <input type="text" id="cor" name="cor" value="${carro != null ? carro.cor : ''}" required><br>

        <label for="tipoCombustivel">Tipo de Combustível:</label>
        <select id="tipoCombustivel" name="tipoCombustivel" required>
            <option value="0">GASOLINA</option>
            <option value="1">ALCOOL</option>
            <option value="2">DISEL</option>
        </select><br>

        <label for="destaque">Destaque:</label>
        <input type="checkbox" id="destaque" name="destaque" ${carro != null && carro.destaque ? 'checked' : ''}><br>

        <label for="lancamento">Lançamento:</label>
        <input type="checkbox" id="lancamento" name="lancamento" ${carro != null && carro.lancamento ? 'checked' : ''}><br>

        <label for="oferta">Oferta:</label>
        <input type="checkbox" id="oferta" name="oferta" ${carro != null && carro.oferta ? 'checked' : ''}><br>
        
        <label for="imagem">Imagem do carro</label>
        <input type="file" id="imagem" name="imagem"><br>

        <button type="submit">${carro != null ? 'Atualizar' : 'Adicionar'}</button>
    </form>

    <a href="carro?action=list">Voltar para a Lista</a>
</body>
</html>
