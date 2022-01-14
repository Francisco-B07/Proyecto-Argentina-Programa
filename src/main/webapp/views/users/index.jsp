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
			<h1>Estos son todos los usuarios registrados</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/users/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nuevo usuario
				</a>
			</div>
		</c:if>
		
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Id</th>
					<th>Monedas</th>
					<th>Tiempo</th>
					<th>Favorita</th>
					<th>EsAdmin</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<c:if test="${user.isActive()}">
					<tr>
						<td><strong><c:out value="${user.username}"></c:out></strong>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Cras pretium eros urna. Sed quis erat congue, bibendum tortor
								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.coins}"></c:out></td>
						<td><c:out value="${user.time}"></c:out></td>
						<td><c:out value="${user.tipo}"></c:out></td>
						<td><c:out value="${user.admin}"></c:out></td>
						<td>
								<a href="/turismo/users/edit.do?id=${user.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/users/delete.do?id=${user.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		
		
		

	</main>
		<jsp:include page="/partials/footer.jsp"></jsp:include>
	
</body>
</html>