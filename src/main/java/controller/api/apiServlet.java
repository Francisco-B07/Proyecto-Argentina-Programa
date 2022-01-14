package controller.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ClimateApi;

@WebServlet("/clima/clima.jsp")
public class apiServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -5684797560896795410L;

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request =HttpRequest
				.newBuilder()
				.uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=La%20Plata"))
				.header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
				.header("x-rapidapi-key", "8fdd811421msh8e5b669f777b565p1b4253jsn929d16054621")
				.GET()
				.build(); 
		
		HttpResponse<String> response=null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	
		String json = response.body();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ClimateApi l = gson.fromJson(json, ClimateApi.class);
		
		Double temperatura = (Double) l.getCurrent().get("temp_c");
		req.setAttribute("temperatura", temperatura);
		String ciudad = (String) l.getLocation().get("name");
		req.setAttribute("ciudad", ciudad);
		String icono = (String) l.getCondition(l).get("icon");
		req.setAttribute("icono", icono);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/clima/clima.jsp");
		dispatcher.forward(req, resp);
	}
	

}
