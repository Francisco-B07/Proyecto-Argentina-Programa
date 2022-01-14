package model;

import java.util.LinkedList;
import java.util.List;

public interface Sugerible {
List <Sugerible> atraccionComprada = new LinkedList<Sugerible>();

	
	
	public Integer getCosto();
	
	public TipoDeAtraccion getTipo();
	
	public Double getTiempo();
	
	public boolean hayCupo();
	
	public boolean esPromocion();

	public boolean esOContiene(Sugerible sugerencia);

	public void restarCupo();
	
	@Override
	public String toString();

	public Integer getId();

	public Atraccion getAtraccionGratis();


}
