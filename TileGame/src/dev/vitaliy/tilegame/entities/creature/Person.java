package dev.vitaliy.tilegame.entities.creature;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.Entity;
import dev.vitaliy.tilegame.gfx.Assets;
import dev.vitaliy.tilegame.tiles.Tile;

public class Person extends Entity{
	
	// default animation speed per frame = 200ms
	public static final short DEFAULT_MOVE_SPEED = 450; 
	
	// Which block the player is currently in
	private double tilePosX, tilePosY;
	private Handler handler;
	private BufferedImage person;
	private boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = false;
	private long timer, lastTime;
	
	public Person(int spawnX, int spawnY, Handler handler) {
		super(handler, spawnX, spawnY, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		xPos = spawnX;
		yPos = spawnY;
		tilePosX = 0.0;
		tilePosY = 0.0;
		this.handler = handler;
		this.person = Assets.playerStill[0];
		timer = 0;
		lastTime = System.currentTimeMillis();
		
	}
	
	public void checkMovement() {
		
		if(moveLeft || moveRight || moveDown || moveUp)
			return;
		
		if(handler.getKeyManager().right) {
			moveRight = true;
			tilePosX += 1.0;
			tilePosY += 0.5;
			timer = 0;
			lastTime = System.currentTimeMillis();
		} 
		else if (handler.getKeyManager().left) {
			moveLeft = true;
			timer = 0;
			lastTime = System.currentTimeMillis();
		}
		else if (handler.getKeyManager().down) {
			moveDown = true;
			timer = 0;
			lastTime = System.currentTimeMillis();
		}
		else if (handler.getKeyManager().up) {
			moveUp = true;
			timer = 0;
			lastTime = System.currentTimeMillis();
		}
		
	}
	
	public void move() {
		
		timer = System.currentTimeMillis() - lastTime;
		
		if(moveLeft) {
			
			
			if(timer < DEFAULT_MOVE_SPEED) {
				xPos -= 2.0f;
				yPos -= 1.0f;
			} else {
				moveLeft = false;
				timer = 0;
			}
			
		} 
		else if(moveRight) {
			
			System.out.println(xPos + " " + tilePosX * Tile.TILE_WIDTH / 4);
			System.out.println("	" + yPos + " " + tilePosY * Tile.TILE_HEIGHT / 2);
			
			
			if(xPos < (float) (tilePosX * Tile.TILE_WIDTH / 4) && yPos < (float) (tilePosY * Tile.TILE_HEIGHT / 2)) {
				xPos += 2.0f;
				yPos += 1.0f;
			} else {
				moveRight = false;
				timer = 0;
			}

		}
		else if(moveDown) {
			
			if(timer < DEFAULT_MOVE_SPEED) {
				xPos -= 2.0f;
				yPos += 1.0f;
			} else {
				moveDown = false;
				timer = 0;
			}
			
		}
		else if(moveUp) {
			
			if(timer < DEFAULT_MOVE_SPEED) {
				xPos += 2.0f;
				yPos -= 1.0f;
			} else {
				moveUp = false;
				timer = 0;
			}

		}		
	}
	
	public void tick() {
		checkMovement();
		move();
		handler.getGameCamera().centerOnPlayer(this);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(person, (int) (xPos - handler.getGameCamera().getxOffset()), 
				(int) (yPos - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	

}
