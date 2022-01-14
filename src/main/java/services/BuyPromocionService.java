package services;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Promocion;
import model.User;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.PromocionDAOImpl;

public class BuyPromocionService {
	AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer promocionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Promocion promocion = promocionDAO.find(promocionId);

		if (!promocion.hayCupo()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.comprarSugerible(promocion);

			// no grabamos para no afectar la base de pruebas
			PromocionDAOImpl.updateCupo(promocion);
			userDAO.update(user);
			itinerarioDAO.insert(user);
		}

		return errors;

	}

}
