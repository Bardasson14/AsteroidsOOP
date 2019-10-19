package com.mygdx.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.objects.Asteroids;
import com.mygdx.objects.DynamicGameObject;
import com.mygdx.objects.Player;
import com.mygdx.objects.Shoot;
import com.mygdx.Desenha;
import com.mygdx.asteroids.AsteroidsGame;

public class MainGameScreen extends Game implements Screen {

  // Declarei uma instância da classe principal
  AsteroidsGame game;

  SpriteBatch batch = new SpriteBatch();
  private float timeSeconds = 0f;
  private float period = 1;
  private float generateCounter = 4f;
  private float generateTick = 0f;
  int killCounter = 0;
  /*
   * Criei uma instância da classe World, esta é necessária para o uso de Box2D
   * Param: Vector2(a, b): a = gravidade no eixo x b = gravidade no eixo y
   * Boolean: indica se o programador quer que objetos "durmam", isto reduz uso da
   * cpu
   */

  World world = new World(new Vector2(0, 0), true);
  // Criando imagens
  Texture img = new Texture("imgs/SpaceShip24x24.png");
  Sprite sprite = new Sprite(img);

  // Passando altura e largura da janela para constantes
  int WINDOWS_WIDTH = Gdx.graphics.getWidth();
  int WINDOWS_HEIGHT = Gdx.graphics.getHeight();

  // Criando diferentes tipos de asteróides
  Texture imgPeq = new Texture("imgs/ast30x30.png");
  public Texture imgMed = new Texture("imgs/ast65x66.png");
  Texture imgGd = new Texture("imgs/ast100x101.png");
  Sprite[] spriteArray = { new Sprite(imgPeq), new Sprite(imgMed), new Sprite(imgGd) };
  // Instanciando player
  Player player = new Player(new Vector2((WINDOWS_WIDTH - sprite.getWidth()) / 2, WINDOWS_HEIGHT / 2), world, sprite);

  // Vidas
  int life;

  // Pontuacao
  BitmapFont score_number = new BitmapFont();
  int score;
  // Lista de asteróides
  ArrayList<Asteroids> asteroids = new ArrayList<Asteroids>();
  ArrayList<Asteroids> ast = new ArrayList<Asteroids>();
  ArrayList<DynamicGameObject> objetos = new ArrayList<DynamicGameObject>();
  // Chave: número de tiros que o asteroide pode sofrer
  // Player player2 = new Player(500, 300, world, sprite);

  // Tamanho da janela
  // Desenha thread = new Desenha(asteroids, objetos);

  // CONSTRUTOR DA CLASSE
  public MainGameScreen(AsteroidsGame game, int life, int score) {

    this.game = game;
    this.life = life;
    this.score = score;

    objetos.add(player);
    // thread.run();
    // thread.start();

  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Checa se o ESC foi pressinado e volta para o menu
    if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
      game.setScreen(new Menu(game));
    }

    // Spawn de asteroids
    Thread t = new Thread() {
      public void run() {
        generateTick = Asteroids.generateAsteroids(world, generateTick, generateCounter, spriteArray, asteroids, 1200,
            600);
      }
    };

    t.start();
    try {
      t.join();
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    generateTick += Gdx.graphics.getDeltaTime();

    // Delay do tiro do jogador
    player.shoot_tick += Gdx.graphics.getDeltaTime();

    // Verificação para ver se o jogador atirou
    Shoot atirou = player.player_shoot(player);

    if (atirou != null) {
      objetos.add(atirou);
      System.out.println("FOI");
    }

    // Movimenta os tiros e o player
    for (DynamicGameObject objeto : objetos)
      objeto.move();

    // Movimenta os asteroids
    for (Asteroids asteroid : asteroids)
      asteroid.move();

    Iterator<Asteroids> a = asteroids.iterator();
    // System.out.println(this.score);
    while (a.hasNext()) {
      Asteroids asteroid = a.next();
      // Iterator<Shoot> s = player.shoots.iterator();
      Iterator<DynamicGameObject> obj = objetos.iterator();
      
      Player player = (Player) obj.next();
      if ((player.collided(asteroid)) && (this.life == 0)) {
        try {
          History.salvaJogo(this.score, game, new Menu(game), this.killCounter);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "O arquivo do save não foi encontrado. O jogo não será salvo.");
        }
        game.setScreen(new Score(game, this.score));

      }

      else if (player.collided(asteroid)) {
        game.setScreen(new MainGameScreen(game, this.life - 1, this.score));
      }

      if (asteroid.pos.x < (-asteroid.spriteWidth * 2) || asteroid.pos.x > (WINDOWS_WIDTH + asteroid.spriteWidth * 2)
          || (asteroid.pos.y < (-asteroid.spriteHeight * 2))
          || asteroid.pos.y > (WINDOWS_HEIGHT + asteroid.spriteHeight * 2)) {
        a.remove();
        continue;
      }
      while (obj.hasNext()) {
        Shoot shoot = (Shoot) obj.next();
        if (this.score >= 0x13371337){
          JOptionPane.showMessageDialog(null, "CTF{G4M3M45TER13371337}");
          this.life = 0;
        }
        if (shoot.collided(asteroid)) {
          
          if (asteroid.spriteHeight == imgPeq.getHeight()) {
            killCounter++;
            this.score += 160;
          } else if (asteroid.spriteHeight == imgMed.getHeight()) {
            killCounter++;
            Asteroids aux_ast1 = new Asteroids(new Vector2(asteroid.pos.x, asteroid.pos.y), world, spriteArray[0],
                asteroid.SPEED.x * 1.2f, asteroid.SPEED.y * 1.2f);
            ast.add(aux_ast1);
            Asteroids aux_ast2 = new Asteroids(new Vector2(asteroid.pos.x, asteroid.pos.y), world, spriteArray[0],
                asteroid.SPEED.x * 1.2f, asteroid.SPEED.y * -1 * 1.2f);
            ast.add(aux_ast2);
            this.score += 80;
          } else if (asteroid.spriteHeight == imgGd.getHeight()) {
            killCounter++;
            Asteroids aux_ast1 = new Asteroids(new Vector2(asteroid.pos.x, asteroid.pos.y), world, spriteArray[1],
                asteroid.SPEED.x * 1.2f, asteroid.SPEED.y * 1.2f);
            ast.add(aux_ast1);
            Asteroids aux_ast2 = new Asteroids(new Vector2(asteroid.pos.x, asteroid.pos.y), world, spriteArray[1],
                asteroid.SPEED.x * 1.2f, asteroid.SPEED.y * -1 * 1.2f);
            aux_ast2.dir.y = -1 * aux_ast2.dir.y;
            ast.add(aux_ast2);
            this.score += 40;
          }
          obj.remove();
          a.remove();
          continue;
        }
      }
      
    }
    //0x13371337

    for (Asteroids asteroide : ast) {
      asteroids.add(asteroide);
    }

    ast.clear();

    batch.begin();

    // Desenha o jogador
    // player.sprite.draw(batch);

    score_number.draw(batch, "SCORE: " + Integer.toString(this.score), WINDOWS_WIDTH / 6,
        WINDOWS_HEIGHT - WINDOWS_HEIGHT / 20);

    for (int i = 0; i <= this.life; i++) {
      sprite.setPosition(WINDOWS_WIDTH / 6 + sprite.getWidth() * i, WINDOWS_HEIGHT - WINDOWS_HEIGHT / 7);
      sprite.draw(batch);
    }
    // player2.sprite.draw(batch);

    // Desenha os asteroids
    for (Asteroids asteroid : asteroids)
      asteroid.sprite.draw(batch);

    // Desenha os tiros do jogador
    for (DynamicGameObject objeto : objetos) {

      objeto.sprite.draw(batch);

    }

    for (Asteroids asteroid : asteroids) {
      asteroid.sprite.draw(batch);
      asteroid.rotacao -= 1;
      asteroid.rotaciona(asteroid.rotacao);
    }

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

  @Override
  public void create() {

  }

}