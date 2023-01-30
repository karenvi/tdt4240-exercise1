package com.karenvi.task4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.karenvi.task4.States.GameStateManager;
import com.karenvi.task4.States.MenuState;
// This works best on Desktop, not Android emulations
public class MyTask4 extends ApplicationAdapter {
	private SpriteBatch spriteBatch;
	private GameStateManager gameStateManager;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(spriteBatch);
	}

}