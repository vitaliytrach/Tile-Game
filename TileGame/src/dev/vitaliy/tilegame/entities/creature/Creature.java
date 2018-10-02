package dev.vitaliy.tilegame.entities.creature;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.Entity;
import dev.vitaliy.tilegame.tiles.Tile;

public abstract class Creature extends Entity{
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
						   DEFAULT_CREATURE_HEIGHT = 64;

	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float xPos, float yPos, int width, int height) {
		super(handler, xPos, yPos, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		// Check if there will be an entity collision on the next move (x direction)
		if(!checkEntityCollisions(xMove, 0f)) 
			moveX();
		
		// Check if there will be an entity collision on the next move (y direction)
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		
		if(xMove > 0 && xPos - width / 2 < ((handler.getWorld().getWidth() - 1) * Tile.TILE_WIDTH)) { // Moving right
			int tx = (int) (xPos + xMove + boundaryBox.x + boundaryBox.width) / Tile.TILE_WIDTH;
			
			// If the upper right of our boundaryBox is not solid
			// AND
			// If the lower right of our boundary box is not solid
			// then move right
			if(!collisonWithTile(tx, (int) (yPos + boundaryBox.y) / Tile.TILE_HEIGHT) &&
					!collisonWithTile(tx, (int) (yPos + boundaryBox.y + boundaryBox.height) / Tile.TILE_HEIGHT)) {
				xPos += xMove;
			}else {
				xPos = tx * Tile.TILE_WIDTH - boundaryBox.x - boundaryBox.width - 1;
			}
			
		}else if(xMove < 0 && (xPos + width / 2) > 0) { // Moving left
			int tx = (int) (xPos + xMove + boundaryBox.x) / Tile.TILE_WIDTH;
			
			// If the upper right of our boundaryBox is not solid
			// AND
			// If the lower right of our boundary box is not solid
			// then move right
			if(!collisonWithTile(tx, (int) (yPos + boundaryBox.y) / Tile.TILE_HEIGHT) &&
					!collisonWithTile(tx, (int) (yPos + boundaryBox.y + boundaryBox.height) / Tile.TILE_HEIGHT)) {
				xPos += xMove;
			}else {
				xPos = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - boundaryBox.x;
			}
		}
	}
	
	public void moveY() {

		if(yMove < 0 && (yPos + height / 2) > 0) { // Moving up
			int ty = (int) (yPos + yMove + boundaryBox.y) / Tile.TILE_HEIGHT;
			
			if(!collisonWithTile((int) (xPos + boundaryBox.x) / Tile.TILE_WIDTH, ty) &&
					!collisonWithTile((int) (xPos + boundaryBox.x + boundaryBox.width) / Tile.TILE_WIDTH, ty)){
				yPos += yMove;
			} else {
				yPos = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - boundaryBox.y;
			}
			
		}else if(yMove > 0 && yPos < ((handler.getWorld().getWidth() - 1) * Tile.TILE_HEIGHT)) { // Moving down
			int ty = (int) (yPos + yMove + boundaryBox.y + boundaryBox.height) / Tile.TILE_HEIGHT;
			
			if(!collisonWithTile((int) (xPos + boundaryBox.x) / Tile.TILE_WIDTH, ty) &&
					!collisonWithTile((int) (xPos + boundaryBox.x + boundaryBox.width) / Tile.TILE_WIDTH, ty)){
				yPos += yMove;
			} else {
				yPos = ty * Tile.TILE_HEIGHT - boundaryBox.y - boundaryBox.height - 1;
			}
		
		}
	}
	
	// Takes tile x and y coordinate, returns true
	// if that tile is solid, false if it is not solid
	protected boolean collisonWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	// GETTERS AND SETTERS
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
}
