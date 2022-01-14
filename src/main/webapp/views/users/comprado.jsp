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
			<h1>Estos son todas tus promociones compradas</h1>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Beneficio</th>
					<th>Atracciones Incluidas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promociones}" var="promocion">
					<tr>
						<td><c:out value="${promocion.getId()}"></c:out></td>
						<td><c:out value="${promocion.getNombre()}"></c:out></td>
						<td><c:out value="${promocion.getTipo()}"></c:out></td>
						<td><c:out value="${promocion.getBeneficio()}"></c:out></td>
						<td><c:out value="${promocion.getNombreAtracciones()}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estos son todas tus atracciones compradas</h1>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Precio</th>
					<th>Duracion</th>
					<th>Cupo</th>
					<th>Tipo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${atracciones}" var="atraccion">
					<tr>
						<td><c:out value="${atraccion.id}"></c:out></td>
						<td><c:out value="${atraccion.nombre}"></c:out></td>
						<td><c:out value="${atraccion.costo}"></c:out></td>
						<td><c:out value="${atraccion.tiempo}"></c:out></td>
						<td><c:out value="${atraccion.cupo}"></c:out></td>
						<td><c:out value="${atraccion.tipo}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>
		<jsp:include page="/partials/footer.jsp"></jsp:include>
	
</body>
</html>


