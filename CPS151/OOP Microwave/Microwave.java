public class Microwave {

	private int time; // in seconds
	private int powerLevel;

	public Microwave() {

		time = 0;
		powerLevel = 1;

	}

	public void reset() {

		time = 0;
		powerLevel = 1;

	}

	public void timeIncrease(int minutes, int seconds) {

		int minutesToSeconds = minutes * 60;
		time = minutesToSeconds + seconds;

	}

	public void powerLevel(int answer) {

		if (answer == 1) {
			powerLevel = 1;
		} else if (answer == 2) {
			powerLevel = 2;
		} else {
			System.out.println("Invalid.");
		}

	}

	public void startButton() {

		System.out.println("Cooking for " + time + " seconds at level " + powerLevel + ".");

	}
}
