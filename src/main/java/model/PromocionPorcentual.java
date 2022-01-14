package model;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	
	private int precioFinal;
	private int descuento;
	
	
	public PromocionPorcentual(Integer id, String nombreDeLaPromo, TipoDeAtraccion tipo, 
			int descuento, String beneficio, Boolean active) {
		super(id, nombreDeLaPromo, tipo, beneficio, active); 
		this.descuento = descuento;
	}
	
	public int getPrecioDesc() {
		return this.descuento;
	}


	
	public Integer getCosto() {
		double precioARedondear = 0;
		precioFinal = 0;
		for (int i = 0; i < super.getAtracciones().size(); i++) {
			precioFinal += super.getAtracciones().get(i).getCosto();
			precioARedondear = -((precioFinal*this.descuento/100)-precioFinal);			

		}
		return  (int) Math.round(precioARedondear);
	}


	
	public String toString() {
		String nombreDeLasAtracciones = ""; 
		for(Atraccion a:super.getAtracciones()) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionPorcentual: " + nombreDeLaPromo + ", Tipo: " + tipo + ", Porcentaje De Descuento: " + this.descuento + "%"
				+ "\n" +"   Atracciones Incluidas: " + nombreDeLasAtracciones + "\n"
				+ "   Tiempo Total: " + this.getTiempo() + ", Precio Final: " + this.getCosto() +  "\n"  + 
				"   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n" ;
				
	}


	

	
	
	public int getAhorro() {
		int precioReal= 0;
		for(Atraccion a:super.getAtracciones()) {
		precioReal += a.getCosto();
		} 
		return  (precioReal - this.getCosto());
	}

	@Override
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}

}
