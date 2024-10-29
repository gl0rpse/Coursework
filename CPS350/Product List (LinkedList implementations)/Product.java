// product class for ProductList.java and main.java
public class Product {
	
	// declare instance variables
	long ID;
	String name;
	double originalPrice;
	double currentPrice;
	Product next;

	// constructor
	public Product(long ID, String name, double originalPrice, double currentPrice) {
		this.ID = ID; // Corrected: assign parameter to instance variable
		this.name = name; // Corrected
		this.originalPrice = originalPrice; // Corrected
		this.currentPrice = currentPrice; // Corrected
		this.next = null; // Initializes next as null
	}

	// deep copy constructor
	public Product(Product product) {
		this.ID = product.ID;
		this.name = product.name;
		this.originalPrice = product.originalPrice;
		this.currentPrice = product.currentPrice;
		this.next = null; // Initialize next as null
	}

	// toString method to print product variables
	public String toString() {
		return "ID: " + ID + ", Name: " + name + ", Original Price: $" + String.format("%.2f", originalPrice)
				+ ", Current Price: $" + String.format("%.2f", currentPrice);
	}
}
