package dev.vitaliy.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.vitaliy.tilegame.gfx.Assets;

public class Rock extends Tile{
	
	public Rock(int id) {
		super(Assets.rock, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
