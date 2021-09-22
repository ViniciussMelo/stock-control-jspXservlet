package pacote;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

@WebServlet(name="productController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ProductService productService;
	
	private String LIST_PRODUCT = "/listProduct.jsp";
	private String INSERT_PRODUCT = "/insertProduct.jsp";
	
	public ProductController() {
		productService = new ProductService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			productService.insertProduct(req, resp);
			
			RequestDispatcher view = req.getRequestDispatcher(LIST_PRODUCT);
			req.setAttribute("products", productService.getAllProducts(req));
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Post product: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String forward="";
	        String action = req.getParameter("action");
	        
	        if(action != null) {
	        	if (action.equalsIgnoreCase("insertProduct")) {
					forward = INSERT_PRODUCT;
				}
	        } else {
				req.setAttribute("products", productService.getAllProducts(req));
				forward = LIST_PRODUCT;
			}
			
			RequestDispatcher view = req.getRequestDispatcher(forward);
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Get product: " + ex.getMessage());
		}
	}
}
