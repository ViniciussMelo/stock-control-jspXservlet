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

@WebServlet(name="MovementController", urlPatterns = {"/MovementController"})
public class MovementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovementService movementService;
	private ProductService productService;
	
	private String LIST_MOVEMENT = "/WEB-INF/view/movement/movement.jsp";
	private String INSERT_MOVEMENT = "/WEB-INF/view/movement/movement_new.jsp";
	private String EDIT_MOVEMENT = "/WEB-INF/view/movement/movement_edit.jsp";
	
	public MovementController() {
		movementService = new MovementService();
		productService = new ProductService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			
			if(action == null) {
				movementService.insertMovement(req);
			} else {
				if (action.equalsIgnoreCase("saveMovement")) {
	        		movementService.editMovement(req);
	        	}
			}
			
	        setMovActive(req);
			RequestDispatcher view = req.getRequestDispatcher(LIST_MOVEMENT);
			req.setAttribute("movements", movementService.getAllMovements(req));
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Post movement: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String forward = "";
	        String action = req.getParameter("action");
			
	        if(action == null) {
				req.setAttribute("movements", movementService.getAllMovements(req));
				forward = LIST_MOVEMENT;	        	
	        } else {
	        	if (action.equalsIgnoreCase("insertMovement")) {
					forward = INSERT_MOVEMENT;
					req.setAttribute("products", productService.getAllProducts(req));
				}
	        	else if (action.equalsIgnoreCase("editMovement")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
					req.setAttribute("mov", movementService.getMovementById(req, id));
	        		forward = EDIT_MOVEMENT;
	        	}  else if (action.equalsIgnoreCase("deleteMovement")) {
	        		forward = LIST_MOVEMENT;
	        		movementService.deleteMovement(req);
	        		req.setAttribute("movements", movementService.getAllMovements(req));
	        	}
	        }
	        
	        setMovActive(req);
			RequestDispatcher view = req.getRequestDispatcher(forward);
			view.forward(req, resp);	        
		} catch (Exception ex) {
			System.out.println("Get movement: " + ex.getMessage());
		}
	}
	
	private void setMovActive(HttpServletRequest req) {
		req.setAttribute("MOV", " active");
	}
}
