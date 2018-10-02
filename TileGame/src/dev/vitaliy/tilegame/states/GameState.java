package dev.vitaliy.tilegame.states;

import java.awt.Graphics;

import dev.vitaliy.tilegame.Game;
import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.creature.Player;
import dev.vitaliy.tilegame.entities.statics.Tree;
import dev.vitaliy.tilegame.worlds.World;

public class GameState extends State{
	
	private World world;

	public GameState(Handler handler) {
		super(handler);

		world = new World(handler, "res/worlds/world2.txt");
		handler.setWorld(world);
		name = "game state";
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics graphics) {
		world.render(graphics);
	}

}
