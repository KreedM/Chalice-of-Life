package Game;

import java.util.Scanner;
import Characters.*;
import Characters.Character;


public class Player {
	private String name;
	private Character c;
	private boolean withdrawn;
	private boolean withdrawnBefore;
	private int place;
	private boolean dead;
	public Player() {
		
	}
	
	public Player(Scanner s, String[] names, int i) {
		boolean skip = false;
		while(true) { 
			System.out.print("What is your name? ");
			name = s.next();
			for(int j = i - 1; j > -1; j--) {
				if(name.equals(names[j])) {
					skip = true;
					break;
				}
			}
			if(skip) {
				System.out.println("That name has already been taken!");
				skip = false;
			}
			else {
				System.out.print("Your name is " + name + "? (y/n) ");
				if(s.next().toLowerCase().equals("y")) {
					names[i] = name;
					break;
				}
			}
		}
		chooseCharacter(s);
	}
	private void chooseCharacter(Scanner s) {
		boolean isSelected = false;
		while(!isSelected){
			System.out.print("Choose your character (Assassin (A), Dark Knight (DK), Elf (E), Giant (G), Light Knight (LK)): ");
			String choice = s.next().toUpperCase();
			switch(choice) {
				case "A": c = new Assassin(name); isSelected = true; break; 
				case "DK": c = new DarkKnight(name); isSelected = true; break;
				case "E": c = new Elf(name); isSelected = true; break;
				case "G": c = new Giant(name); isSelected = true; break;
				case "LK": c = new LightKnight(name); isSelected = true; break;
				case "W": c = new Wizard(name); isSelected = true; break;
			}
		}
	}
	protected void showStats(Scanner s) {
		System.out.println(name + "'s Stats");
		System.out.println("HP: " + c.getHealth() + " Damage: " + c.getDamage() + " Energy: " + c.getEnergy() + " Accuracy: " + c.getAccuracy() + " Healing: " + c.getHealing());
	}
	protected void setName(String name){
		this.name = name;
	}
	protected String getName() {
		return name;
	}
	protected Character getCharacter() {
		return c;
	}
	protected boolean getWithdrawn() {
		return withdrawn;
	}
	protected void setWithdrawn(boolean n) {
		withdrawn = n;
	}
	protected boolean getWithdrawnBefore() {
		return withdrawnBefore;
	}
	protected void setWithdrawnBefore(boolean n) {
		withdrawnBefore = n;
	}
	protected int getPlace() {
		return place;
	}
	protected void setPlace(int n) {
		place = n;
	}
	protected boolean getDead() {
		return dead;
	}
	protected void setDead(boolean n) {
		dead = n;
	}
}
