<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre: </label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${promocion.getNombre()}">
	</div>
	<div class="mb-3">
  			<label for="tipo"
			class='col-form-label'>Tipo: </label>
 				 <select class="form-select" id="tipo" name="tipo">
   					 <option selected value="AVENTURA">AVENTURA</option>
   					 <option value="PAISAJE">PAISAJE</option>
   					 <option value="DEGUSTACION">DEGUSTACION</option>
  				</select>
  				<div class="invalid-feedback">
		</div>
		</div>
		<div class="mb-3">
  			<label for="beneficio"
			class='col-form-label'>Beneficio: </label>
 				 <select class="form-select" id="beneficio" name="beneficio">
   					 <option selected value="Absoluta">Absoluta</option>
   					 <option value="AxB">AxB</option>
   					 <option value="Porcentual">Porcentual</option>
  				</select>
  				<div class="invalid-feedback">
		</div>
		</div>
		
	<div class="mb-3">
		<label for="cost"
			class='col-form-label'>Precio o descuento: </label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value=""></input>
		<div class="invalid-feedback">
		</div>
	</div>
	
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
