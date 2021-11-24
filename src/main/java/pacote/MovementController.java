package pacote;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movement;
import service.MovementServiceV2;
import service.ProductService;
import service.ProductServiceV2;

@WebServlet(name="MovementController", urlPatterns = {"/MovementController"})
public class MovementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
	private String LIST_MOVEMENT = "/WEB-INF/view/movement/movement.jsp";
	private String INSERT_MOVEMENT = "/WEB-INF/view/movement/movement_new.jsp";
	private String EDIT_MOVEMENT = "/WEB-INF/view/movement/movement_edit.jsp";
	
	public MovementController() {
		productService = new ProductService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
        	Connection conn = DBUtil.getConnection();
			String action = req.getParameter("action");
			
			if(action == null) {
				int productId = Integer.parseInt(req.getParameter("productId"));
				String type = req.getParameter("type");
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				
				//Movement movement = new Movement(productId, type, quantity);	
				
				//MovementServiceV2.insertMovement(conn, movement);
			} else {
				if (action.equalsIgnoreCase("saveMovement")) {
	        		//movementService.editMovement(req);
	        	}
			}
			
	        setMovActive(req);
			RequestDispatcher view = req.getRequestDispatcher(LIST_MOVEMENT);
			req.setAttribute("movements", MovementServiceV2.getAllMovements(conn));
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
        	Connection conn = DBUtil.getConnection();
			
	        if(action == null) {
	        	List<Movement> aaa = MovementServiceV2.getAllMovements(conn);
	        	
				req.setAttribute("movements", aaa);
				forward = LIST_MOVEMENT;	        	
	        } else {
	        	if (action.equalsIgnoreCase("insertMovement")) {
					forward = INSERT_MOVEMENT;
					req.setAttribute("products", ProductServiceV2.getAllProducts(conn));
				}
	        	else if (action.equalsIgnoreCase("editMovement")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		Movement mov = MovementServiceV2.getMovementById(conn, id);
	        		
					req.setAttribute("mov", mov);
	        		forward = EDIT_MOVEMENT;
	        	}  else if (action.equalsIgnoreCase("deleteMovement")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		
	        		forward = LIST_MOVEMENT;
	        		
	        		MovementServiceV2.deleteMovementById(conn, id);
	        		req.setAttribute("movements", MovementServiceV2.getAllMovements(conn));
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
