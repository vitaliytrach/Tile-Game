package dev.vitaliy.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.Bounds;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;

	// Position of the entity
	protected float xPos, yPos;
	// Size of the entity
	protected int width, height;
	
	protected Handler handler;
	protected Rectangle boundaryBox;
	
	protected int health;
	protected boolean active = true;
	
	public Entity(Handler handler, float xPos, float yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;		
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		health = DEFAULT_HEALTH;
		
		// Default boundary box is the size of the entities image
		boundaryBox = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics graphics);
	
	public abstract void die();
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity entity : handler.getWorld().getEntityManager().getEntities())
		{
			// Check if entity in the foreach loop is itself
			if(entity.equals(this))
				continue;
			
			// Check if entity at 0,0 is intersecting an entity at x,y
			if(entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (xPos + boundaryBox.x + xOffset), (int) (yPos + boundaryBox.y + yOffset), boundaryBox.width, boundaryBox.height);
	}
	
	public void hurt(int damage) {
		health -= damage;
		if(health < 1) {
			active = false;
			die();
		}
	}
	
	// GETTERS AND SETTERS

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
