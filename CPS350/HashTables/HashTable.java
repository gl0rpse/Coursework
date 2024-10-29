public class HashTable {

	// instance variables
	private int maxSize;
	private Integer[] content;
	private boolean[] occupied;
	private boolean[] available;

	// constructor for simple hashtable
	public HashTable(final int size) {
		this.maxSize = size;
		this.content = new Integer[maxSize];
		this.occupied = new boolean[maxSize];
		this.available = new boolean[maxSize];
	}

	/*---------------------------------------------------------------------------
	 * Simple hashing without dealing with collision:
	 * 
	 *---------------------------------------------------------------------------*/

	// method for computing the hash index
	private int hash(int key) {
		int result = key % maxSize; // formula
		return result; // returns hash index
	}

	// simple search method
	// -will return integer on hash key based on index value of occupied[]
	public int simpleSearch(final int key) {
		int index = hash(key); // index is hashing value of key

		if (occupied[index] && content[index] != null && content[index] == key) { // if table is occupied at that index, return the index
			return index;
		} else {
			return -1; // if not, return -1
		}
	}

	// simple insertion method
	// -will insert key at hash index if unoccupied and return true
	// otherwise, returns false
	public boolean simpleInsertion(final int key) {
		int index = hash(key); // index is hashing value of key

		if (!occupied[index]) { // if table is NOT occupied at hash index...
			content[index] = key; // place key at hash index
			occupied[index] = true; // signify that the spot at hash index is now occupied
			return true; // successful insertion
		} else {
			return false; // returns false based on conflicts (hash index is occupied already)
		}
	}

	// simple deletion method
	// -will "delete" a value from the table by changing its status
	public boolean simpleDeletion(final int key) {
		int index = hash(key); // compute hash index

		if (!occupied[index]) { // if not occupied, return false
			return false;
		} else {
			content[index] = null; // remove content from table
			occupied[index] = false; // make unoccupied
			return true; // successful deletion
		}
	}

	/*---------------------------------------------------------------------------
	 * Linear Probing hashing:
	 * 
	 *---------------------------------------------------------------------------*/

	// linear probe search method
	// -will find linear probe index and compare the input key with the key at that
	// index
	// -to return its index value.
	public int search(final int key) {
		int index = hash(key); // compute hash index

		// for loop to iterate through hash table
		for (int i = 0; i < maxSize; i++) {
			int lpIndex = (index + i) % maxSize; // compute linear probing index

			// if table is occupied or not available at linear probing index
			if (!occupied[lpIndex] && !available[lpIndex]) {
				return -1;
			}

			// if index is occupied and has content
			if (occupied[lpIndex] && content[lpIndex] != null && content[lpIndex] == key) {
				return lpIndex; // return index where value was found
			}
		}
		return -1; // fail
	}

	// linear probe insertion method
	// -will find linear probe index and insert the key at that index
	// -while changing the status conditions as well
	public boolean insertion(final int key) {
		int index = hash(key); // compute hash index

		// for loop to iterate through hash table
		for (int i = 0; i < maxSize; i++) {
			int lpIndex = (index + i) % maxSize; // compute linear probe index

			// if not occupied or available at linear probe index, insert key
			if (!occupied[lpIndex] || available[lpIndex]) {
				content[lpIndex] = key;
				occupied[lpIndex] = true;
				available[lpIndex] = false;
				return true;
			}
		}
		return false; // insertion unsuccessful
	}

	// linear probe deletion method
	// -will find linear probe index and delete the key at that index
	// -while changing the status conditions as well
	public boolean deletion(final int key) {
		int index = hash(key); // compute hash index

		for (int i = 0; i < maxSize; i++) {
			int lpIndex = (index + i) % maxSize;
			if (!occupied[lpIndex] && !available[lpIndex]) {
				return false;
			}
			if (occupied[lpIndex] && content[lpIndex] != null && content[lpIndex] == key) {
				occupied[lpIndex] = false; // make not occupied
				available[lpIndex] = true; // make available
				return true; // successful deletion
			}
		}
		return false;
	}

	// method to display contents of a hashtable along with its status
	public void display() {
		System.out.println("Table: ");
		for (int i = 0; i < maxSize; i++) { // for loop to iterate through table
			String status; // status variable
			if (occupied[i]) {
				status = "Occupied";
			} else if (available[i]) {
				status = "Available";
			} else {
				status = "Empty";
			}
			System.out.println("Key: " + content[i] + " Status: " + status);
		}
		System.out.println(); // extra space
	}

}
