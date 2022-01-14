package services;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.User;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyAtraccionService {

	AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Atraccion atraccion = attractionDAO.find(attractionId);

		if (!atraccion.hayCupo()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(atraccion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(atraccion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.comprarSugerible(atraccion);

			// no grabamos para no afectar la base de pruebas
			
			attractionDAO.update(atraccion);
			userDAO.update(user);
			itinerarioDAO.insert(user);
		}

		return errors;

	}

}
