package com.mygdx.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.objects.Asteroids;
import com.mygdx.objects.Player;
import com.mygdx.objects.Shoot;

import com.mygdx.asteroids.AsteroidsGame;

public class MainGameScreen implements Screen {

  private static final String IMGS_AST30X30_PNG = "imgs/ast30X30.png";

// Declarei uma instância da classe principal
  AsteroidsGame game;

  SpriteBatch batch = new SpriteBatch();
  private float timeSeconds = 0;
  private float period = 1;
  private float generateCounter = 2f, generateTick = generateCounter;
  
  /* Criei uma instância da classe World, esta é necessária para o uso de Box2D
  * Param:
  * Vector2(a, b):
  * 	a = gravidade no eixo x
  * 	b = gravidade no eixo y
  * Boolean:
  * indica se o programador quer que objetos "durmam", isto reduz uso da cpu
  */

  World world = new World(new Vector2(0, 0), true);
  // Criando imagens
  Texture img = new Texture("imgs/SpaceShip24x24.png");
  Sprite sprite = new Sprite(img);

  //Criando diferentes tipos de asteróides
  Sprite sprites[] = new Sprite[3];
  Texture imgPeq = new Texture("imgs/ast30x30.png");
  sprites[0] = new Sprite(imgPeq);
  Texture imgMed = new Texture("imgs/ast65x66.png");
  sprites[1] = new Sprite(imgMed);
  Texture imgGd  = new Texture("imgs/ast100x101.png");
  sprites[2] = new Sprite(imgGd);

  // Instanciando player
  Player player = new Player(new Vector2(50, 50), world, sprite);
  
  // Lista de asteróides
  HashMap<Integer, Asteroids> asteroids = new HashMap<Integer, Asteroids>();
  //Chave: número de tiros que o asteroide pode sofrer
  //Player player2 = new Player(500, 300, world, sprite);

  // Tamanho da janela
  int WINDOWS_WIDTH  = Gdx.graphics.getWidth() ;
  int WINDOWS_HEIGHT = Gdx.graphics.getHeight();
  
  // CONSTRUTOR DA CLASSE
  public MainGameScreen(AsteroidsGame game) {

    this.game = game;

  }

  @Override
  public void show() {



  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    

    player.move();

    //asteroidSelection = 0 -> menor sprite (pode tomar um tiro)
    //e assim sucessivamente...
    if (generateTick > generateCounter){
      Random r = new Random();
      int asteroidSelection = r.nextInt(2);
      int y = r.nextInt(WINDOWS_HEIGHT);
      int dir_y = 1;
      if (y > (WINDOWS_HEIGHT/2)) dir_y = -1;
      int random_speed = r.nextInt(500) + 300;
      int random_x = r.nextInt(1);
      int x = r.nextInt(WINDOWS_WIDTH);
      int dir_x = 1;
      if (random_x == 1) {
        x = WINDOWS_HEIGHT;
        dir_x = -1;
      }
      else 
        x = 0;
      asteroids.put(new Integer(asteroidSelection + 1), new Asteroids(new Vector2(x, y), world, sprites[asteroidSelection], random_speed*dir_x, random_speed*dir_y));
    }

    else{
      generateTick += Gdx.graphics.getDeltaTime();
    }

    player.shoot_tick += Gdx.graphics.getDeltaTime();

    for (Shoot shoot: player.shoots)
      shoot.move(shoot);
    player.player_shoot(player);
    batch.begin();
    //game.batch.begin();
    //game.batch.draw(sprite, player.pos.x, player.pos.y);
    player.sprite.draw(batch);
    //player2.sprite.draw(batch);
    for (Integer key: asteroids.keySet())
      asteroids.get(key).sprite.draw(batch);
    for (Shoot shoot: player.shoots){
      shoot.sprite.draw(batch);
    }

    batch.end();
    
    //player.collided(player2);
    //game.batch.end();

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