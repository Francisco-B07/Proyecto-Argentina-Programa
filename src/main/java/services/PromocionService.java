package services;

import java.util.List;

import model.*;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromocionService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}
	

	public Promocion create(String nombre, TipoDeAtraccion tipo, Integer precioODesc, String beneficio, Boolean active) {
		Promocion promocion= null;

			if(beneficio.equals("AxB")) {
				 promocion = new PromocionAxB(-1, nombre, tipo, DAOFactory.getAtraccionDAO().find((Integer) precioODesc), beneficio, active);

			} 
			if(beneficio.equals("Porcentual")) {
				 promocion = new PromocionPorcentual(-1, nombre, tipo, precioODesc, beneficio, active);	
			}
			if(beneficio.equals("Absoluta")){
				promocion = new PromocionAbsoluta(-1, nombre, tipo, precioODesc, beneficio, active);
			}
			
			PromocionDAO PromocionDAO = DAOFactory.getPromocionDAO();
			PromocionDAO.insert(promocion);
			return promocion;
	}
		

		/*if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}
*/
	public Promocion Update(Integer id, String nombre, TipoDeAtraccion tipo, Integer precioODesc, String beneficio, Boolean active) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(nombre);
		promocion.setTipo(tipo);
		promocion.setPrecioODesc(precioODesc);
		promocion.setActive(active);

//		if (atraccion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
//		}

		return promocion;
	}


	public Promocion update(Integer id, String nombre, TipoDeAtraccion tipo, Integer precioODesc, String beneficio, Boolean active) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(nombre);
		promocion.setTipo(tipo);
		promocion.setPrecioODesc(precioODesc);
		promocion.setActive(active);

		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public void delete(Integer id) {

		Promocion promocion = new PromocionAbsoluta (id, null, null, null, null, null);
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.find(id);
	}

}