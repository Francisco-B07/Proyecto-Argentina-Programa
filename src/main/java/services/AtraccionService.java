package services;

import java.util.List;

import model.Atraccion;
import model.TipoDeAtraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}


	public Atraccion create(String nombre, Integer costo, Double tiempo, Integer cupo, TipoDeAtraccion tipo, Boolean active) {

		Atraccion atraccion = new Atraccion(-1, nombre, costo, tiempo, cupo, tipo, active);


		if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public Atraccion update(Integer id, String nombre, Integer costo, Double tiempo, Integer cupo, TipoDeAtraccion tipo) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.find(id);

		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setTiempo(tiempo);
		atraccion.setCupo(cupo);
		atraccion.setTipo(tipo);

		if (atraccion.isValid()) {
			atraccionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(Integer id) {

		Atraccion atraccion = new Atraccion(id, null, null, null, null, null, null);
		AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		attractionDAO.delete(atraccion);
	}

	public Atraccion find(Integer id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.find(id);
	}

}
