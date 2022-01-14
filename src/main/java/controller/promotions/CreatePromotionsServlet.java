package controller.promotions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import model.TipoDeAtraccion;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/promotions/create.do")
public class CreatePromotionsServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo"));
		String beneficio = req.getParameter("beneficio");
		Integer precioODesc = Integer.parseInt(req.getParameter("cost"));
		Boolean active = Boolean.parseBoolean(req.getParameter("active"));

		Promocion promocion = promocionService.create(name, tipo, precioODesc, beneficio, active);

		if (promocion.isValid()) {

			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			req.setAttribute("promocion", promocion);


			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}