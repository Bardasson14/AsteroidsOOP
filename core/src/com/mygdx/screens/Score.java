package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.asteroids.AsteroidsGame;

public class Score implements Screen {

    AsteroidsGame score;
    int score_number;
    BitmapFont score_write = new BitmapFont();
    SpriteBatch batch = new SpriteBatch();

    int WINDOWS_WIDTH = Gdx.graphics.getWidth();
    int WINDOWS_HEIGHT = Gdx.graphics.getHeight();

    // Construtor da classe
    public Score(AsteroidsGame score, int score_number) {
        this.score = score;
        this.score_number = score_number;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            score.setScreen(new Menu(score));
        }
        
        batch.begin();        
        
        score_write.draw(batch, "GAME OVER", WINDOWS_WIDTH / 2-50,WINDOWS_HEIGHT/2+30);   
        score_write.draw(batch, "FINAL SCORE: " + Integer.toString(this.score_number), WINDOWS_WIDTH / 2-58,WINDOWS_HEIGHT/2);
        score_write.draw(batch, "PRESS ESC TO GO BACK TO MENU", WINDOWS_WIDTH / 2-120,WINDOWS_HEIGHT/2-30);      
        
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

    }
    
}