package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public abstract class Promocion implements Sugerible {
	public void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}

	protected Integer id;
	protected TipoDeAtraccion tipo;
	protected Integer precioODesc;
	protected String beneficio;
	protected Double tiempoTotal;
	protected String nombreDeLaPromo;
	private Boolean active;
	private Map<String, String> errors;
	

	public Promocion(Integer id, String nombreDeLaPromo, TipoDeAtraccion tipo, String beneficio, Boolean active) {
		this.id = id;
		this.nombreDeLaPromo = nombreDeLaPromo;
		this.tipo = tipo;
		this.beneficio = beneficio;
		this.active = active;
		//this.atracciones = atracciones;
	}

	

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

	}
	
	public boolean esPromocion() {
		return true;
	}

	
	public boolean hayCupo() {
		List<Atraccion> atracciones = getAtracciones();
		for (Atraccion a : atracciones) {
			if (!a.hayCupo())
				return false;
		}		
		return true;
	}
	
	
	public void restarCupo() {
		List<Atraccion> atracciones = getAtracciones();
		for (Atraccion a : atracciones) {
			a.restarCupo();
		}
	}

	
	public Double getTiempo() {
		List<Atraccion> atracciones = getAtracciones();
		tiempoTotal = (double) 0;
		for (int i = 0; i < atracciones.size(); i++) {
			tiempoTotal += atracciones.get(i).getTiempo();
		}
		return (Double)tiempoTotal;
	}

	
	public TipoDeAtraccion getTipo() {
		return this.tipo;
	}

	
	public boolean esOContiene(Sugerible s) {
		List<Atraccion> atracciones = getAtracciones();
		for (Atraccion a : atracciones) {
			if (a.equals(s))
				return true;
		}
		return false;
	}

	public List<Atraccion> getAtracciones() {
		 AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAllByPromoId(id);
		return atracciones;
	}
	
	public String getNombreAtracciones() {
		 AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAllByPromoId(id);
		String nombreDeLasAtracciones = ""; 
		for(Atraccion a:atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return nombreDeLasAtracciones;
	}
	
	public abstract int getAhorro();

	public String getBeneficio() {
		return this.beneficio;
	}


	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public boolean isActive() {
		return active;
	}


	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombreDeLaPromo;
	}
	
	public void setPrecioODesc(Integer precioODesc) {
		this.precioODesc = precioODesc;
	}


	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}


	public void setNombre(String nombreDeLaPromo) {
		this.nombreDeLaPromo = nombreDeLaPromo;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}

}