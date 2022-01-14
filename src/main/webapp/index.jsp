<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1 class="text-center">
				¡Bienvenido a La Tierra Media, <c:out value="${user.username}"/>!
			</h1>
			
		</div>
		<div class="container-xxl" style="height: 500px;">
        <div id="carouselExampleCaptions" style="width: 60%;" class="carousel slide position-relative top-50 start-50 translate-middle" data-bs-ride="carousel">
            <div class="carousel-indicators">
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img src="/turismo/assets/img/atraccion-3.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <p>Dale un vistazo a las atracciones de la tierra media.</p>
                </div>
              </div>
              <div class="carousel-item">
                <img src="/turismo/assets/img/atraccion-1.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <p>Dale un vistazo a las atracciones de la tierra media.</p>
                </div>
              </div>
              <div class="carousel-item">
                <img src="/turismo/assets/img/atraccion-2.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <p>Dale un vistazo a las atracciones de la tierra media.</p>
                </div>
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
    </div>
   
    <jsp:include page="views/clima/clima.jsp"></jsp:include>

	</main>
	<jsp:include page="partials/footer.jsp"></jsp:include>
</body>
</html>
