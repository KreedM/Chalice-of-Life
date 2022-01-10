package Game;

import Characters.*;
import Characters.Character;
import java.util.Scanner;

public class Game {
	int places;
	Scanner s;
	Player[] players;
	String winner;
	boolean replay;
	public Game() {
		s = new Scanner(System.in);
	}
	public void start() throws InterruptedException {
		welcome();
		do {
			init();
			while(true) {
				if(places != 1) {
					System.out.println("*Start of Round*\n");
					Thread.sleep(500);
				}
				for(int i = 0; i < players.length; i++) {
					if(players[i].getWithdrawn()) {
						players[i].setWithdrawn(false);
						players[i].setWithdrawnBefore(true);
					}
					players[i].getCharacter().applyEffects();
					if(!players[i].getDead() && players[i].getCharacter().getHealth() <= 0) {
						players[i].setDead(true);
						players[i].setPlace(places);
						places--;
					}
					if(!players[i].getDead()) {
						if(places == 1) {
							winner = players[i].getName();
							break;
						}
						System.out.println(players[i].getName() + "'s Turn\n");
						System.out.print("Show stats? (y/n) ");
						if(s.next().equals("y")) {
							for(int j = 0; j < players.length; j++) {
								players[j].showStats(s);
							}
							Thread.sleep(1000);
						}
						System.out.println();
						chooseMove(players, players[i]);
						Thread.sleep(500);
					}
				}
				if(winner != null) {
					break;
				}
				System.out.println("*End of Round*\n");
				Thread.sleep(500);
				System.out.println("Recap: ");
				for(int i = 0; i < players.length; i++) {
					System.out.println(players[i].getName() + ": " + players[i].getCharacter().getHealth());
				}
				System.out.println();
				Thread.sleep(1000);
			}
			System.out.println("\nGame Over!");
			System.out.println("The winner is " + winner + "!\n");
			System.out.print("Play again? (y/n) ");
			if(s.next().equals("y")) {
				replay = true;
				System.out.println("\n*New Game*\n");
				Thread.sleep(1000);
			}
			else {
				replay = false;
			}
		}
		while(replay);
		System.out.println("This game was created by Derek Meng, who was inspired by Mr. Smith to do so.");
		s.close();
	}
	private void init() throws InterruptedException {
		int numPlayers = 0;
		do {
			System.out.print("How many players? ");
			numPlayers = s.nextInt();
		}
		while(numPlayers < 2);
		places = numPlayers;
		players = new Player[numPlayers];
		String[] names = new String[numPlayers];
		for(int i = 0; i < players.length; i++) {
			System.out.println("PLAYER " + (i + 1) + "\n");
				players[i] = new Player(s, names, i);
		}
		applyPerk(players);
	}
	private void chooseMove(Player[] pList, Player p) {
		int notWithdrawn = 0;
		for(int i = 0; i< pList.length; i++) {
			if(!pList[i].getWithdrawn()) {
				notWithdrawn++;
			}
		}
		String move = "";
		while(true) {
			System.out.print("Do you: Attack (a), Special (s), heal (h), or withdraw (w)? ");
			move = s.next().toLowerCase();
			if(move.equals("a") || move.equals("s") || move.equals("h") || move.equals("w")) {
				if(move.equals("w")) {
					if(p.getWithdrawnBefore()) {
						System.out.println("You've already withdrawn before!");
						continue;
					}
					p.setWithdrawn(true);
					break;
				}
				else if(move.equals("h")) {
					p.getCharacter().heal();
					break;
				}
				if(notWithdrawn == 1) {
					System.out.println("Everyone else is withdrawn. Try another move. ");
					continue;
				}
				else if(move.equals("a")) {
					p.getCharacter().attack(getAttacked(pList));
					break;
				}
				else if(move.equals("s")) {
					if(p.getCharacter().getEnergy() == 0) {
						System.out.println("You don't have any more energy!");
						continue;
					}
					p.getCharacter().special(getAttacked(pList));
					break;
				}
			}
		}
	}
	private Character getAttacked(Player[] p) {
		String name;
		while(true) {
			System.out.print("Who do you attack? ");
			name = s.next().toLowerCase();
			for(int i = 0; i < p.length; i++) {
				if(p[i].getName().toLowerCase().equals(name)) {
					return p[i].getCharacter();
				}
			}
		}
	}
	private void applyPerk(Player[] pList) {
		int KnightCount = 0;
		for(int i = 0; i < pList.length; i++) {
			if(pList[i].getCharacter() instanceof LightKnight || pList[i].getCharacter() instanceof DarkKnight) {
				KnightCount++;
			}
			if(KnightCount == 2) {
				System.out.println("Knights are powered by each other!");
				for(int j = 0; j < pList.length; j++) {
					if(pList[j].getCharacter() instanceof LightKnight) {
						((LightKnight) pList[j].getCharacter()).perk();
					}
					else if(pList[j].getCharacter() instanceof DarkKnight) {
						((DarkKnight) pList[j].getCharacter()).perk();
					}
				}
				break;
			}
		}
	}
	private void welcome() throws InterruptedException {
		System.out.println("*Please play this game with a full screen*");
		Thread.sleep(3000);
		System.out.print("Game starting in ");
		for(int i = 3; i > 0; i--) {
			System.out.print(i + "... ");
			Thread.sleep(1000);;
		}
		System.out.println("\nWelcome to the game known as the Chalice of Life!");
		System.out.println("Battle other players in your pursuit for the legendary, truly omnipotent chalice!");
		System.out.println("With the chalice, the impossible becomes the possible. ");
		System.out.println("It can bestow, morph, and extend the life of any being!");
		System.out.println("Legends say the artifact was created by ancient wizards possessing arcane knowledge. Others say it was created by God himself as a gift to humanity!");
		System.out.println("Now, it is time to being your struggle, for the legendary, truly omnipotent chalice!");
		String input;
		while(true) {
			System.out.print("Type b to begin, or h for help: ");
			input = s.next().toLowerCase();
			System.out.println();
			if(input.equals("b")) {
				break;
			}
			else if(input.equals("h")) {
				displayInstructions();
			}
		}
	}
	private void displayInstructions() {
		System.out.println("The goal of the game is to zero out everyone else's health.");
		System.out.print("At any point in the game, each surviving character has the ability to");
		System.out.println(" attack, perform a special move, heal, or withdraw.");
		System.out.println("Every move, asides from withdraw, has a chance to fail. This is determined by your accuracy stat.");
		System.out.println("The higher your accuracy is, the more likely your hit will connect.");
		System.out.println("When you withdraw, you prevent yourself from being targeted until your next turn.");
		System.out.println("It is best to use this to force your opponents' move, or remove yourself from danger.");
		System.out.println("You only get to withdraw once, so use it sparingly.");
		System.out.println("Every character has a unique special move with powerful effects depending on your character.");
		System.out.println("However, you spend energy every time you use a special, so use it wisely too.");
		String ch;
		while(true) {
			System.out.println("To get more info on a specific character, type its name. Otherwise, type x to exit.");
			System.out.println("Assassin (A), Dark Knight (DK), Elf (E), Giant (G), Light Knight (LK), Wizard (W)");
			System.out.print("Input: ");
			ch = s.next().toUpperCase();
			if(ch.equals("A")) {
				Assassin.info();
			}
			else if(ch.equals("DK")) {
				DarkKnight.info();
			}
			else if(ch.equals("E")) {
				Elf.info();
			}
			else if(ch.equals("G")) {
				Giant.info();
			}
			else if(ch.equals("LK")) {
				LightKnight.info();
			}
			else if(ch.equals("W")) {
				Wizard.info();
			}
			else if(ch.equals("X")) {
				break;
			}
		}
	}
}
