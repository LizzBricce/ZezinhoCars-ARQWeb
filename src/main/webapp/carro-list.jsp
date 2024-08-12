<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listagem de Carros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .section-header {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        .card {
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-img-top {
            height: 180px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <c:if test="${not empty user.email}"><a href="carro-form.jsp" class="btn btn-primary mb-4">Adicionar Novo Carro</a></c:if>
        
        <div class="section-header">Carros em Oferta</div>
        <c:choose>
            <c:when test="${not empty carrosEmOferta}">
                <div class="row">
                    <c:forEach var="carro" items="${carrosEmOferta}" varStatus="status">
                        <div class="col-md-4">
                            <div class="card">
                                <img src="data:image/jpeg;base64,${carro.imagemBase64}" class="card-img-top" alt="Imagem do Carro">
                                <div class="card-body">
                                    <h5 class="card-title">${carro.marca} ${carro.modelo}</h5>
                                    <p class="card-text"><span>R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span></p>
                                    <a href="carro?action=view&id=${carro.id}" class="btn btn-warning">Ver detalhes</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>Sem carros em oferta</p>
            </c:otherwise>
        </c:choose>
        
        <div class="section-header">Carros Lançamentos</div>
        <c:choose>
            <c:when test="${not empty carrosLancamentos}">
                <div class="row">
                    <c:forEach var="carro" items="${carrosLancamentos}" varStatus="status">
                        <div class="col-md-4">
                            <div class="card">
                                <img src="data:image/jpeg;base64,${carro.imagemBase64}" class="card-img-top" alt="Imagem do Carro">
                                <div class="card-body">
                                    <h5 class="card-title">${carro.marca} ${carro.modelo}</h5>
                                    <p class="card-text"><span>R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span></p>
                                    <a href="carro?action=view&id=${carro.id}" class="btn btn-warning">Ver detalhes</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>Nenhum lançamento</p>
            </c:otherwise>
        </c:choose>
        
        <div class="section-header">Carros Destaque</div>
        <c:choose>
            <c:when test="${not empty carrosDestaque}">
                <div class="row">
                    <c:forEach var="carro" items="${carrosDestaque}" varStatus="status">
                        <div class="col-md-4">
                            <div class="card">
                                <img src="data:image/jpeg;base64,${carro.imagemBase64}" class="card-img-top" alt="Imagem do Carro">
                                <div class="card-body">
                                    <h5 class="card-title">${carro.marca} ${carro.modelo}</h5>
                                    <p class="card-text"><span>R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span></p>
                                    <a href="carro?action=view&id=${carro.id}" class="btn btn-warning">Ver detalhes</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>Sem carros em destaque</p>
            </c:otherwise>
        </c:choose>
        
        <div class="section-header">Todos os Carros</div>
        <c:choose>
            <c:when test="${not empty todosOsCarros}">
                <div class="row">
                    <c:forEach var="carro" items="${todosOsCarros}" varStatus="status">
                        <div class="col-md-4">
                            <div class="card">
                                <img src="data:image/jpeg;base64,${carro.imagemBase64}" class="card-img-top" alt="Imagem do Carro">
                                <div class="card-body">
                                    <h5 class="card-title">${carro.marca} ${carro.modelo}</h5>
                                    <p class="card-text"><span>R$ <script>
                                        document.write((${carro.preco}).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }));
                                    </script></span></p>
                                    <a href="carro?action=view&id=${carro.id}" class="btn btn-warning">Ver detalhes</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>Nenhum outro carro disponível</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
