<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="templates/header.jsp" />
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sobre - ZezinhoCars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <style>
        .hero-section {
            background: url('img/car.png') no-repeat center center;
            background-size: cover;
            color: #fff;
            padding: 100px 0;
            text-align: center;
        }
        .hero-section h1 {
            font-size: 3rem;
            margin-bottom: 20px;
        }
        .hero-section p {
            font-size: 1.25rem;
        }
        .section-title {
            margin-top: 40px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 2rem;
            color: #007bff;
        }
        .section-content {
            margin-bottom: 40px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="hero-section">
            <h1>Bem-vindo ao ZezinhoCars</h1>
            <p>A melhor plataforma para encontrar o carro dos seus sonhos!</p>
        </div>
        
        <div class="section-content">
            <h2 class="section-title">Sobre Nós</h2>
            <p>
                O ZezinhoCars é uma plataforma dedicada a ajudar você a encontrar o carro perfeito. Com uma vasta seleção de veículos de diversas categorias, você pode explorar opções que atendem às suas necessidades e preferências. Embora não ofereçamos a funcionalidade de compra diretamente através da nossa plataforma, nosso objetivo é fornecer uma experiência de pesquisa completa e satisfatória.
            </p>
            <p>
                Nossa missão é oferecer um serviço acessível e fácil de usar para todos que estão em busca de um carro, seja para uso pessoal, familiar ou profissional. Nossa equipe é composta por entusiastas de automóveis que estão sempre prontos para ajudar e fornecer as melhores informações sobre os veículos que você procura.
            </p>
            <p>
                Se você tiver alguma dúvida ou precisar de assistência, sinta-se à vontade para entrar em contato conosco através de nosso formulário de contato ou pelos nossos canais de atendimento ao cliente.
            </p>
        </div>

        <div class="section-content">
            <h2 class="section-title">Nossa Equipe</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <img src="http://github.com/LucasAlt40.png" class="card-img-top" alt="Membro da Equipe 1">
                        <div class="card-body">
                            <h5 class="card-title">Lucas</h5>
                            <p class="card-text">Maior hater do Javascript.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <img src="http://github.com/LizzBricce.png" class="card-img-top" alt="Membro da Equipe 2">
                        <div class="card-body">
                            <h5 class="card-title">Elizabeth Bricce</h5>
                            <p class="card-text">Edita aqui Liz.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="section-content text-center">
            <a href="carro?action=list" class="btn btn-primary">Voltar para Home</a>
        </div>
    </div>
</body>
</html>
