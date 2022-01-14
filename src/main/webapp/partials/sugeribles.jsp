<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="container">

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
			<h1>Estas son tus sugerencias de la Tierra Media</h1>
		</div>

	
		<section class="container p-4" id="sugeribles">
    <div class="row row-cols-1 row-cols-md-3 g-4">
    
    <c:forEach items="${sugeribles}" var="sugerible">
    <c:if test="${user.canAfford(sugerible) && user.canAttend(sugerible) && sugerible.hayCupo() && !user.yaCompro()}">
      <div class="col">
        <div class="card">
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
            <h5 class="card-title"><strong><c:out value="${sugerible.nombre}"></c:out></strong></h5>
            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item"><strong>Tipo: </strong><c:out value="${sugerible.tipo}"></c:out></li>
            <li class="list-group-item"><strong>Costo: </strong><c:out value="${sugerible.costo}"></c:out></li>
          </ul>
          <div class="card-body d-flex justify-content-around">
							<c:choose>
								<c:when
									test="${user.canAfford(sugerible) && user.canAttend(sugerible) && sugerible.hayCupo() && !user.yaCompro()}">
									<a href="/turismo/sugeribles/buy.do?id=${sugerible.id}"
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
	</div>
		