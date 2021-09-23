package pacote;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;

@WebServlet(name="productController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ProductService productService;
	
	private String LIST_PRODUCT = "/WEB-INF/view/product/product.jsp";
	private String INSERT_PRODUCT = "/WEB-INF/view/product/product_new.jsp";
	private String EDIT_PRODUCT = "/WEB-INF/view/product/product_edit.jsp";
	
	public ProductController() {
		productService = new ProductService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String action = req.getParameter("action");
	        
	        if(action != null) {
	        	if (action.equalsIgnoreCase("saveProduct")) {
	        		productService.editProduct(req);
	        	}
	        }
	         else {
        		productService.insertProduct(req, resp);
        	}

	        setProdActive(req);
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
	        	else if (action.equalsIgnoreCase("editProduct")) {
	        		String barcode = req.getParameter("barcode");
	        		forward = EDIT_PRODUCT;
	        		req.setAttribute("prod", productService.getProductByBarcode(req, barcode));
	        	} else if (action.equalsIgnoreCase("deleteProduct")) {
	        		forward = LIST_PRODUCT;
	        		productService.deleteProduct(req);
					req.setAttribute("products", productService.getAllProducts(req));
	        	}
	        } else {
				req.setAttribute("products", productService.getAllProducts(req));
				forward = LIST_PRODUCT;
			}
			
	        setProdActive(req);
			RequestDispatcher view = req.getRequestDispatcher(forward);
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Get product: " + ex.getMessage());
		}
	}
	
	private void setProdActive(HttpServletRequest req) {
		req.setAttribute("PROD", " active");
	}
}
