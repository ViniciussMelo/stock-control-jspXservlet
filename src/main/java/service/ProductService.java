package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;

public class ProductService {
	private final static String TABLE_NAME = "products";
	
	public static Product getProductByBarcode(Connection conn, int barcode) {
		try {			
			String sql = "select * from " + TABLE_NAME + " where barcode = ? order by barcode";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, barcode);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				int prodId = rs.getInt("barcode");
				String prodName = rs.getString("name");
				Double prodPrice = rs.getDouble("price");
				Product prod = new Product(prodId, prodName, prodPrice);
				
				return prod;
			}
		} catch (Exception ex) {
			System.out.println("Error when getProductByBarcode: " + ex.getMessage());
		}
		
		return null;
	}
	
	public static ArrayList<Product> getAllProducts(Connection conn) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {			
			String sql = "select * from " + TABLE_NAME + " order by barcode";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				int prodId = rs.getInt("barcode");
				String prodName = rs.getString("name");
				Double prodPrice = rs.getDouble("price");
				Product prod = new Product(prodId, prodName, prodPrice);
				
				products.add(prod);
			}			
		} catch (Exception ex) {
			System.out.println("Error when getAllProducts: " + ex.getMessage());
		}
		
		return products;
	}
	
	public static void deleteProductByBarcode(Connection conn, int barcode) {
		try {
			String sql = "delete from " + TABLE_NAME + " where barcode = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, barcode);
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when delete product: " + ex.getMessage());
		}
	}
	
	public static void insertProduct(Connection conn, Product prod) throws SQLException {
		try {
			String sql = "insert into " + TABLE_NAME + " (barcode, name, price)"
					+ "        values (?, ?, ?)";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, prod.getBarcode());
			stm.setString(2, prod.getName());
			stm.setDouble(3, prod.getPrice());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when insertProduct: " + ex.getMessage());
		}
	}
	
	public static void updateProduct(Connection conn, Product prod) throws SQLException {
		try {
			String sql = "update " + TABLE_NAME +
					 "		 set name = ?, "
					 + "		 price = ? "
					 + "   where barcode = ?";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, prod.getName());
			stm.setDouble(2, prod.getPrice());
			stm.setInt(3, prod.getBarcode());
			
			stm.execute();
		} catch (Exception ex) {
			System.out.println("Error when insertProduct: " + ex.getMessage());
		}
	}
}
