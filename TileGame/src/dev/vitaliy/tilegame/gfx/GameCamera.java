package dev.vitaliy.tilegame.gfx;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.entities.Entity;

public class GameCamera {
	
	private float xOffset, yOffset;
	private Game game;
	
	public GameCamera(Game game, float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.game = game;
	}
	
	public void centerOnPlayer(Entity player) {
		xOffset = (player.getxPos() - game.getWidth() / 2) + player.getWidth() / 2;
		yOffset = (player.getyPos() - game.getHeight() / 2) + player.getHeight() / 2;
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
	}
	
	// GETTERS AND SETTERS

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
