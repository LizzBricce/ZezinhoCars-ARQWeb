<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-1yoH5Fb6udkTX4P7s/qU1EJb1a9E+xM2omLKP/JyEtdIkQeXJRu7Pbt6z1f5TxK4Rso4pFlH2SStHpEJQd3Hg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	    <style>
        .border-yellow{
            border: 2px solid #ffc107; 
        }
        .hover-yellow:hover{
        	 background-color: #ffc107;
        }
        .navbar-brand .car-icon {
            width: 36px;
            height: auto;
            margin-right: 5px; 
        }
    </style>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="img/car.png" alt="Car Icon" class="car-icon"> ZEZINHO CARS
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link text-dark hover-yellow" href="carro?action=list">Home</a>
                </li>
                <c:if test="${empty user}">
	               <li class="nav-item">
	                   <a class="nav-link text-dark hover-yellow" href="user-form.jsp">Cadastro</a>
	               </li>
                </c:if>
                <c:if test="${not empty user}">
                    <li class="nav-item">
                        <a class="nav-link text-dark hover-yellow" href="logout">Logout</a>
                    </li>
                 </c:if>
                <c:if test="${empty user}">
                    <li class="nav-item">
                        <a class="nav-link text-dark hover-yellow" href="login.jsp">Login</a>
                    </li>
                </c:if>
                <li class="nav-item">
                   <a class="nav-link text-dark hover-yellow" href="sobre.jsp">Sobre</a>
                </li>
            </ul>
        </div>
    </nav>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous" defer></script>
</body>
</html>
