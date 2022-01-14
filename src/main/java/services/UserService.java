package services;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.TipoDeAtraccion;
import model.User;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.ItinerarioDAOImpl;

public class UserService {

	public List<User> list() {
		return DAOFactory.getUserDAO().findAll();
	}
	
	public List<Atraccion> findAtraccionesCompradas(Integer id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAtraccionesCompradas(id);
		return atracciones;
	}
	
	public List<Promocion> findPromocionesCompradas(Integer id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> promociones = promocionDAO.findPromocionesCompradas(id);
		return promociones;
	}

	public User create(String username, String password, Boolean admin, Integer coins, Double time, TipoDeAtraccion tipo, Boolean active) {

		User user = new User(-1, username, password, coins, time, admin, tipo, active);
		user.setPassword(password);
		

		if (user.isValid()) {
			UserDAO userDAO = DAOFactory.getUserDAO();
			userDAO.insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public User update(Integer id, String username,  String password, Integer coins, Double time, Boolean admin, Boolean active) {

		UserDAO userDAO = DAOFactory.getUserDAO();
		User user = userDAO.find(id);
		
		user.setUsername(username);
		user.setPassword(password);
		user.setCoins(coins);
		user.setTime(time);
		user.setAdmin(admin);
		user.setActive(active);

		if (user.isValid()) {
			userDAO.update(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public void delete(Integer id) {
		User user2 = new User(id, null, null, null, null, null, null, null);

		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.delete(user2);
	}

	public User find(Integer id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		return userDAO.find(id);
	}

}
