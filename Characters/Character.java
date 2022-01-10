package Characters;

public abstract class Character { //Add withdraw to player class
	private int health;
	private int energy;
	private int accuracy;
	private int damage;
	private int healing;
	private String name;
	private boolean corrupted;
	private boolean rejuvenated;
	public Character(int health, int damage, int accuracy, int energy, int healing, String name) {
		this.health = health;
		this.damage = damage;
		this.accuracy = accuracy;
		this.energy = energy;
		this.healing = healing;
		this.name = name;
	}
	public void attack(Character c) {
		rejuvenated = false;
		System.out.print(name + " attempts to attack " + c.name + "... and ");
		if(hitOrMiss()) {
			c.health -= damage;
		}
	}
	public void heal() {
		System.out.print(name + " attempts to heal... and ");
		if(hitOrMiss()) {
			corrupted = false;
			health += healing;
		}
	}
	public void applyEffects() {
		if(rejuvenated && corrupted) {
			rejuvenated = false;
			corrupted = false;
		}
		else if(rejuvenated) {
			System.out.println(name + " is rejuvenated!");
			health += 10;
		}
		else if(corrupted) {
			System.out.println(name + " is corrupted!");
			health -= 15;
		}
	}
	public abstract void special(Character c);
	protected boolean hitOrMiss() {
		if((int)(Math.random() * 100) < accuracy) {
			System.out.print("succeeds!\n");
			return true;
		}
		System.out.print("fails!\n");
		return false;
	}
	protected void setHealth(int n) {
		health = n;
	}
	protected void setDamage(int n) {
		damage = n;
	}
	protected void setAccuracy(int n) {
		accuracy = n;
	}
	protected void setHealing(int n) {
		healing = n;
	}
	protected void corrupted(boolean n) {
		corrupted = n;
	}
	protected void rejuvenated(boolean n) {
		rejuvenated = n;
	}
	public String getName() {
		return name;
	}
	public int getDamage() {
		return damage;
	}
	public int getHealth() {
		return health;
	}
	public int getHealing() {
		return healing;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public boolean getCorrupted() {
		return corrupted;
	}
	public boolean getRejuvenated() { //Delete if unneeded{
		return rejuvenated;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int n) {
		energy = n;
	}
	public void spendEnergy() {
		energy--;
	}
}
