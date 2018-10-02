package dev.vitaliy.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.vitaliy.tilegame.display.Display;
import dev.vitaliy.tilegame.gfx.Assets;
import dev.vitaliy.tilegame.gfx.GameCamera;
import dev.vitaliy.tilegame.input.KeyManager;
import dev.vitaliy.tilegame.input.MouseManager;
import dev.vitaliy.tilegame.states.GameState;
import dev.vitaliy.tilegame.states.MenuState;
import dev.vitaliy.tilegame.states.State;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	private String title;
	
	// Variable for the games tick rate
	private int tps = 60;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	
	// States
	public State gameState;
	private State menuState;
	
	// Inputs
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	// Game constructor that starts the game and
	// gives the window dimensions
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	// The init method initializes the window
	// using the display class
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(this, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	// Method that updates all game logic
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();		
		}		
	}
	
	// Render method that renders all game
	// graphics as fast as the users computer can
	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		
		// Check if canvas has a buffer strategy or not
		if(bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		graphics = bufferStrategy.getDrawGraphics();
		
		// Clear screen
		graphics.clearRect(0, 0, width, height);
		
		// Checks if the game is in the game state or not
		if(State.getState() != null) {
			State.getState().render(graphics);
		}
		
		bufferStrategy.show();
		graphics.dispose();	
	}

	// Method that overrides the runnable interfaces method "run".
	// It updates all game logic and rendering using System.nanotime
	@Override
	public void run() {
		init();
		
		// Variable for how many times the tick method should run every second
		double timePerTick = 1000000000 / tps;
		
		double delta = 0;	
		long now;		
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int fps = 0;
		
		// Game loop
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			// Ticks the game at the rate of the tps variable
			if(delta >= 1) {
				tick();
				ticks++;
				delta--;				
			}
			
			// Prints the tickrate and FPS count every second
			if(timer >= 1000000000) {
				System.out.println("Ticks: " + ticks + ", Fps: " + fps);
				ticks = 0;
				fps = 0;
				timer = 0;
			}
			
			fps++;
			render();	
		}
		
		stop();
	}
	
	// Start method for the game thread to initialize on
	public synchronized void start() {
		
		// Check for if the game is already running or not
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		
		// Calls run method
		thread.start();
	}
	
	// Stop method for the game thread to stop
	public synchronized void stop() {
		
		// Check for if the game
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// GETTERS AND SETTERS
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
}
