package dev.vitaliy.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	// Width and height of 1 grid space
	private static final int width = 64, height = 64;
	
	public static BufferedImage dirt, grass, rock, tree, woodLogs;
	public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight, playerStill;
	public static BufferedImage[] startButton;
	public static BufferedImage dirt1, dirt2;
	public static BufferedImage person;
	
	// Method that runs only once whose purpose
	// is to load all the assets our game will need
	public static void init() {
		Spritesheet spritesheet = new Spritesheet(ImageLoader.loadImage("/textures/spritesheet_3.png"));
		Spritesheet spritesheetUI = new Spritesheet(ImageLoader.loadImage("/textures/spritesheet_UI.png"));
		Spritesheet tilesSheet_1 = new Spritesheet(ImageLoader.loadImage("/textures/Iso_tiles_1.png"));
		
		dirt1 = tilesSheet_1.crop(0 * width, 0 * height, width * 2, height);
		dirt2 = tilesSheet_1.crop(2 * width, 0 * height, width * 2, height);
		
		playerDown = new BufferedImage[2];
		playerUp = new BufferedImage[2];
		playerLeft = new BufferedImage[1];
		playerRight = new BufferedImage[1];
		playerStill = new BufferedImage[1];
		
		startButton = new BufferedImage[2];
		
		playerDown[0] = spritesheet.crop(1 * width, 0 * height, width, height);
		playerDown[1] = spritesheet.crop(2 * width, 0 * height, width, height);
		
		playerUp[0] = spritesheet.crop(3 * width, 0 * height, width, height);
		playerUp[1] = spritesheet.crop(0 * width, 1 * height, width, height);
		
		playerRight[0] = spritesheet.crop(1 * width, 1 * height, width, height);
		
		playerLeft[0] = spritesheet.crop(2 * width, 1 * height, width, height);
		
		playerStill[0] = spritesheet.crop(0 * width, 0 * height, width, height);
		
		startButton[0] = spritesheetUI.crop(0 * width, 0 * height, width * 2, height);
		startButton[1] = spritesheetUI.crop(2 * width, 0 * height, width * 2, height);
		
		dirt = spritesheet.crop(1 * width, 2 * height, width, height);
		grass = spritesheet.crop(0 * width, 2 * height, width, height);
		rock = spritesheet.crop(2 * width, 2 * height, width, height);
		tree = spritesheet.crop(3 * width, 2 * height, width, height * 2);
		woodLogs = spritesheetUI.crop(0 * width, 1 * height, width, height);
		
		person = spritesheet.crop(0 * width, 0 * height, width, height);

	}

}
