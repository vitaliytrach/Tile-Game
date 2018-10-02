package dev.vitaliy.tilegame;

// The Launcher class's only function
// is to start the game
public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("myGame" , 600, 480);
		game.start();
	}

}
