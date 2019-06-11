package com.mygdx.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.objects.Asteroids;
import com.mygdx.objects.Player;
import com.mygdx.objects.Shoot;

import com.mygdx.asteroids.AsteroidsGame;

public class MainGameScreen implements Screen {

  
// Declarei uma instância da classe principal
  AsteroidsGame game;

  SpriteBatch batch = new SpriteBatch();
  private float timeSeconds = 0f;
  private float period = 1;
  private float generateCounter = 2f;
  private float generateTick = 0f;
  
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

  //Passando altura e largura da janela para constantes
  int WINDOWS_WIDTH  = Gdx.graphics.getWidth() ;
  int WINDOWS_HEIGHT = Gdx.graphics.getHeight();

  //Criando diferentes tipos de asteróides
  Texture imgPeq = new Texture("imgs/ast30x30.png");
  Texture imgMed = new Texture("imgs/ast65x66.png");
  Texture imgGd  = new Texture("imgs/ast100x101.png");
  Sprite[] spriteArray = {new Sprite(imgPeq),new Sprite(imgMed),new Sprite(imgGd)};
  // Instanciando player
  Player player = new Player(new Vector2((WINDOWS_WIDTH - sprite.getWidth())/2, WINDOWS_HEIGHT/2), world, sprite);
  
  // Lista de asteróides
  ArrayList<Asteroids> asteroids = new ArrayList<Asteroids>();
  //Chave: número de tiros que o asteroide pode sofrer
  //Player player2 = new Player(500, 300, world, sprite);

  // Tamanho da janela

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

    generateTick = Asteroids.generateAsteroids(world, generateTick, generateCounter, spriteArray, asteroids, WINDOWS_WIDTH, WINDOWS_HEIGHT);
    generateTick += Gdx.graphics.getDeltaTime();

    player.shoot_tick += Gdx.graphics.getDeltaTime();

    for (Shoot shoot: player.shoots)
      shoot.move(shoot);
    for (Asteroids asteroid: asteroids)
      asteroid.move_xy(asteroid.SPEED);
    
    player.player_shoot(player);

    Iterator<Shoot> s = player.shoots.iterator();
    Iterator<Asteroids> a = asteroids.iterator();
    
    while (a.hasNext()){
      Asteroids ast = a.next();
      while (s.hasNext()){
        Shoot shoot = s.next();
        if (ast.collided(shoot)){
          s.remove();
          a.remove();
        }
      }
    }


    batch.begin();
    //game.batch.begin();
    //game.batch.draw(sprite, player.pos.x, player.pos.y);
    player.sprite.draw(batch);
    for (Shoot shoot: player.shoots){ 
      shoot.sprite.draw(batch);
    }

    for (Asteroids asteroid: asteroids){
      asteroid.sprite.draw(batch);
    }
    //player2.sprite.draw(batch);

    batch.end();

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