<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visualizar Carro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
    <style>
        .position-relative {
            position: relative;
        }
        
        .badge-lancamento {
            position: absolute;
            top: 0;
            right: 0;
            width: 120px; /* Ajuste o tamanho da faixa conforme necessário */
            height: 120px; /* Ajuste a altura da faixa conforme necessário */
            transform-origin: top right;
            z-index: 10; /* Garante que a faixa fique sobre a imagem */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Detalhes do Carro</h1>
        <div class="card">
            <div class="card-header">
                <h2 class="text-center">${carro.marca} ${carro.modelo} (${carro.anoFabricacao})</h2>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Categoria:</strong> ${carro.categoria}</p>
                        <p><strong>Combustível:</strong> ${carro.tipoCombustivel}</p>
                        <p><strong>Cor:</strong> ${carro.cor}</p>
                        <p><strong>Km:</strong> ${carro.km}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Avaliação:</strong> ${carro.avaliacao}</p>
                        <p><strong>Destaque:</strong> 
                            <c:choose>
                                <c:when test="${carro.destaque}">Sim</c:when>
                                <c:otherwise>Não</c:otherwise>
                            </c:choose>
                        </p>
                        <p><strong>Lançamento:</strong> 
                            <c:choose>
                                <c:when test="${carro.lancamento}">Sim</c:when>
                                <c:otherwise>Não</c:otherwise>
                            </c:choose>
                        </p>
                        <p><strong>Preço:</strong> 
                            <c:choose>
                                <c:when test="${carro.oferta}">
                                    <span style="text-decoration: line-through;">R$ <script>
                                        document.write(((${carro.preco}) * 1.15).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span>
                                    <span class="text-danger">R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span>
                                </c:when>
                                <c:otherwise>
                                    <span>R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>
                <c:if test="${carro.imagemBase64 != null && !carro.imagemBase64.isEmpty()}">
                    <div class="text-center mt-4 position-relative">
                        <img src="data:image/jpeg;base64,${carro.imagemBase64}" alt="Imagem do Carro" class="img-fluid rounded"/>
                        
                        <c:if test="${carro.lancamento}">                        
                        	<img alt="lançamento" src="https://promoval.com.br/wp-content/uploads/2021/02/lancamento.png" class="badge-lancamento"/>
                        </c:if>
                    </div>
                </c:if>
            </div>
            <div class="card-footer text-center">
                <a href="carro?action=list" class="btn btn-primary">
                    <i class="bi bi-arrow-left"></i> Voltar
                </a>
            </div>
        </div>
    </div>
</body>
</html>
