package model;

public class Category {
	
	private String brand;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String brand) {
		super();
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Category [brand=" + brand + "]";
	}
	
	
	
}
