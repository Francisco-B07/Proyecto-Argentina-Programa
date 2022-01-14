package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.TipoDeAtraccion;
import services.AtraccionService;

@WebServlet("/attractions/edit.do")
public class EditAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Atraccion atraccion = atraccionService.find(id);
		req.setAttribute("attraction", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Integer cost = Integer.parseInt(req.getParameter("cost"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo"));

		Atraccion atraccion = atraccionService.update(id, name, cost, duration, capacity, tipo);

		if (atraccion.isValid()) {
			resp.sendRedirect("/turismo/attractions/index.do");
		} else {
			req.setAttribute("attraction", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
