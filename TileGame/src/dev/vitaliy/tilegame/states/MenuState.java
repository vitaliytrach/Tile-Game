package dev.vitaliy.tilegame.states;

import java.awt.Graphics;
import dev.vitaliy.tilegame.Handler;
import dev.vitaliy.tilegame.gfx.Assets;
import dev.vitaliy.tilegame.ui.ClickListener;
import dev.vitaliy.tilegame.ui.UIImageButton;
import dev.vitaliy.tilegame.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(250, 200, 128, 64, Assets.startButton, new ClickListener() {
			
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics graphics) {
		uiManager.render(graphics);
	}

}
