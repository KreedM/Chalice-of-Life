package Characters;

public class Giant extends Character {
	//Has Immunity against any effects
	private int stack;
	public Giant(String name) {
		super(155, 10, 50, 2, 0, name);
	}
	public void special(Character c) {
		spendEnergy();
		System.out.println(getName() + " heals and pounds the ground!");
		setHealth(getHealth() + 5 * stack);
		c.setHealth(getHealth() - 5 * stack);
	}
	public void applyEffects() {
		stack++;
		setHealth(getHealth() + stack);
		if(getRejuvenated() && getCorrupted()) {
			corrupted(false);
			rejuvenated(false);
		}
		else if(getRejuvenated()) {
			rejuvenated(false);
			stack++;
		}
		else if(getCorrupted()) {
			corrupted(false);
			stack = 0;
		}
		setDamage(10 + 5 * stack);
		setAccuracy(getAccuracy() + stack);
	}
	public static void info() {
		System.out.println("Giant");
		System.out.println("HP: 155 Damage: 10 Accuracy: 50 Energy: 2 Healing: 0");
		System.out.println("A character of type Giant relies on surviving until the endgame, when it'll reign supreme.");
		System.out.println("Every turn, the Giant gains a stack and heals for the number of stacks. Each one increases his stats by a factor.");
		System.out.println("His/her damage and accuracy will be increased by a factor of 5 and 1 respectively.");
		System.out.println("The Giant's special will heal 5 and deal 5 damage per stack.");
		System.out.println("He/she is not effeced by rejuvenation and corruption.");
		System.out.println("Rejuvenation adds a stack, while corruption takes all his stacks away.");
		System.out.println("Both last for only 1 turn, but it makes the Dark Knight an effective counter.");
	}
}
