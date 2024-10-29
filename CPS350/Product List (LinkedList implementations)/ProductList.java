import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// ProductList class for main.java
public class ProductList {

	// declare instance variable(s)
	private Product head;

	// constructor method
	// has a string parameter for the file path
	public ProductList(String inputFile) throws FileNotFoundException {

		head = null;

		// try catch statement for exception
		try {
			// create a new file variable using file path
			File file = new File(inputFile);

			// declare scanner for file reading
			Scanner scan = new Scanner(file);

			// while loop to parse product.txt
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim();

				// check for empty line for ID
				if (!line.isEmpty()) {
					long ID = Long.parseLong(line);

					// check for empty line for name
					String name = scan.nextLine().trim();
					if (!name.isEmpty()) {
						// check for empty line for og price
						line = scan.nextLine().trim();
						if (!line.isEmpty()) {
							double originalPrice = Double.parseDouble(line);

							// check for empty line for current price
							line = scan.nextLine().trim();
							if (!line.isEmpty()) {
								double currentPrice = Double.parseDouble(line);

								// create a product using info that was parsed
								Product product = new Product(ID, name, originalPrice, currentPrice);

								// add product to the ProductList
								if (head == null) {
									head = product;
								} else {
									Product current = head;
									while (current.next != null) {
										current = current.next;
									}
									// append product
									current.next = product; 
								}
							}
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			// print exception message
			System.out.println("ERROR: File Not Found or File DNE.");

			// force exit program
			System.exit(0);
		}

	}

	// method to print out content of a ProductList
	public void displayProductList() {
		Product node = head;
		while (node != null) {
			System.out.println(node.toString());
			node = node.next;
		}
	}

	// deep copy constructor ProductList of other ProductList
	public ProductList(ProductList original) {
		if (original.head == null) {
			this.head = null;
		} else {
			this.head = new Product(original.head);
			Product prod1 = this.head;
			Product prod2 = original.head.next;
			while (prod2 != null) {
				prod1.next = new Product(prod2);
				prod1 = prod1.next;
				prod2 = prod2.next;
			}
		}
	}

	// insertion sort method
	// will sort products in a ProductList based on discounts
	// discount = (OGPrice - Price) / OGPrice
	public void insertionSort() {

		// base case for empty list
		if (head == null)
			return;

		// empty temp list
		Product temp = null;
		Product original = head;

		// insert each product into the sorted list
		while (original != null) {
			
			// save next node
			Product next = original.next;

			// declare discount variable
			double discount = (original.originalPrice - original.currentPrice) / original.originalPrice;

			// insert product into the new list based on discount
			if (temp == null || discount < ((temp.originalPrice - temp.currentPrice) / temp.originalPrice)) {
				original.next = temp;
				temp = original;
				
			} else {
				
				// declare sortedList variable = to temp
				Product sortedList = temp;
				
				// while next node isnt null
				while (sortedList.next != null) {
					
					// declare sorted discount variable
					double sortDiscount = (sortedList.next.originalPrice - sortedList.next.currentPrice) / sortedList.next.originalPrice;
					if (discount < sortDiscount) {
						break;
					}
					
					// move over a position
					sortedList = sortedList.next;
				}
				
				// insert product
				original.next = sortedList.next;
				sortedList.next = original;
			}
			original = next; 
		}

		// update head
		head = temp;
	}
}
