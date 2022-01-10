package Characters;

public class Elf extends Character{
	public Elf(String name) {
		super(130, 15, 100, 3, 30, name);
	}
	public void special(Character c) {
		spendEnergy();
		System.out.print(getName() + " fires a crippling shot at " + c.getName() + "!");
		c.setAccuracy(c.getAccuracy() - 10);
		c.setHealth(c.getHealth() - 10);
		setHealth(getHealth() + 10);
	}
	public static void info() {
		System.out.println("Elf");
		System.out.println("HP: 130 Damage: 15 Accuracy: 100 Energy: 3 Healing: 30");
		System.out.println("A character of type Elf has superb marksmanship and healing.");
		System.out.println("His/her special is guaranteed to fire a crippling shot at the target.");
		System.out.println("This regains 10 health, and decreases the opponent's accuracy and health by ten.");
	}
}
