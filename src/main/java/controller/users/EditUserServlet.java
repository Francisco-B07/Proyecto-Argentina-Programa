package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;



@WebServlet("/users/edit.do")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = -5552207654449803089L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		User user = userService.find(id);
		req.setAttribute("user", user);


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Integer coins = Integer.parseInt(req.getParameter("coins"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		
		Double time = Double.parseDouble(req.getParameter("time"));
		Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
		Boolean active = Boolean.parseBoolean(req.getParameter("active"));
		
		User user = userService.update(id, username, password, coins, time, admin, active);


		if (user.isValid()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("user", user);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
