<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Carros</title>
</head>
<body>
    <h2>Lista de Carros</h2>
    
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Avalia��o</th>
                <th>Pre�o</th>
                <th>KM</th>
                <th>Categoria</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Ano de Fabrica��o</th>
                <th>Cor</th>
                <th>Combust�vel</th>
                <th>Destaque</th>
                <th>Lan�amento</th>
                <th>Oferta</th>
                <th>Imagem</th>
                <th>A��es</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="carro" items="${carros}">
                <tr>
                    <td>${carro.id}</td>
                    <td>${carro.avaliacao}</td>
                    <td>${carro.preco}</td>
                    <td>${carro.km}</td>
                    <td>${carro.categoria}</td>
                    <td>${carro.marca}</td>
                    <td>${carro.modelo}</td>
                    <td>${carro.anoFabricacao}</td>
                    <td>${carro.cor}</td>
                    <td>${carro.tipoCombustivel}</td>
                    <td>${carro.destaque ? 'Sim' : 'N�o'}</td>
                    <td>${carro.lancamento ? 'Sim' : 'N�o'}</td>
                    <td>${carro.oferta ? 'Sim' : 'N�o'}</td>
                    <td><img src="data:image/jpeg;base64,${carro.imagemBase64}" alt="Imagem do carro" width="100"/></td>
                    <td>
                        <a href="carro?action=edit&id=${carro.id}">Editar</a> |
                        <a href="carro?action=delete&id=${carro.id}" onclick="return confirm('Tem certeza que deseja excluir este carro?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="carro-form.jsp">Adicionar Novo Carro</a>
</body>
</html>
