package Characters;

public class LightKnight extends Character{
	public LightKnight(String name) {
		super(140, 15, 70, 2, 25, name);
	}
	public void perk() {
		setHealth(150);
		setDamage(20);
		setAccuracy(75);
		setHealing(35);
	}
	public void special(Character c) {
		spendEnergy();
		System.out.print(getName() + " attempts to purge " + c.getName() + " of all evil...and ");
		if(hitOrMiss()) {
			c.setHealth(c.getHealth() - 60);
			c.rejuvenated(true);
			rejuvenated(true);
		}
	}
	public static void info() {
		System.out.println("Light Knight");
		System.out.println("HP: 140 Damage: 15 Accuracy: 70 Energy: 2 Healing: 25");
		System.out.println("A character of type Light Knight relies on his balanced damage and health.");
		System.out.println("He/she is almost the same as the Dark Knight, with exception of possessing more healing.");
		System.out.println("His/her special cleanses the opponent, dealing 60 damage but also giving him/her rejuvenation.");
		System.out.println("Rejuvenation will restore 10 health every turn until an attack is attempted.");
		System.out.println("Specials do not count as attacks.");
		System.out.println("If 2 or more knights exist in the game, they'll receive a buff.");
		System.out.println("In the case of the Light Knight, his/her new stats will be:");
		System.out.println("HP: 150 Damage: 20 Accuracy: 75 Energy: 2 Healing: 35");
	}
}