package controller.sugerible;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.ComparadorDeSugeribles;
import model.Promocion;
import model.Sugerible;
import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.UserDAOImpl;
import services.AtraccionService;
import services.LoginService;
import services.PromocionService;

@WebServlet("/sugeribles/index.do")
public class ListSugeribleServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AtraccionService atraccionService;
	private PromocionService promocionService;
	private LoginService loginService;


	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = atraccionService.list();
		List<Promocion> promociones = promocionService.list();
		List<Sugerible> sugeribles = new LinkedList<Sugerible>();
		
		UserDAO userDao = DAOFactory.getUserDAO();
    	User user = userDao.findByUsername(req.getRemoteUser());
		
		sugeribles.addAll(atracciones);
		sugeribles.addAll(promociones);
		sugeribles.sort(new ComparadorDeSugeribles(user));
		
		req.setAttribute("sugeribles", sugeribles);


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/sugeribles/index.jsp");
		dispatcher.forward(req, resp);

	}
	 
}
