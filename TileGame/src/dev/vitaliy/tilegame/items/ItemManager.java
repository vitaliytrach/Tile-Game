package dev.vitaliy.tilegame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.vitaliy.tilegame.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick() {
		if(items == null) {
			return;
		}
		
		Iterator<Item> iterator = items.iterator();
		
		while(iterator.hasNext()) {
			Item item = iterator.next();
			item.tick();
			if(item.getCount() == Item.PICKED_UP) {
				iterator.remove();
			}
		}		
	}
	
	public void render(Graphics graphics) {
		if(items == null) {
			return;
		}
		
		for(Item item : items) {
			item.render(graphics);
		}
	}
	
	public void addItem(Item item) {
		item.setHandler(handler);
		items.add(item);
	}

	// GETTERS AND SETTERS
	
	public Handler getHandler() {
		return handler;
	}

}
