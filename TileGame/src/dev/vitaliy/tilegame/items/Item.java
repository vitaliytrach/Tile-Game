package dev.vitaliy.tilegame.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.gfx.Assets;

public class Item {
	
	// Handler
	
	public static Item[] items = new Item[256];
	public static Item woodLogsItem = new Item(Assets.woodLogs, "Wood Logs", 0);
	
	// CLASS STUFF
	
	public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32, PICKED_UP = -1;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected int x, y, count;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		items[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics graphics) {
		if(handler == null) {
			return;
		}
		
		render(graphics, (int) (x - handler.getGameCamera().getxOffset() + Assets.tree.getWidth()/2), 
				(int) (y - handler.getGameCamera().getyOffset() + Assets.tree.getHeight()/2));
	}
	
	public void render(Graphics graphics, int x, int y) {
		graphics.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}
	
	public Item createNew(int x, int y) {
		Item item = new Item(texture, name, id);
		item.setPosition(x, y);
		return item;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// GETTERS AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

}
