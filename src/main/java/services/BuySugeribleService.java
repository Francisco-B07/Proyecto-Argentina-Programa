package services;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Promocion;
import model.Sugerible;
import model.User;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImpl;

public class BuySugeribleService {

	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer sugeribleId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Sugerible sugerible = itinerarioDAO.find(sugeribleId);

		if (!sugerible.hayCupo()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(sugerible)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(sugerible)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.comprarSugerible(sugerible);

			// no grabamos para no afectar la base de pruebas
			itinerarioDAO.
			userDAO.update(user);
		}

		return errors;

	}

}
