package model;

public class Type {
	private int Id;
	private String Description;
	
	public Type(String description) {
		this.Description = description;
	}
	
	public Type(int id, String description) {
		this.Id = id;
		this.Description = description;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
