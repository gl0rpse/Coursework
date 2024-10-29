// RealBuff class to be used by main.java
public class RealBuff {

	// instance variables
	static final int max_size = 100; // the default maximum size of the buffer
	double[] content; // the content of the buffer
	int current_size; // the number of valid elements

	// constructor
	RealBuff() // initializing an empty buffer of the default maximum size
	{
		this.content = new double[max_size];
		this.current_size = 0;
	}

	// initializing an empty buffer of the maximum size given by n
	RealBuff(final int n) {

		this.content = new double[n];
		this.current_size = 0;
	}

	// initializing a buff which is a copy of buff
	RealBuff(final RealBuff buff) {

		this.content = new double[buff.content.length];
		this.current_size = buff.current_size;

		// for loop to put contents of buff into (deep copy)
		for (int i = 0; i < buff.current_size; i++) {
			this.content[i] = buff.content[i];
		}

	}

	// method to append a value at the end of the buff content
	public boolean appendVal(double value) {

		// state variable
		boolean state = true;

		// if current size is greater than array size, state is false
		if (current_size >= content.length) {
			state = false;
		}

		// place value at end of content array
		content[current_size] = value;

		// increment size to match the appended value
		current_size++;

		// return statement
		return state;
	}

	// method to insert a value at a specified index
	public boolean insertVal(int index, double value) {

		// variable for T/F state of method
		boolean state = true;

		// if index number does not meet size requirements, state is false
		if (index < 0 || index > current_size) {
			state = false;
		}

		// for loop to shift elements to make space for new value
		for (int i = 0; i > index; i--) {
			content[i] = content[i - 1];
		}

		// assign value at specified index
		content[index] = value;

		// increment current size
		current_size++;

		// return statement with state
		return state;
	}

	// method to delete a value at a specific index
	public boolean deleteVal(int index) {

		// value for T/F state
		boolean state = true;

		// if index doesnt meet size requirements, state is false
		if (index < 0 || index >= current_size) {
			state = false;
		}

		// for loop to iterate through and shift elements
		for (int i = index; i < current_size - 1; i++) {
			content[i] = content[i + 1];
		}
		current_size--; // decrement current size of buff to match
		return state; // return statement
	}

	// a method to display contents of buffer
	public void display() {

		// for loop which iterates through each index of buff
		for (int i = 0; i < current_size; i++) {
			System.out.print(content[i] + ", "); // will print each value of buff
		}

		System.out.println(); // extra white space for after print statement
	}

	// a method to do an insertion sort on a buff
	public void insertSort() {

		// for loop starting at second value of buff
		for (int i = 1; i < current_size; i++) {
			double key = content[i];

			int x = i - 1;

			// nested while loop
			while (x >= 0 && content[x] > key) {
				content[x + 1] = content[x];

				x--; // decrement x

			}

			content[x + 1] = key;
		}
	}

	// private method for partition to be used by quicksort algo
	private int partition(double[] arr, int x, int y) {
		double pivot = arr[y]; // pivot = arr[high]
		int i = x - 1; // i = low - 1
		for (int j = x; j < y; j++) { // increment j correctly
			if (arr[j] <= pivot) { // if arr[j] <= arr[high]
				i++;
				double temp = arr[i]; // temp for swapping arr[i] and arr[j]
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[y]
		double temp = arr[i + 1];
		arr[i + 1] = arr[y];
		arr[y] = temp;
		return i + 1;
	}

	// public method for quicksort
	public void quickSort() {
		quickSort(content, 0, current_size - 1);
	}

	// private method for quicksort
	private void quickSort(double[] arr, int x, int y) {
		if (x < y) { // base case
			int Q = partition(arr, x, y);
			quickSort(arr, x, Q - 1);
			quickSort(arr, Q + 1, y);
		}
	}

	// merge method for mergesort()
	private void merge(double[] arr, int x, int m, int y) {
		// meclare left and right arrays
		int n1 = m - x + 1; // size of left array
		int n2 = y - m; // size of right array

		double[] left = new double[n1];
		double[] right = new double[n2];

		// copy data to left and right arrays
		for (int i = 0; i < n1; i++) {
			left[i] = arr[x + i]; // fill left array
		}
		for (int i = 0; i < n2; i++) {
			right[i] = arr[m + 1 + i]; // fill right array
		}

		// merge arrays
		int i = 0, j = 0, k = x; // k starts at x

		while (i < n1 && j < n2) {
			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		// copy remaining elements of left array if any
		while (i < n1) {
			arr[k++] = left[i++];
		}

		// copy remaining elements of right array if any
		while (j < n2) {
			arr[k++] = right[j++];
		}
	}

	// private method for merge sort
	private void mergeSort(double[] arr, int x, int y) {
		if (x < y) {
			int Q = (x + y) / 2;
			mergeSort(arr, x, Q);
			mergeSort(arr, Q + 1, y);
			merge(arr, x, Q, y);
		}
	}

	// public method for merge sort
	public void mergeSort() {
		if (current_size > 0) { // check if buffer isn't empty
			mergeSort(content, 0, current_size - 1); // call to private mergeSort()
		}
	}

}// end RealBuff
