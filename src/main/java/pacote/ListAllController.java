package pacote;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;
import service.ProductService;
import service.TypeService;
import util.DBUtil;

@WebServlet(name="listAllController", urlPatterns = {"/ListAllController"})
public class ListAllController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String LIST_ALL = "/WEB-INF/view/listAll/listAll.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
        	Connection conn = DBUtil.getConnection();
        	
			req.setAttribute("movements", MovementService.getAllMovements(conn));
			req.setAttribute("products", ProductService.getAllProducts(conn));
			req.setAttribute("types", TypeService.getAllTypes(conn));
			req.setAttribute("MOVPROD", " active");
			

			RequestDispatcher view = req.getRequestDispatcher(LIST_ALL);
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Get all movements and products: " + ex.getMessage());
		}
	}
}
