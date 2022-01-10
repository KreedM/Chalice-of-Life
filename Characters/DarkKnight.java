package Characters;

public class DarkKnight extends Character{
	public DarkKnight(String name) {
		super(140, 25, 70, 2, 15, name);
	}
	public void perk() {
		setHealth(150);
		setDamage(35);
		setAccuracy(75);
		setHealing(20);
	}
	public void special(Character c) {
		spendEnergy();
		System.out.print(getName() + " attempts to corrupt " + c.getName() + "...and ");
		if(super.hitOrMiss()) {
			c.setHealth(c.getHealth() - 35);
			c.corrupted(true);
		}
	}
	public static void info() {
		System.out.println("Dark Knight");
		System.out.println("HP: 140 Damage: 25 Accuracy: 70 Energy: 2 Healing: 15");
		System.out.println("A character of type Dark Knight relies on his balanced damage and health.");
		System.out.println("He/she is almost the same as the Light Knight, with exception of possessing more damage.");
		System.out.println("His/her special corrupts the opponent, dealing 35 damage and making he/she lose health every turn.");
		System.out.println("This effect lasts until a player succesfully heals.");
		System.out.println("Specials do not count as healing.");
		System.out.println("If 2 or more knights exist in the game, they'll receive a buff.");
		System.out.println("In the case of the Dark Knight, his/her new stats will be:");
		System.out.println("HP: 150 Damage: 35 Accuracy: 75 Energy: 2 Healing: 20");
	}
}
