
//this is a lot to read...

public class main {

	// main method
	public static void main(String[] args) {

		// create hashtables for testing
		HashTable table1 = new HashTable(11);
		HashTable table2 = new HashTable(11);

		// insert values into hashtable and display
		System.out.println("Insertion test and tables:");
		System.out.println();
		table1.display();
		table1.simpleInsertion(34);
		table1.display();
		table1.simpleInsertion(29);
		table1.display();
		table1.simpleInsertion(53);
		table1.display();
		table1.simpleInsertion(44);
		table1.display();
		table1.simpleInsertion(120);
		table1.display();
		table1.simpleInsertion(39);
		table1.display();
		table1.simpleInsertion(45);
		table1.display();
		table1.simpleInsertion(40);
		table1.display();
		System.out.println("-------------------------------------------------");

		// simple deletion test
		System.out.println("Simple Deletion Test: ");
		table1.deletion(120);
		table1.display();
		System.out.println("-------------------------------------------------");

		// simple search test
		System.out.println("Simple Search Test: ");
		System.out.println("Index of the key: " + table1.simpleSearch(40));
		table1.display();
		System.out.println("-------------------------------------------------");

		// simple insertion test (again)
		System.out.println("Simple Insertion test:");
		table1.simpleInsertion(46);
		table1.display();
		System.out.println("-------------------------------------------------");

		// linear probing methods of insertion
		System.out.println("Insertion test and tables (using linear probing):");
		table2.insertion(34);
		table2.display();
		table2.insertion(29);
		table2.display();
		table2.insertion(53);
		table2.display();
		table2.insertion(44);
		table2.display();
		table2.insertion(120);
		table2.display();
		table2.insertion(39);
		table2.display();
		table2.insertion(45);
		table2.display();
		table2.insertion(40); // Now the insertion should work using linear probing
		table2.display();
		System.out.println("-------------------------------------------------");

		// linear probe delete test
		System.out.println("Linear Probing Deletion: ");
		table2.deletion(120);
		table2.display();
		System.out.println("-------------------------------------------------");

		// linear probe search test
		System.out.println("Linear Probe Search Test: ");
		System.out.println("The index of the key is: " + table2.search(40));
		table2.display();
		System.out.println("-------------------------------------------------");

		// linear probe insertion test
		System.out.println("Linear Probe Insertion test:");
		table1.insertion(46);
		table1.display();
		System.out.println("-------------------------------------------------");
	}

}
