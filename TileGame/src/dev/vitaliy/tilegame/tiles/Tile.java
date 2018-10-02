package dev.vitaliy.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	// STATIC STUFF
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile grassTile2 = new GrassTile(3);
	public static Tile dirtTile = new Dirt(1);
	public static Tile rockTile = new Rock(2);
	
	// CLASS
	public static final int TILE_WIDTH = 128, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	private int xPos, yPos;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics graphics, int xPos, int yPos) {
		graphics.drawImage(texture, xPos, yPos, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
