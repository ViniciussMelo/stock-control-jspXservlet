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
import service.MovementService;
import service.ProductService;
import service.TypeService;

@WebServlet(name="MovementController", urlPatterns = {"/MovementController"})
public class MovementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String LIST_MOVEMENT = "/WEB-INF/view/movement/movement.jsp";
	private String INSERT_MOVEMENT = "/WEB-INF/view/movement/movement_new.jsp";
	private String EDIT_MOVEMENT = "/WEB-INF/view/movement/movement_edit.jsp";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
        	Connection conn = DBUtil.getConnection();
			String action = req.getParameter("action");
			
			if(action == null) {
				int productId = Integer.parseInt(req.getParameter("productId"));
				int typeId = Integer.parseInt(req.getParameter("typeId"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				
				Movement movement = new Movement(productId, typeId, quantity);
				
				MovementService.insertMovement(conn, movement);
			} else {
				if (action.equalsIgnoreCase("saveMovement")) {
					int id = Integer.parseInt(req.getParameter("id"));
					int productId = Integer.parseInt(req.getParameter("productId"));
					int typeId = Integer.parseInt(req.getParameter("typeId"));
					int quantity = Integer.parseInt(req.getParameter("quantity"));
					
					Movement movement = new Movement(id, productId, typeId, quantity);
					
	        		MovementService.updateMovement(conn, movement);
	        	}
			}
			
	        setMovActive(req);
			RequestDispatcher view = req.getRequestDispatcher(LIST_MOVEMENT);
			req.setAttribute("movements", MovementService.getAllMovements(conn));
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
				req.setAttribute("movements", MovementService.getAllMovements(conn));
				forward = LIST_MOVEMENT;	        	
	        } else {
	        	if (action.equalsIgnoreCase("insertMovement")) {
					forward = INSERT_MOVEMENT;
					req.setAttribute("products", ProductService.getAllProducts(conn));
					req.setAttribute("types", TypeService.getAllTypes(conn));
				}
	        	else if (action.equalsIgnoreCase("editMovement")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		Movement mov = MovementService.getMovementById(conn, id);
	        		
					req.setAttribute("mov", mov);
	        		forward = EDIT_MOVEMENT;
	        	}  else if (action.equalsIgnoreCase("deleteMovement")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		
	        		forward = LIST_MOVEMENT;
	        		
	        		MovementService.deleteMovementById(conn, id);
	        		req.setAttribute("movements", MovementService.getAllMovements(conn));
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
