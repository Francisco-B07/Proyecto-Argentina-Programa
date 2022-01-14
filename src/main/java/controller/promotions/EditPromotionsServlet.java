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

@WebServlet("/promotions/edit.do")
public class EditPromotionsServlet extends HttpServlet {

	
	private static final long serialVersionUID = 5330255351527869549L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promocion promocion = promocionService.find(id);
		req.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("name");
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo"));
		Integer precioODesc = Integer.parseInt(req.getParameter("cost"));
		String beneficio = req.getParameter("beneficio");
		Boolean active = Boolean.parseBoolean(req.getParameter("active"));
		
		Promocion promocion = promocionService.update(id, nombre, tipo, precioODesc, beneficio, active);
		
		if (promocion.isValid()) {
			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
