<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>

 nav ul li a {
    font-family: arial;
    color: #EEE;
    text-decoration: none;
    position: relative;
    padding: 15px 10px;
    font-size: 18px;
    display: block;
}
nav ul li a + a {
    margin-left: 16px;
}
nav ul li a::after {
    content: '';
    position: absolute;
    left: 0px;
    bottom: 0px;
    width: 100%;
    height: 4px;
    background-color: #333;
    transform-origin: bottom right;
    transition: transform 0.5s ease;
    transform: scaleX(0);
}
nav ul a:hover::after {
    transform-origin: bottom left;
    transform: scaleX(1);
}
</style>

<nav class="navbar navbar-expand-md navbar-light mb-4" style="background: #E3F2FD">
	<div class="container">
		<a class="navbar-brand" href="/turismo/index.jsp">Turismo en la Tierra Media</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
			<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/turismo/sugeribles/index.do">Sugerencias</a></li>
			
			 <li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/turismo/attractions/index.do">Atracciones</a></li>
					
			<c:if test="${user.isAdmin()}">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/turismo/users/index.do">Usuarios</a></li>
			</c:if>
			
			<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/turismo/promotions/index.do">Promociones</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<c:out value="${user.username}"></c:out>
					</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item disabled" style="color: black;">
							<i title="monedas" style="color: gold;" class="bi bi-coin"></i> <c:out value="${user.coins}"></c:out>
						</a></li>
						<li><a class="dropdown-item disabled" style="color: black;">
							<i title="tiempo" style="color: blue;" class="bi bi-clock-fill"></i> <c:out value="${user.time}h"></c:out>
						</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a href="/turismo/users/comprado.do" class="dropdown-item">Comprado</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a href="/turismo/logout" class="dropdown-item">Salir</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
