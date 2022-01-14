package model;
import java.util.HashMap;
import java.util.Map;

import java.util.Objects;

public class Atraccion implements Sugerible {
	private int id;
	protected String nombre;
	private Integer costo;
	private Double tiempo;
	private Integer cupo;
	protected TipoDeAtraccion tipo;
	private Boolean active;
	private Map<String, String> errors;
	
	public Atraccion(Integer id, String nombre, Integer costo, Double tiempo, Integer cupo, TipoDeAtraccion tipo, Boolean active){

		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
		this.active = active;

	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	public void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("costo", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errors.put("tiempo", "Debe ser positivo");
		}
		if (cupo <= 0) {
			errors.put("cupo", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	
	public Atraccion(String nombre) {
		this.nombre = nombre;
		
	}

	public Integer getCupo() {
		return cupo;
	}
	
	public Integer getId() {
		return this.id;
	}

	
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	
	public void restarCupo() {
		this.cupo--;
	}

	
	public Double getTiempo() {
		return this.tiempo;
	}

	
	public Integer getCosto() {
		return this.costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	
	public TipoDeAtraccion getTipo() {
		return this.tipo;
	}

	
	public boolean esPromocion() {
		return false;
	}

	
	public String toString() {
		return "Atraccion " + nombre + ", Tiempo: " + tiempo + ", Costo: " + costo + ", Cupo: " + cupo +  ", Tipo: " + tipo + "\n";
	}

	
	public boolean esOContiene(Sugerible sugerencia) {
		return this.equals(sugerencia);
	}

	
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipo);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}

	
	public boolean isActive() {
		return active;
	}

	@Override
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}
}
