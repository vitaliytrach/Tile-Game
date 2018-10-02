package dev.vitaliy.tilegame;

import dev.vitaliy.tilegame.gfx.GameCamera;
import dev.vitaliy.tilegame.input.KeyManager;
import dev.vitaliy.tilegame.input.MouseManager;
import dev.vitaliy.tilegame.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler (Game game) {
		this.game = game;
	}
			
	// SETTERS AND GETTERS FOR CLASS VARIABLES
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
