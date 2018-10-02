package dev.vitaliy.tilegame.entities.statics;

import java.awt.Graphics;

import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.gfx.Assets;
import dev.vitaliy.tilegame.items.Item;
import dev.vitaliy.tilegame.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		
		// BoundaryBox specific to the tree
		boundaryBox.x = 29;
		boundaryBox.y = 72;
		boundaryBox.width = 11;
		boundaryBox.height = 56;
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.tree, (int) (xPos - handler.getGameCamera().getxOffset()), 
				(int) (yPos - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodLogsItem.createNew((int) xPos, (int) yPos));
	}

}
