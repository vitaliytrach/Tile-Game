package dev.vitaliy.tilegame.worlds;

import java.awt.Graphics;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.Entity;
import dev.vitaliy.tilegame.entities.EntityManager;
import dev.vitaliy.tilegame.entities.creature.Person;
import dev.vitaliy.tilegame.entities.creature.Player;
import dev.vitaliy.tilegame.items.ItemManager;
import dev.vitaliy.tilegame.tiles.Tile;
import dev.vitaliy.tilegame.utils.Utils;

public class World {
	
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	
	private EntityManager entityManager;
	
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 0, 0), new Person(Tile.TILE_WIDTH / 4, - Tile.TILE_HEIGHT / 2, handler));
		itemManager = new ItemManager(handler);
		loadWorld(path);
		
		entityManager.getPlayer().setxPos(spawnX);
		entityManager.getPlayer().setyPos(spawnY);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics graphics) {
		
		float x1 = handler.getGameCamera().getxOffset();
		float y1 = handler.getGameCamera().getyOffset();
		
		//int xStart = Math.max(0, (int) handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH * 2);
		//int xEnd = Math.min(width, (int) (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH * 2 + 1);
		//int yStart = Math.max(0, (int) handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		//int yEnd = Math.min(height, (int) (handler.getGameCamera().getyOffset() + handler.getHeight()) /  Tile.TILE_HEIGHT + 1);
		
		int xStart = 0;
		int xEnd = width;
		int yStart = 0;
		int yEnd = height;
			
		// X position starts at the middle of the Map (minus tile width because 1 tile is 2 X 1)
		int widthStart = 0;
		int heightStart = 0;
		
		
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				
				getTile(x, y).render(graphics, (int) (x * Tile.TILE_WIDTH / 2 - handler.getGameCamera().getxOffset() + widthStart),
						(int) (y * Tile.TILE_HEIGHT / 2 - handler.getGameCamera().getyOffset()) + heightStart);
				
				heightStart += 32;
			}
			widthStart -= Tile.TILE_WIDTH / 2;
			heightStart = yStart * 32;
		}
		
		//System.out.println(handler.getGameCamera().getxOffset() + ", " + handler.getGameCamera().getyOffset());
		
		//System.out.println("RESTART!");
		
		entityManager.render(graphics);
		//itemManager.render(graphics);
	}
	
	public Tile getTile(int x, int y) {
		// Making sure X and Y are inside the boundary of the map
		// If it is outside just pretend its a grass tile;
		if(x < 0 || y < 0 || x >= width || y >= width) 
			return Tile.grassTile;
		
		Tile tile = Tile.tiles[tiles[x][y]];
		if(tile == null) {
			return Tile.dirtTile;
		}
		
		return tile;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				getTile(x, y).setxPos(x);
				getTile(x, y).setyPos(y);
			}
		}
		
	}
	
	// GETTERS AND SETTERS
	
	public ItemManager getItemManager() {
		return itemManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
