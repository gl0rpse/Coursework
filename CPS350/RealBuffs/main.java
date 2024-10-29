
//import random class for rng doubles
import java.util.Random;

public class main {

	// main method
	public static void main(String[] args) {

		// declare random class for rng
		Random rand = new Random();

		// create a new buffs
		RealBuff buff1 = new RealBuff();
		RealBuff buff2 = new RealBuff();

		// declare an array of doubles
		double[] doubles = new double[10];

		// for loop to fill array with doubles
		// append buffs with said doubles afterwards
		for (int i = 0; i < doubles.length; i++) {
			doubles[i] = rand.nextDouble();
			buff1.appendVal(doubles[i] * 50); // scale doubles with 50
			buff2.appendVal(doubles[i] * 50);
		}

		// display buff1
		System.out.println("Original:");
		buff1.display();
		System.out.println();

		// sort buff1 using quickSort()
		System.out.println("Quicksort:");
		buff1.quickSort();
		buff1.display(); // display buff post-quickSort()
		System.out.println();

		// display buff1
		System.out.println("Original:");
		buff2.display();
		System.out.println();

		// sort buff1 using quickSort()
		System.out.println("Mergesort:");
		buff2.mergeSort();
		buff2.display(); // display buff post-quickSort()
		System.out.println();

//**********************************************************************\\

		// declare variable n for 100000
		int n = 10000;

		// declare array of RealBuffs
		RealBuff[] buffs1 = new RealBuff[10];
		RealBuff[] buffs2 = new RealBuff[10];

		// fill buffs with RealBuffs, and RealBuffs with rng doubles
		// buffs1 iteration
		for (int i = 0; i < buffs1.length; i++) {
			buffs1[i] = new RealBuff(n); // RealBuff at index i of size n

			// nested for loop to fill RealBuffs with rng doubles
			for (int j = 0; j < n; j++) {
				buffs1[i].appendVal((200 * rand.nextDouble()) - 100); // random numbers from -100 to 100
			}
		}

		// fill buffs with RealBuffs, and RealBuffs with rng doubles
		// buffs2 iteration
		for (int i = 0; i < buffs2.length; i++) {
			buffs2[i] = new RealBuff(n); // RealBuff at index i of size n

			// nested for loop to fill RealBuffs with rng doubles
			for (int j = 0; j < n; j++) {
				buffs2[i].appendVal((200 * rand.nextDouble()) - 100); // random numbers from -100 to 100
			}
		}

		// declare variables for sorting algo times
		long mergeTime = 0;
		long quickTime = 0;

		// for loop to get mergeSort time
		for (RealBuff buff : buffs1) {
			long start1 = System.currentTimeMillis();
			buff.mergeSort();
			long end1 = System.currentTimeMillis();
			mergeTime += (end1 - start1);
		}

		// for loop to get quickSort time
		for (RealBuff buff : buffs2) {
			long start2 = System.currentTimeMillis();
			buff.quickSort();
			long end2 = System.currentTimeMillis();
			quickTime += (end2 - start2);
		}

		// output statement
		System.out.println(
				"Average Mergesort Time is " + mergeTime + "." + "\nAverage Quicksort Time is " + quickTime + ".");

	}

}
