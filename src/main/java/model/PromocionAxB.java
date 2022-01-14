package model;

import java.util.List;

public class PromocionAxB extends Promocion {
	// la atraccion gratis la pasamos por archivo (la ultima atraccion)

	private int precioFinal;
	
	private Atraccion atraccionGratis;

	public PromocionAxB(Integer id, String nombreDeLaPromo, 
			TipoDeAtraccion tipo, Atraccion atraccionGratis, String beneficio, Boolean active){// List<Atraccion> atracciones) {
		super(id, nombreDeLaPromo, tipo, beneficio, active); //atracciones); estoy probando retirando la lista de atracciones de la promocion
		
//		atracciones.remove(this.atraccionGratis);
//		this.atracciones = atracciones;
		this.atraccionGratis = atraccionGratis;
	
		
	}

	
	public Integer getCosto() {
		precioFinal = 0;
		
//		for (int i = 0; i < super.atracciones.size(); i++) {
//			if (super.atracciones.get(i).getNombre() != atraccionGratis.getNombre())
//				precioFinal += super.atracciones.get(i).getCosto();
//		}
		
		 for(Atraccion a : super.getAtracciones()) {
			precioFinal += a.getCosto();
		}
		 precioFinal -= this.atraccionGratis.getCosto();
		return this.precioFinal;
	}

	
	public String toString() {
		String nombreDeLasAtracciones = "";
		for (Atraccion a : super.getAtracciones()) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionAxB: " + nombreDeLaPromo + ", Tipo : " + tipo + "\n " 
				+ "  Atracciones Incluidas: " + atraccionGratis.getNombre()
				+ ", " + nombreDeLasAtracciones + "\n " 
				+ "  Tiempo Total: " + this.getTiempo() + ", Precio Total: "
				+ this.getCosto() + "\n"  + "   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n";
	}
	
	
	public Double getTiempo() {
		
		tiempoTotal = (double) 0;
		for (int i = 0; i < super.getAtracciones().size(); i++) {
			tiempoTotal += super.getAtracciones().get(i).getTiempo();
		}
		return (Double)tiempoTotal;
	}
	
	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}
	
	
	public boolean esOContiene(Sugerible s) {
		if(this.atraccionGratis.equals(s)) {
			return true;
		}
		else for (Atraccion a : super.getAtracciones()) {
			if (a.equals(s))
				return true;
		}
		return false;
	}
	
	
	public int getAhorro() {
		return this.atraccionGratis.getCosto();
	}
	
	
	public void restarCupo() {
		super.restarCupo();
		this.atraccionGratis.restarCupo();
		
		}
	
	public boolean hayCupo() {
		for (Atraccion a : super.getAtracciones()) {
			if (!a.hayCupo()) //|| !this.atraccionGratis.hayCupo())
				return false;
		}		
		return true;
	}

	

}
