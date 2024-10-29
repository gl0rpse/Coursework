import java.util.Random;

public class Creature {
//instance variables	
	private int strength;
	private int hitPoints;
	private String name;
	private String species;

	public Creature(String newName, String newSpecies, int newStrength, int newHit) throws IllegalArgumentException {
		name = newName;
		species = newSpecies;
		if (newStrength < 0) {
			strength = 0;
		} else {
			strength = newStrength;
		}
		hitPoints = newHit;
	}

	public String getSpecies() {
		return species;
	}

	public void setStrength(int newStrength) throws IllegalArgumentException {
		if (newStrength < 0) {
			throw new IllegalArgumentException("Strength cannot be negative.");
		}
		strength = newStrength;
	}

	public int getStrength() {
		return strength;
	}

	public void setHitPoints(int newHit) throws IllegalArgumentException {
		if (newHit < 0) {
			throw new IllegalArgumentException("Hit points cannot be negative.");
		}
		hitPoints = newHit;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getDamage() {
		// create random object for RNG
		Random rand = new Random();
		int damage = rand.nextInt(hitPoints) + 1; // Random number between 1 and hitPoints (inclusive)
		// Check for special attacks
		if (species.equals("Balrog") || species.equals("Cyberdemon")) {
			// Demons have a 5% chance of inflicting a demonic attack
			if (rand.nextDouble() <= 0.05) {
				damage += 50;
				System.out.println(species + " inflicted a demonic attack!");
			}
		}
		if (species.equals("Elf")) {
			// Elves have a 10% chance to inflict a magical attack that doubles the normal
			// damage
			if (rand.nextDouble() <= 0.1) {
				damage *= 2;
				System.out.println(species + " inflicted a magical attack!");
			}
		}
		// Balrogs attack twice
		if (species.equals("Balrog")) {
			damage *= 2;
			System.out.println(species + " attacked twice!");
		}
		System.out.println(species + " inflicted " + damage + " damage.");
		return damage;
	}

	public void Damage(int damage) throws IllegalArgumentException {
		strength = strength - damage;
	}

	public boolean isDead() {
		if (strength <= 0) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public boolean isNamed(String aName) {
		return name.equals(aName);
	}
}
