package model;

public class Movement {
	private int Id;
	private int Quantity;

	private int ProductId;
	private String ProductName;

	private int TypeId;
	private String TypeName;
	
	public Movement(int productId, int typeId, int quantity) {
		this.TypeId = typeId;
		this.Quantity = quantity;
		this.ProductId = productId;
	}
	
	public Movement(int id, int productId, int typeId, int quantity) {
		this.Id = id;
		this.TypeId = typeId;
		this.Quantity = quantity;
		this.ProductId = productId;
	}
	
	public Movement(int id, int productId, int typeId, int quantity, String productName, String typeName) {
		this.Id = id;
		this.TypeId = typeId;
		this.Quantity = quantity;
		this.ProductId = productId;
		this.ProductName = productName;
		this.TypeName = typeName;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getTypeId() {
		return TypeId;
	}

	public void setTypeId(int typeId) {
		TypeId = typeId;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
}
