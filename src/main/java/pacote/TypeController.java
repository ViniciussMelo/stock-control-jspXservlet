package pacote;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Type;
import service.TypeService;
import util.DBUtil;

@WebServlet(name="TypeController", urlPatterns = {"/TypeController"})
public class TypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String LIST_TYPE = "/WEB-INF/view/type/type.jsp";
	private String INSERT_TYPE = "/WEB-INF/view/type/type_new.jsp";
	private String EDIT_TYPE = "/WEB-INF/view/type/type_edit.jsp";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String action = req.getParameter("action");
        	Connection conn = DBUtil.getConnection();
	        
	        if(action != null) {
	        	if (action.equalsIgnoreCase("saveType")) {
		    		int id = Integer.parseInt(req.getParameter("id"));
	        		String description = req.getParameter("description");
		    		
		    		Type type = new Type(id, description);
		    		
	        		TypeService.updateType(conn, type);
	        	}
	        }
	         else {
		    	String description = req.getParameter("description");
	    		
	    		Type type = new Type(description);
	    		
	    		TypeService.insertType(conn, type);
	         }

	        setTypeActive(req);
			RequestDispatcher view = req.getRequestDispatcher(LIST_TYPE);
			req.setAttribute("types", TypeService.getAllTypes(conn));
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Post type: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String forward = "";
	        String action = req.getParameter("action");
        	Connection conn = DBUtil.getConnection();
        	
        	if(action == null) {
				req.setAttribute("types", TypeService.getAllTypes(conn));      
				forward = LIST_TYPE;	        	  		
        	} else {
	        	if (action.equalsIgnoreCase("insertType")) {
					forward = INSERT_TYPE;
				}
	        	else if (action.equalsIgnoreCase("editType")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		forward = EDIT_TYPE;
	        		
	        		req.setAttribute("type", TypeService.getTypeById(conn, id));
	        	} else if (action.equalsIgnoreCase("deleteType")) {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        		
	        		forward = LIST_TYPE;
	        		TypeService.deleteTypeById(conn, id);
					req.setAttribute("types", TypeService.getAllTypes(conn));
	        	}
        	}
        	
        	setTypeActive(req);
			RequestDispatcher  view = req.getRequestDispatcher(forward);
			view.forward(req, resp);
		} catch (Exception ex) {
			System.out.println("Get type: " + ex.getMessage());			
		}
	}
	
	private void setTypeActive(HttpServletRequest req) {
		req.setAttribute("TYPE", " active");
	}
}
