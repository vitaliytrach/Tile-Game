package dev.vitaliy.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.creature.Person;
import dev.vitaliy.tilegame.entities.creature.Player;
import dev.vitaliy.tilegame.entities.statics.Tree;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private Person person;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderOrder = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getyPos() + a.getHeight()/2 < b.getyPos() + b.getHeight()/2) {
				return -1;
			}
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player, Person person) {
		this.handler = handler;
		this.player = player;
		this.person = person;
		entities = new ArrayList<Entity>();
		
		entities.add(new Tree(handler, 100, 100));
		entities.add(person);
		
	}
	
	public void tick() {
		Iterator<Entity> iterator = entities.iterator();
		
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			entity.tick();
			
			if(!entity.isActive()) {
				iterator.remove();
			}			
		}
		
		entities.sort(renderOrder );
	}
	
	public void render(Graphics graphics) {
		for(Entity e : entities) {
			e.render(graphics);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	// GETTERS AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
