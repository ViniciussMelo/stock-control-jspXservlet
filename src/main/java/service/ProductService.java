package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

public class ProductService {
	
	private final String PRODUCT_COUNT = "PRODUCT_COUNT";
	private final String PRODUCT = "PRODUCT";
	
	public void insertProduct(HttpServletRequest req, HttpServletResponse resp) {
		String barcode = req.getParameter("barcode");
		String name = req.getParameter("name");
		Double price = Double.parseDouble(req.getParameter("price"));
		
		Product product = new Product(barcode, name, price);
		setProductInSession(req, product);
	}
	
	private void setProductInSession(HttpServletRequest req, Product product) {
		int count = getCountProduct(req);
		req.getSession().setAttribute(PRODUCT + count, product);
		setCountProduct(req);
	}
	
	private int getCountProduct(HttpServletRequest req) {
		int count = 0;
		try {
		count = (int)req.getSession().getAttribute(PRODUCT_COUNT);
		} catch (Exception ex) {
			System.out.println("Session for " + PRODUCT_COUNT + " is null");
		}
		return count;
	}
	
	private void setCountProduct(HttpServletRequest req) {
		int count = getCountProduct(req);
		req.getSession().setAttribute(PRODUCT_COUNT, count + 1);
	}
	
	public List<Product> getAllProducts(HttpServletRequest req) {
		List<Product> listProducts = new ArrayList<Product>();
		
		int countProduct = getCountProduct(req);
		
		for (int i = 0; i < countProduct; i++) {
			Product prod = getProductByPosition(req, i);
			listProducts.add(prod);
		}
		
		return listProducts;
	}
	
	private Product getProductByPosition(HttpServletRequest req, int position) {
		Product prod = (Product)req.getSession().getAttribute(PRODUCT + position);
		
		return prod;
	}
}
