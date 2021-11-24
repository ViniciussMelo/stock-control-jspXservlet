package pacote;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;
import util.DBUtil;

@WebServlet(name="productController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private String LIST_PRODUCT = "/WEB-INF/view/product/product.jsp";
	private String INSERT_PRODUCT = "/WEB-INF/view/product/product_new.jsp";
	private String EDIT_PRODUCT = "/WEB-INF/view/product/product_edit.jsp";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String action = req.getParameter("action");
        	Connection conn = DBUtil.getConnection();
	        
	        if(action != null) {
	        	if (action.equalsIgnoreCase("saveProduct")) {
		     		int barcode = Integer.parseInt(req.getParameter("barcode"));
		    		String name = req.getParameter("name");
		    		Double price = Double.parseDouble(req.getParameter("price"));
		    		
		    		Product product = new Product(barcode, name, price);
		    		
	        		ProductService.updateProduct(conn, product);
	        	}
	        }
	         else {
	     		int barcode = Integer.parseInt(req.getParameter("barcode"));
	    		String name = req.getParameter("name");
	    		Double price = Double.parseDouble(req.getParameter("price"));
	    		
	    		Product product = new Product(barcode, name, price);
	    		
        		ProductService.insertProduct(conn, product);
        	}

	        setProdActive(req);
			RequestDispatcher view = req.getRequestDispatcher(LIST_PRODUCT);
			req.setAttribute("products", ProductService.getAllProducts(conn));
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Post product: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String forward = "";
	        String action = req.getParameter("action");
        	Connection conn = DBUtil.getConnection();
	        
	        if(action != null) {
	        	if (action.equalsIgnoreCase("insertProduct")) {
					forward = INSERT_PRODUCT;
				}
	        	else if (action.equalsIgnoreCase("editProduct")) {
	        		int barcode = Integer.parseInt(req.getParameter("barcode"));
	        		forward = EDIT_PRODUCT;
	        		
	        		req.setAttribute("prod", ProductService.getProductByBarcode(conn, barcode));
	        	} else if (action.equalsIgnoreCase("deleteProduct")) {
	        		int barcode = Integer.parseInt(req.getParameter("barcode"));
	        		
	        		forward = LIST_PRODUCT;
	        		ProductService.deleteProductByBarcode(conn, barcode);
					req.setAttribute("products", ProductService.getAllProducts(conn));
	        	}
	        } else {
	        	List<Product> prods = ProductService.getAllProducts(conn);
				req.setAttribute("products", prods);
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
