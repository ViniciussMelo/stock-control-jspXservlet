package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Type;

public class TypeService {
	private final static String TABLE_NAME = "types";
	
	public final static String TYPE_ID_COLUMN = "id";
	public final static String TYPE_DESCRIPTION_COLUMN = "description";
	
	public static Type getTypeById(Connection conn, int id) {
		try {
			String sql = "select * from " + TABLE_NAME + " where " + TYPE_ID_COLUMN + " = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, id);			
		
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				int typeId = rs.getInt(TYPE_ID_COLUMN);
				String typeDescription = rs.getString(TYPE_DESCRIPTION_COLUMN);
				
				Type type = new Type(typeId, typeDescription);
				
				return type;
			}
		} catch (Exception ex) {

			System.out.println("Error when getTypeById: " + ex.getMessage());
		}
		
		return null;
	}
	
	public static ArrayList<Type> getAllTypes(Connection conn) {
		ArrayList<Type> types = new ArrayList<Type>();
		
		try {
			String sql = "select * from " + TABLE_NAME;
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
		
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				int typeId = rs.getInt(TYPE_ID_COLUMN);
				String typeDescription = rs.getString(TYPE_DESCRIPTION_COLUMN);
				
				Type type = new Type(typeId, typeDescription);
				
				types.add(type);
			}
		} catch (Exception ex) {
			System.out.println("Error when getAllTypes: " + ex.getMessage());
		}
		
		return types;
	}
	

	public static void deleteTypeById(Connection conn, int id) {
		try {
			String sql = "delete from " + TABLE_NAME + " where " + TYPE_ID_COLUMN +" = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when delete movement: " + ex.getMessage());
		}
	}
	
	public static void insertType(Connection conn, Type type) {
		try {
			String sql = "insert into " + TABLE_NAME + " ("+ TYPE_DESCRIPTION_COLUMN + ")"
					+ "        values (?)";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, type.getDescription());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when insertType: " + ex.getMessage());
		}
	}
	
	public static void updateType(Connection conn, Type type) throws SQLException {
		try {
			String sql = "update " + TABLE_NAME +
					 "		 set " + TYPE_DESCRIPTION_COLUMN + " = ? "
					 + "   where " + TYPE_ID_COLUMN + " = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, type.getDescription());
			stm.setInt(2, type.getId());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when updateType: " + ex.getMessage());
		}
	}
}
