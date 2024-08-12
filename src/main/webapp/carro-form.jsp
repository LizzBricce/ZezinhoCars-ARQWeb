<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Carro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .form-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        .btn-submit {
            background-color: #28a745;
            color: #fff;
            font-size: 16px;
            padding: 10px 20px;
        }

        .btn-submit:hover {
            background-color: #218838;
        }

        .btn-clear {
            background-color: transparent;
            color: #000;
            border: 1px solid #ced4da;
            font-size: 16px;
            padding: 10px 20px;
        }

        .btn-clear:hover {
            background-color: #e2e6ea;
        }

        .btn-back {
            position: absolute;
            top: 20px;
            left: 20px;
        }

        .checkbox-group {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .checkbox-group div {
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <a href="carro?action=list" class="btn btn-outline-primary btn-sm btn-back">Voltar para a Lista</a>
        <h2 class="text-center mb-4">${carro != null ? 'Editar Carro' : 'Adicionar Carro'}</h2>
        <div class="form-container">
            <form action="carro" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="${carro != null ? 'edit' : 'add'}">
                <c:if test="${carro != null}">
                    <input type="hidden" name="id" value="${carro.id}">
                </c:if>

                <div class="mb-3">
                    <label for="avaliacao" class="form-label">Avaliação:</label>
                    <input type="number" id="avaliacao" name="avaliacao" min="1" max="10" class="form-control" value="${carro != null ? carro.avaliacao : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="preco" class="form-label">Preço:</label>
                    <input type="number" step="0.01" id="preco" name="preco" class="form-control" value="${carro != null ? carro.preco : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="km" class="form-label">Quilometragem:</label>
                    <input type="number" id="km" name="km" min="1" class="form-control" value="${carro != null ? carro.km : ''}" required>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <label for="categoria" class="form-label">Categoria:</label>
                        <select id="categoria" name="categoria" class="form-select" required>
                            <option value="0" ${carro != null && carro.categoria == 'SUV' ? 'selected' : ''}>SUV</option>
                            <option value="1" ${carro != null && carro.categoria == 'HATCHBACK' ? 'selected' : ''}>HATCHBACK</option>
                            <option value="2" ${carro != null && carro.categoria == 'SEDAN' ? 'selected' : ''}>SEDAN</option>
                        </select>
                    </div>
                    <div class="col">
                        <label for="tipoCombustivel" class="form-label">Tipo de Combustível:</label>
                        <select id="tipoCombustivel" name="tipoCombustivel" class="form-select" required>
                            <option value="0" ${carro != null && carro.tipoCombustivel == 'GASOLINA' ? 'selected' : ''}>GASOLINA</option>
                            <option value="1" ${carro != null && carro.tipoCombustivel == 'ALCOOL' ? 'selected' : ''}>ALCOOL</option>
                            <option value="2" ${carro != null && carro.tipoCombustivel == 'DISEL' ? 'selected' : ''}>DISEL</option>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="marca" class="form-label">Marca:</label>
                    <input type="text" id="marca" name="marca" class="form-control" value="${carro != null ? carro.marca : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="modelo" class="form-label">Modelo:</label>
                    <input type="text" id="modelo" name="modelo" class="form-control" value="${carro != null ? carro.modelo : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="anoFabricacao" class="form-label">Ano de Fabricação:</label>
                    <input type="text" id="anoFabricacao" name="anoFabricacao" class="form-control" value="${carro != null ? carro.anoFabricacao : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="cor" class="form-label">Cor:</label>
                    <input type="text" id="cor" name="cor" class="form-control" value="${carro != null ? carro.cor : ''}" required>
                </div>

                <div class="mb-3">
                    <div class="checkbox-group">
                        <div>
                            <input type="checkbox" id="destaque" name="destaque" class="form-check-input" ${carro != null && carro.destaque ? 'checked' : ''}>
                            <label for="destaque" class="form-check-label">Destaque</label>
                        </div>
                        <div>
                            <input type="checkbox" id="lancamento" name="lancamento" class="form-check-input" ${carro != null && carro.lancamento ? 'checked' : ''}>
                            <label for="lancamento" class="form-check-label">Lançamento</label>
                        </div>
                        <div>
                            <input type="checkbox" id="oferta" name="oferta" class="form-check-input" ${carro != null && carro.oferta ? 'checked' : ''}>
                            <label for="oferta" class="form-check-label">Oferta</label>
                        </div>
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="imagem" class="form-label">Imagem do carro:</label>
                    <input type="file" id="imagem" name="imagem" class="form-control">
                </div>
                
                <c:if test="${carro != null && carro.imagemBase64 != null}">
                    <img src="data:image/jpeg;base64,${carro.imagemBase64}" alt="Imagem do Carro" width="200" class="mb-3"><br>
                </c:if>

                <div class="d-flex justify-content-between mt-4">
                    <button type="submit" class="btn btn-submit">${carro != null ? 'Atualizar' : 'Adicionar'}</button>
                    <button type="reset" class="btn btn-outline-danger">Limpar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
