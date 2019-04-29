package com.mygdx.screens;

//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.objetos.Player;

import com.mygdx.asteroids.AsteroidsGame;

public class MainGameScreen implements Screen {

  // Declarei uma instância da classe principal
  AsteroidsGame game;

  // Criei uma instância da classe World, esta é necessária para o uso de Box2D
  // Param:
  // Vector2(a, b):
  // a = gravidade no eixo x
  // b = gravidade no eixo y
  // Boolean:
  // indica se o programador quer que objetos "durmam", isto reduz uso da cpu
  World world = new World(new Vector2(0, 0), true);

  // Criando imagens
  Texture img;
  Texture img2;

  Player player = new Player(50, 50, world);

  float x = 300;
  float y = 100;

  public MainGameScreen(AsteroidsGame game) {

    this.game = game;

  }

  @Override
  public void show() {

    img = new Texture("imgs/SpaceShip24x24.png");
    img2 = new Texture("imgs/SpaceShip-96x96.png");

  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    if (Gdx.input.isKeyPressed(Keys.RIGHT))
      x += 10 * Gdx.graphics.getDeltaTime();

    if (Gdx.input.isKeyPressed(Keys.LEFT))
      x -= 10;

    if (Gdx.input.isKeyPressed(Keys.UP))
      y += 10;

    if (Gdx.input.isKeyPressed(Keys.DOWN))
      y -= 10;

    if (x > 700)
      x = 0;
    if (x < 0)
      x = 700;
    if (y > 700)
      y = 0;
    if (y < 0)
      y = 700;

    game.batch.begin();
    game.batch.draw(img, x, y);
    game.batch.draw(img2, 400, 300);
    game.batch.end();

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

    // game.batch.dispose();
    // img.dispose();

  }

}