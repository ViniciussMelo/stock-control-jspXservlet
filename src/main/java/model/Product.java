package model;

public class Product {
	private String barcode;
	private String name;
	private double price;
	private boolean active;
	
	public Product(String barcode, String name, double price) {
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.active = true;
	}
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
