package Characters;

public class Assassin extends Character{
	public Assassin(String name) {
		super(120, 55, 55, 2, 10, name);
	}
	public void special(Character c) {
		spendEnergy();
		System.out.println(getName() + " backstabs " + c.getName() + "!");
		if(c.getHealth() <= 75) {
			c.setHealth(0);
			c.setAccuracy(getAccuracy() + 35);
		}	
		else {
			c.setHealth(c.getHealth() - 40);
		}
	}
	public static void info() {
		System.out.println("Assassin");
		System.out.println("HP: 120 Damage: 55 Accuracy: 55 Energy: 2 Healing: 10");
		System.out.println("A character of type Assassin relies on landing hard blows on his/her enemy.");
		System.out.println("His special has a 100% of hitting, and does 40 damage.");
		System.out.println("However, the move executes the opponent if the opponent less than or equal to 75 health.");
	}
}
