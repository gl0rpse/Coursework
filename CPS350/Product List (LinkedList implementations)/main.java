import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {

		// declare a product list
		ProductList list = new ProductList("products.txt");

		// display productlist
		System.out.println("First list: \n");
		list.displayProductList();

		// extra space
		System.out.println();

		// create a deepcopy of original list
		ProductList copyList = new ProductList(list);

		// display the deepcopy
		System.out.println("Copy list: \n");
		copyList.displayProductList();

		// extra space
		System.out.println();

		// sorted list with discount
		list.insertionSort();
		System.out.println("Sorted list: \n");
		list.displayProductList();
	}

}
