package controller.sugerible;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import persistence.commons.DAOFactory;
import services.BuyAtraccionService;
import services.BuySugeribleService;

@WebServlet("/sugeribles/buy.do")
public class BuySugeribleServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuySugeribleService buySugeribleService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buySugeribleService = new BuySugeribleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer sugeribleId = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		Map<String, String> errors = buySugeribleService.buy(user.getId(), sugeribleId);
		
		User user2 = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("success", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/sugeribles/index.do");
		dispatcher.forward(req, resp);
	}
}
