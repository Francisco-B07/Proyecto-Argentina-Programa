<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">

		<label for="username" class="col-form-label">Username:</label> <input
			type="text" class="form-control" id="username" name="username"
			required value="${tmp_user.username}">
	</div>

	<div class="mb-3">
		<label for="password"
			class='col-form-label ${tmp_user.errors.get("password") != null ? "is-invalid" : "" }'>Contraseņa:</label>
		<input class="form-control" type="password" id="password" name="password" required
			value="${tmp_user.password}"></input>
		<div class="invalid-feedback">
			<c:out value='${tmp_user.errors.get("password")}'></c:out>
		</div>
	</div>


	<div class="mb-3">
		<label for="coins"
			class='col-form-label ${tmp_user.errors.get("coins") != null ? "is-invalid" : "" }'>Monedas:</label>
		<input class="form-control" type="number" id="coins" name="coins"
			required value="${tmp_user.coins}"></input>
		<div class="invalid-feedback">
			<c:out value='${tmp_user.errors.get("coins")}'></c:out>
		</div>
	</div>



	<div class="mb-3">
		<label for="time"
			class='col-form-label ${tmp_user.errors.get("time") != null ? "is-invalid" : "" }'>Tiempo:</label>
		<input class="form-control" type="number" id="time" name="time"
			required value="${tmp_user.time}"></input>
		<div class="invalid-feedback">
			<c:out value='${tmp_user.errors.get("time")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
  			<label for="tipo"
			class='col-form-label ${atraccion.errors.get("duration") != null ? "is-invalid" : "" }'>Tipo:</label>
 				 <select class="form-select" id="tipo" name="tipo">
   					 <option selected value="AVENTURA">AVENTURA</option>
   					 <option value="PAISAJE">PAISAJE</option>
   					 <option value="DEGUSTACION">DEGUSTACION</option>
  				</select>
  				<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tipo")}'></c:out>
		</div>
		</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
