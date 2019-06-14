
package com.mygdx.asteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.MainGameScreen;
import com.mygdx.screens.Menu;
import com.mygdx.screens.Score;

public class AsteroidsGame extends Game {

	public SpriteBatch batch;

	@Override
	public void create() {

		batch = new SpriteBatch();
		this.setScreen(new Menu(this));

	}

	// Player player = new Player(50, 50);

	@Override
	public void render() {
		super.render();
	}

}




