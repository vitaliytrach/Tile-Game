package dev.vitaliy.tilegame.entities.statics;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

}
