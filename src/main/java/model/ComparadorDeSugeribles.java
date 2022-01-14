package model;

import java.util.Comparator;

public class ComparadorDeSugeribles implements Comparator<Sugerible> {
	private User usuario;

	public ComparadorDeSugeribles(User usuario) {
		this.usuario = usuario;

	}

	
	public int compare(Sugerible s1, Sugerible s2) {
		if (usuario.getTipo() == s1.getTipo() // Por tipo
				&& usuario.getTipo() != s2.getTipo())
			return -1;
		else if (usuario.getTipo() != s1.getTipo() && usuario.getTipo() == s2.getTipo())
			return 1;

		else if (s1.esPromocion() && !s2.esPromocion()) // Por tipo de sugerible
			return -1;

		else if (!s1.esPromocion() && s2.esPromocion())
			return 1;

		else if (s1.getCosto() > s2.getCosto()) // Por precio
			return -1;

		else if (s1.getCosto() < s2.getCosto())
			return 1;

		else // Por tiempo
			return Double.compare(s2.getTiempo(), s1.getTiempo());
	}

}
