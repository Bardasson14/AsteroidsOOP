package com.mygdx.asteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.MainGameScreen;

public class AsteroidsGame extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create() {

		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));

	}

	// Player player = new Player(50, 50);

	@Override
	public void render() {
		super.render();
	}

}




