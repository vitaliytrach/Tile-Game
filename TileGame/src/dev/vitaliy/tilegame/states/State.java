package dev.vitaliy.tilegame.states;

import java.awt.Graphics;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.Handler;

public abstract class State {
	
	protected Handler handler;
	public String name;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	// ABSTRACT METHODS
	
	public abstract void tick();
	
	public abstract void render(Graphics graphics);

}
