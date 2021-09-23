package pacote;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="homeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String HOME = "/index.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("HOME", " active");
		RequestDispatcher view = req.getRequestDispatcher(HOME);
		view.forward(req, resp);
	}
}
