package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Movement;

public class MovementService {
	private final static String TABLE_NAME = "movements";
	private final static String PRODUCT_TABLE_NAME = "products";
	private final static String TYPE_TABLE_NAME = "types";
	
	public final static String MOVEMENT_ID_COLUMN = "id";
	public final static String MOVEMENT_QUANTITY_COLUMN = "quantity";
	public final static String MOVEMENT_PRODUCT_ID_COLUMN = "product_id";
	public final static String MOVEMENT_TYPE_ID_COLUMN = "type_id";
	
	public final static String PRODUCT_BARCODE_COLUMN = "barcode";
	public final static String PRODUCT_NAME_COLUMN = "name";
	
	public final static String TYPE_ID_COLUMN = "id";
	public final static String TYPE_DESCRIPTION_COLUMN = "description";
	
	public static Movement getMovementById(Connection conn, int id) {
		try {
			String sql = "select "+ TABLE_NAME+ "." + MOVEMENT_ID_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_TYPE_ID_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_QUANTITY_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_PRODUCT_ID_COLUMN + ","
					+ "			 "+ PRODUCT_TABLE_NAME+ "." + PRODUCT_NAME_COLUMN + " as prodName,"
					+ "			 "+ TYPE_TABLE_NAME+ "." + TYPE_DESCRIPTION_COLUMN + " as typeDesc"
					+ "	    from " + TABLE_NAME
					+ "	  	join " + PRODUCT_TABLE_NAME +" on "
					+ "     " + PRODUCT_TABLE_NAME + "." + PRODUCT_BARCODE_COLUMN + " = " + TABLE_NAME + "." + MOVEMENT_PRODUCT_ID_COLUMN
					+ "	  	join " + TYPE_TABLE_NAME +" on "
					+ "     " + TYPE_TABLE_NAME + "." + TYPE_ID_COLUMN + " = " + TABLE_NAME + "." + MOVEMENT_TYPE_ID_COLUMN
					+ "    where " + TABLE_NAME + "." + MOVEMENT_ID_COLUMN + " = ?"
					+ "   order by " + TABLE_NAME + "." + MOVEMENT_ID_COLUMN;
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				int movId = rs.getInt(MOVEMENT_ID_COLUMN);
				int movQuantity = rs.getInt(MOVEMENT_QUANTITY_COLUMN);
				
				String prodName = rs.getString("prodName");
				int productId = rs.getInt(MOVEMENT_PRODUCT_ID_COLUMN);
				
				int typeId = rs.getInt(MOVEMENT_TYPE_ID_COLUMN);
				String typeDesc = rs.getString("typeDesc");
				
				Movement mov = new Movement(movId, productId, typeId, movQuantity, prodName, typeDesc);
				
				return mov;
			}
		} catch (Exception ex) {
			System.out.println("Error when getMovementById: " + ex.getMessage());
		}
		
		return null;
	}
	
	public static ArrayList<Movement> getAllMovements(Connection conn) {
		ArrayList<Movement> movements = new ArrayList<Movement>();
		try {			
			String sql = "select "+ TABLE_NAME+ "." + MOVEMENT_ID_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_TYPE_ID_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_QUANTITY_COLUMN + ","
					+ "			 "+ TABLE_NAME+ "." + MOVEMENT_PRODUCT_ID_COLUMN + ","
					+ "			 "+ PRODUCT_TABLE_NAME+ "." + PRODUCT_NAME_COLUMN + " as prodName,"
					+ "			 "+ TYPE_TABLE_NAME+ "." + TYPE_DESCRIPTION_COLUMN + " as typeDesc"
					+ "	    from " + TABLE_NAME
					+ "	  	join " + PRODUCT_TABLE_NAME +" on "
					+ "     " + PRODUCT_TABLE_NAME + "." + PRODUCT_BARCODE_COLUMN + " = " + TABLE_NAME + "." + MOVEMENT_PRODUCT_ID_COLUMN
					+ "	  	join " + TYPE_TABLE_NAME +" on "
					+ "     " + TYPE_TABLE_NAME + "." + TYPE_ID_COLUMN + " = " + TABLE_NAME + "." + MOVEMENT_TYPE_ID_COLUMN
					+ "   order by " + TABLE_NAME + "." + MOVEMENT_ID_COLUMN;
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				int movId = rs.getInt(MOVEMENT_ID_COLUMN);
				int movQuantity = rs.getInt(MOVEMENT_QUANTITY_COLUMN);
				
				String prodName = rs.getString("prodName");
				int productId = rs.getInt(MOVEMENT_PRODUCT_ID_COLUMN);
				
				int typeId = rs.getInt(MOVEMENT_TYPE_ID_COLUMN);
				String typeDesc = rs.getString("typeDesc");
				
				Movement mov = new Movement(movId, productId, typeId, movQuantity, prodName, typeDesc);
				
				movements.add(mov);
			}
		} catch (Exception ex) {
			System.out.println("Error when getAllMovements: " + ex.getMessage());
		}
		
		return movements;
	}
	

	public static void deleteMovementById(Connection conn, int id) {
		try {
			String sql = "delete from " + TABLE_NAME + " where " + MOVEMENT_ID_COLUMN +" = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when delete movement: " + ex.getMessage());
		}
	}
		
	public static void insertMovement(Connection conn, Movement mov) {
		try {
			String sql = "insert into " + TABLE_NAME + " ("+ MOVEMENT_TYPE_ID_COLUMN +","+ MOVEMENT_QUANTITY_COLUMN + "," + MOVEMENT_PRODUCT_ID_COLUMN + ")"
					+ "        values (?, ?, ?)";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, mov.getTypeId());
			stm.setInt(2, mov.getQuantity());
			stm.setInt(3, mov.getProductId());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when insertMovement: " + ex.getMessage());
		}
	}
	
	public static void updateMovement(Connection conn, Movement mov) throws SQLException {
		try {
			String sql = "update " + TABLE_NAME +
					 "		 set " + MOVEMENT_TYPE_ID_COLUMN + " = ?, "
					 + "         " + MOVEMENT_QUANTITY_COLUMN + " = ? "
					 + "   where " + MOVEMENT_ID_COLUMN + " = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, mov.getTypeId());
			stm.setInt(2, mov.getQuantity());
			stm.setInt(3, mov.getId());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when updateMovement: " + ex.getMessage());
		}
	}
}
