import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class FantasyGame1 {

	// declare global scanner
	public static Scanner in = new Scanner(System.in);

	// main method
	public static void main(String[] args) {

		// system greeting
		System.out.println("Welcome to the Fantasy Game!");
		System.out.println(); // break
		System.out.println(
				"        1. Add the game players\r\n" + "        2. Players play in turn until only one is left");
		System.out.println("Good Luck!");
		System.out.println(); // break
		System.out.println("First, let's add some players:");

		// create random object for RNG
		Random random = new Random();

		// create an ArrayList to store creatures
		ArrayList<Creature> players = new ArrayList<Creature>();

		// create a variables for characters
		String name = "";
		String species = "";
		int strength;
		int hitPoints;

		// do-while loop for adding players
		do {
			System.out.println("Enter player's name ('quit' when no more players): ");

			// checks if input is not a string value.
			while (in.hasNextInt() || in.hasNextDouble()) {
				System.out.println("Invalid Input. Enter a name.");
				in.next();
			}

			// set value to creature name
			name = in.next();

			// system prompt for player to choose their class
			System.out.println(); // break
			System.out.println(name + ", which species are you?");
			System.out.println("        b/B: Balrog\r\n" + "        c/C: Cyberdemon\r\n" + "        e/E: Elf\r\n"
					+ "        h/H: Human");

			species = in.next().toLowerCase(); // Convert to lowercase for easier comparison
			
			while (!species.equals("b") && !species.equals("c") && !species.equals("e") && !species.equals("h")) {
				System.out.println("Invalid Input. Please try again.");
				species = in.next().toLowerCase();
			}

			//if-else statements for player creature type
			if (species.equals("b")) {
				species = "Balrog";
			} else if (species.equals("c")) {
				species = "Cyberdemon";
			} else if (species.equals("e")) {
				species = "Elf";
			} else {
				species = "Human";
			}
			
		
			strength = random.nextInt(100) + 1; // Random strength between 1 and 100
            hitPoints = random.nextInt(100) + 1; // Random hit points between 1 and 100

            Creature player = new Creature(name, species, strength, hitPoints);
            players.add(player);

		} while (!name.equalsIgnoreCase("quit"));

		
		System.out.println("Current Players:\n");
		System.out.println("NAME | SPECIES | STRENGTH | HIT POINTS\n");
		for (int i = 0; i < players.size(); i++) {
		    Creature player = players.get(i);
		    System.out.println(player.getName() + " | " + player.getSpecies() + " | " + player.getStrength() + " | " + player.getHitPoints());
		}
		
		
	}

}
