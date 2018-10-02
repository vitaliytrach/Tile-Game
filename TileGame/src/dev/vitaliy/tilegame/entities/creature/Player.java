package dev.vitaliy.tilegame.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.Entity;
import dev.vitaliy.tilegame.gfx.Animation;
import dev.vitaliy.tilegame.gfx.Assets;

public class Player extends Creature {
	
	// Default animation speed set to 200ms
	private static final short DEFAULT_ANIMATION_SPEED = 200;
	
	//Animations
	private Animation downAnimation, upAnimation, leftAnimation, rightAnimation, stillAnimation;
	private BufferedImage lastFrame;
	
	//Attack timer
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	
	// Direction keeps track of which direction the player is pointed
	// 0 = left
	// 1 = right
	// 2 = up
	// 3 = down
	// Player is looking down by default
	private byte direction = 3;
	
	
	public Player(Handler handler, float xPos, float yPos) {
		super(handler, xPos, yPos, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
				
		// BoundaryBox specific to my pixel character (for now)
		boundaryBox.x = 25;
		boundaryBox.y = 40;
		boundaryBox.width = 18;
		boundaryBox.height = 21;
		
		// Animations
		downAnimation = new Animation(200, Assets.playerDown);
		upAnimation = new Animation(200, Assets.playerUp);
		rightAnimation = new Animation(200, Assets.playerRight);
		leftAnimation = new Animation(200, Assets.playerLeft);
		stillAnimation = new Animation(200, Assets.playerStill);
		lastFrame = Assets.playerStill[0];
	}

	@Override
	public void tick() {
		// Animations
		downAnimation.tick();
		upAnimation.tick();
		leftAnimation.tick();
		rightAnimation.tick();
		stillAnimation.tick();
		
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnPlayer(this);
		
		// Attack
		checkAttack();
		
	}
	
	private void checkAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle collisionBounds = getCollisionBounds(0, 0);
		
		Rectangle attackRectangle = new Rectangle();
		
		// Size in pixels of the attack range
		int arSize = 20;
		attackRectangle.width = arSize;
		attackRectangle.height = arSize;
		
		// Attack logic
		if(handler.getKeyManager().attack && direction == 2) { // Attacking up
			attackRectangle.x = collisionBounds.x + (collisionBounds.width / 2) - (arSize / 2);
			attackRectangle.y = collisionBounds.y - arSize;
		} else if(handler.getKeyManager().attack && direction == 3) { // Attacking down
			attackRectangle.x = collisionBounds.x + (collisionBounds.width / 2) - (arSize / 2);
			attackRectangle.y = collisionBounds.y + arSize;
		} else if(handler.getKeyManager().attack && direction == 0) { // Attacking left
			attackRectangle.x = collisionBounds.x - arSize;
			attackRectangle.y = collisionBounds.y + collisionBounds.height / 2 - arSize / 2;
		} else if(handler.getKeyManager().attack && direction == 1) { // Attacking left
			attackRectangle.x = collisionBounds.x + collisionBounds.width;
			attackRectangle.y = collisionBounds.y + collisionBounds.height / 2 - arSize / 2;
		} else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
			
			if(entity.equals(this)) { // Check if entity is ourselves
				continue;
			}
			
			if(entity.getCollisionBounds(0, 0).intersects(attackRectangle)) {
				entity.hurt(1);
				return;
			}
		}
		
	}
	
	private void checkPickUp() {
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) 
			yMove = -speed;
		if(handler.getKeyManager().down) 
			yMove = speed;
		if(handler.getKeyManager().left) 
			xMove = -speed;
		if(handler.getKeyManager().right) 
			xMove = speed;		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(getCurrentAnimationFrame(), (int) (xPos - handler.getGameCamera().getxOffset()), 
				(int) (yPos - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if(xMove < 0) { // Moving left
			direction = 0;
			lastFrame = Assets.playerLeft[0];
			return leftAnimation.getCurrentFrame();
		}
		else if(xMove > 0) { // Moving right
			direction = 1;
			lastFrame = Assets.playerRight[0];
			return rightAnimation.getCurrentFrame();
		}
		else if(yMove < 0) { // Moving up
			direction = 2;
			lastFrame = Assets.playerUp[0];
			return upAnimation.getCurrentFrame();
		}
		else if(yMove > 0) { // Moving down
			direction = 3;
			lastFrame = Assets.playerDown[0];
			return downAnimation.getCurrentFrame();
		} else {
			return lastFrame;
		}

	}
	
	@Override
	public void die() {
		// TODO: Player dying
	}
	
	public byte getByte() {
		return direction;
	}

}
