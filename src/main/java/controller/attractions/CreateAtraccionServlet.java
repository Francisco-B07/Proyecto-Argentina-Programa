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

@WebServlet("/attractions/create.do")
public class CreateAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Integer cost = Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo"));
		// TODO cambiar el input por desplegable
		Boolean active = Boolean.parseBoolean(req.getParameter("active"));

		Atraccion atraccion = atraccionService.create(name, cost, duration, capacity, tipo, active);
		
		if (atraccion.isValid()) {

			resp.sendRedirect("/turismo/attractions/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
