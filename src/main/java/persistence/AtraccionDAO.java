package persistence;

import java.util.List;

import model.Atraccion;
import persistence.commons.GenericDAO;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public List<Atraccion> findAllByPromoId(Integer id);
	public List<Atraccion> findAtraccionesCompradas(Integer id);
}
