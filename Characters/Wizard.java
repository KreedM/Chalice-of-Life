package Characters;

import java.util.Scanner;

public class Wizard extends Character{
	private Scanner sc;
	public Wizard(String name) {
		super(130, 0, 75, 1, 0, name);
		sc = new Scanner(System.in);
	}
	public void attack(Character c) {
		int spend = howMuchSpend();
		System.out.print(getName() + " attempts to summon a lightning bolt at " + c.getName() + "...and ");
		if(hitOrMiss()) {
			c.setHealth(c.getHealth() - 15 * spend);
		}
	}
	public void special(Character c) {
		int spend = howMuchSpend();
		c.setHealth(c.getHealth() - 7 * spend);
		setHealth(getHealth() + 7 * spend);
		System.out.println(getName() + " siphons life from " + c.getName() + "!");
		c.corrupted(true);
	}
	public void heal() {
		System.out.print(getName() + " attempts to gather mana...and ");
		if(hitOrMiss()) {
			setEnergy(getEnergy() + 2);
		}
	}
	public int howMuchSpend() {
		int spend = Integer.MAX_VALUE;
		while(spend < 0 || spend > getEnergy()) {
			System.out.print("How much energy do you spend? ");
			spend = Integer.parseInt(sc.next());
			if(spend < 0 || spend > getEnergy()) {
				System.out.println("You don't have enough energy!");
			}
		}
		setEnergy(getEnergy() - spend);
		return spend;
	}
	public void applyEffects() {
		setEnergy(getEnergy() + 1);
		if(getRejuvenated() && getCorrupted()) {
			corrupted(false);
			rejuvenated(false);
		}
		else if(getRejuvenated()) {
			rejuvenated(true);
			setEnergy(getEnergy() + 1);
		}
		else if(getCorrupted()) {
			corrupted(false);
		}
	}
	public static void info() {
		System.out.println("Wizard");
		System.out.println("HP: 130 Damage: 0 Accuracy: 80 Energy: 2 Healing: 0");
		System.out.println("The wizard is unique in that he/she uses energy for his moves to blast powerful moves at opponents.");
		System.out.println("When the wizard chooses to heal, he/she will gain 2 energy instead. She/he will also gain 1 energy each turn.");
		System.out.println("Attacks and specials all depend on the amount of energy the player chooses to use.");
		System.out.println("The wizard's attack conjures a lightning bolt that does 15 damage per energy spent.");
		System.out.println("His/her special, on the other hand, deals damages the opponent and heals 7 per stack.");
		System.out.println("As a wizard, corruption doesn't affect him/her.");
		System.out.println("Rejuvenation still does, and it additionally restores 1 energy.");
	}
}
