package dev.vitaliy.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	// Adding canvas to JFrame makes us see it
	private JFrame frame;
	
	// Allows us to draw graphics on it
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	// Initializes JFrame
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		
		// Ends program on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Not resizable
		frame.setResizable(false);
		
		// Opens frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		
		// By default JFrame is not visible
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
				
	}
	
	// GETTERS AND SETTERS
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
