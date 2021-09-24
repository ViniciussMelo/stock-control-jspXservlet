package pacote;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;
import service.ProductService;

@WebServlet(name="productsAndMovementsController", urlPatterns = {"/ProductsAndMovementsController"})
public class ProductsAndMovementsController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private MovementService movementService;
	
	private String LIST_ALL = "/WEB-INF/view/listAll/listAll.jsp";

	public ProductsAndMovementsController() {
		movementService = new MovementService();
		productService = new ProductService();		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("movements", movementService.getAllMovements(req));
			req.setAttribute("products", productService.getAllProducts(req));
			req.setAttribute("MOVPROD", " active");
			

			RequestDispatcher view = req.getRequestDispatcher(LIST_ALL);
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Get all movements and products: " + ex.getMessage());
		}
	}
}
