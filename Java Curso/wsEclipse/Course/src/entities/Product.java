package entities;

public class Product {
	
	public Product() {
	}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	private String name;
	private double price;
	private int quantity;
	
	//////// CONSTRUTORES
	/*
	 * public Product () { // Construtor padrão
	 * 
	 * 
	 * } public Product (String name, double price, int quantity) { // Construtor
	 * this.name = name; this.price = price; this.quantity = quantity; }
	 * 
	 * public Product (String name, double price) { // Segundo Construtor onde a
	 * quantidade não é obrigatória this.name = name; this.price = price; }
	 * 
	 * 
	 * ///// GETs e SETs
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name ) { this.name = name; }
	 * 
	 * public Double getPrice() { return price; }
	 * 
	 * public void setPrice(Double price ) { this.price = price; }
	 * 
	 * public int getQuantity() { return quantity; }
	 * 
	 * 
	 * 
	 */
	
	 * ///// GETs e SETs

	
	
	///// Outros métodos
	
	public double totalValueInStock() {
		return price * quantity;
	}
	
	public void addProducts(int quantity) {
		this.quantity += quantity;
	}
	
	public void removeProducts(int quantity) {
		this.quantity -= quantity;		
	}
	
	public String toString() { // criando a minha versão de toString
		return name
				+ ", $ "
				+ String.format ("%.2f", price)
				+ ", "
				+ quantity
				+ " units, Total: $ "
				+ String.format ("%.2f", totalValueInStock());		
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

	public int getQuantity() {
		return quantity;
	}

	
}
