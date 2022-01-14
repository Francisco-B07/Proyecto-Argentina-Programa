<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/attractions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>
		
		<section class="container p-4" id="atracciones">
    <div class="row row-cols-1 row-cols-md-3 g-4">
    

   
    <c:forEach items="${atracciones}" var="atraccion">
    	<c:if test="${atraccion.isActive()}">
      <div class="col">
        <div class="card shadow p-3 mb-5 bg-white rounded">
          <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img src="/turismo/assets/img/atraccion-1.jpg" class="d-block w-100" alt="Muestra atraccion">
              </div>
              <div class="carousel-item">
                <img src="/turismo/assets/img/atraccion-2.jpg" class="d-block w-100" alt="Muestra atraccion">
              </div>
              <div class="carousel-item">
                <img src="/turismo/assets/img/atraccion-3.jpg" class="d-block w-100" alt="Muestra atraccion">
              </div>
            </div>
          </div>
          <div class="card-body">
            <h5 class="card-title"><strong><c:out value="${atraccion.nombre}"></c:out></strong></h5>
            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item"><strong>Cupo: </strong><c:out value="${atraccion.cupo}"></c:out></li>
            <li class="list-group-item"><strong>Tipo: </strong><c:out value="${atraccion.tipo}"></c:out></li>
            <li class="list-group-item"><strong>Tiempo: </strong><c:out value="${atraccion.tiempo}"></c:out></li>
            <li class="list-group-item"><strong>Costo: </strong><c:out value="${atraccion.costo}"></c:out></li>
          </ul>
          <div class="card-body d-flex justify-content-around">
            <c:if test="${user.admin}">
								<a href="/turismo/attractions/edit.do?id=${atraccion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/attractions/delete.do?id=${atraccion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> 
							<c:choose>
								<c:when
									test="${user.canAfford(atraccion) && user.canAttend(atraccion) && atraccion.hayCupo()}">
									<a href="/turismo/attractions/buy.do?id=${atraccion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose>
          </div>
        </div>
      </div>
      </c:if>
      </c:forEach>
      
    </div>
  </section>
		
		
		

	</main>
		<jsp:include page="/partials/footer.jsp"></jsp:include>
	
</body>
</html>