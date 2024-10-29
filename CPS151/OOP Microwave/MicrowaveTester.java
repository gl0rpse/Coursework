import java.util.Scanner;

public class MicrowaveTester {
	public static void main(String[] args) {

		// create a microwave
		Microwave microwave = new Microwave();

		// reset it
		microwave.reset();

		// create a scanner for user input
		Scanner in = new Scanner(System.in);

		// set timer for first microwave run
		int minutes = 2;
		int seconds = 30;

		// throw times into timeIncrease method
		microwave.timeIncrease(minutes, seconds);

		// start microwave (print values)
		microwave.startButton();

		// change power level of microwave to 2
		int powerLevel = 2;
		microwave.powerLevel(powerLevel);

		// start microwave again
		microwave.startButton();

		// reset microwave again
		microwave.reset();

		// set new microwave times
		minutes = 5;
		seconds = 0;
		
		//throw new times into timeIncrease method
		microwave.timeIncrease(minutes, seconds);

		// start microwave again
		microwave.startButton();

		// close scanner
		in.close();

	}
}
