package model;

import java.util.List;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(Integer id, String nombreDeLaPromo, TipoDeAtraccion tipo, Integer precioODesc,
			String beneficio, Boolean active) {
		super(id, nombreDeLaPromo, tipo, beneficio, active);
		this.precioODesc = precioODesc;
	}

	public Integer getCosto() {
		return this.precioODesc;
	}

	public String toString() {
		String nombreDeLasAtracciones = "";
		for (Atraccion a : super.getAtracciones()) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionAbsoluta: " + nombreDeLaPromo + ", Tipo: " + tipo + "\n" + "   Atracciones Incluidas: "
				+ nombreDeLasAtracciones + "\n" + "   Tiempo Total: " + this.getTiempo() + ", Precio Total: "
				+ this.precioODesc + "\n" + "   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n";
	}

	@Override
	public int getAhorro() {
		int precioReal = 0;
		for (Atraccion a : super.getAtracciones()) {
			precioReal += a.getCosto();
		}
		return precioReal - this.precioODesc;
	}

	@Override
	public Atraccion getAtraccionGratis() {
		// TODO Auto-generated method stub
		return null;
	}

}
