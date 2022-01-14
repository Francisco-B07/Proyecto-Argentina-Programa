package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TipoDeAtraccion;
import model.User;
import services.AtraccionService;
import services.UserService;

@WebServlet("/users/create.do")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 6711034380731447398L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Integer coins = Integer.parseInt(req.getParameter("coins"));
		Double time = Double.parseDouble(req.getParameter("time"));
		Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo"));
		Boolean active = Boolean.parseBoolean(req.getParameter("active"));
	
		User user = userService.create(username, password, admin, coins, time, tipo, active);
		if (user.isValid()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("user", user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		}

	}
	

}
