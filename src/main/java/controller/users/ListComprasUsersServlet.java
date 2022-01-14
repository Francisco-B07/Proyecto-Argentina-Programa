package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import model.User;
import services.AtraccionService;
import services.UserService;

@WebServlet("/users/comprado.do")
public class ListComprasUsersServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 8457064829349092340L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		List<Atraccion> atracciones = userService.findAtraccionesCompradas(user.getId());
		List<Promocion> promociones = userService.findPromocionesCompradas(user.getId());
		
		

		req.setAttribute("atracciones", atracciones);
		req.setAttribute("promociones", promociones);
		
		List<Atraccion> atraccionesPorUsuario = null;
		List<Promocion> promocionesPorUsuario = null;
		List<User> users = userService.list();
		for (User user2 : users) {
			atraccionesPorUsuario = userService.findAtraccionesCompradas(user2.getId());
			promocionesPorUsuario = userService.findPromocionesCompradas(user2.getId());
		}
		req.setAttribute("atraccionesPorUsuario", atraccionesPorUsuario);
		req.setAttribute("promocionesPorUsuario", promocionesPorUsuario);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/comprado.jsp");
		dispatcher.forward(req, resp);

	}

}
