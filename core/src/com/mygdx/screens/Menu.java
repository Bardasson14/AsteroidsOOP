package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.asteroids.AsteroidsGame;
import com.mygdx.screens.MainGameScreen;

public class Menu extends Game implements Screen {
    AsteroidsGame menu;
    Texture exitbutton; 
    Texture playbutton;
    
    int WINDOWS_WIDTH  = Gdx.graphics.getWidth() ;
    int WINDOWS_HEIGHT = Gdx.graphics.getHeight();
    

    public Menu(AsteroidsGame menu){
        this.menu = menu;
        exitbutton = new Texture("imgs/exitbutton.png");
        playbutton = new Texture("imgs/playbutton.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        menu.batch.begin();

        menu.batch.draw(playbutton,(WINDOWS_WIDTH - playbutton.getWidth())/2,(WINDOWS_HEIGHT - playbutton.getHeight())/2);
        menu.batch.draw(exitbutton,(WINDOWS_WIDTH - playbutton.getWidth())/2,(WINDOWS_HEIGHT - playbutton.getHeight())/4);
        if((Gdx.input.getX()>WINDOWS_WIDTH/2)&&(Gdx.input.getX()<WINDOWS_WIDTH/2+playbutton.getWidth())&&(Gdx.input.getY()<WINDOWS_HEIGHT/2)&&(Gdx.input.getY()>WINDOWS_HEIGHT/2-playbutton.getHeight())){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                menu.setScreen(new MainGameScreen(menu));
            }
        }
        if((Gdx.input.getX()>WINDOWS_WIDTH/2)&&(Gdx.input.getX()<WINDOWS_WIDTH/2+exitbutton.getWidth())&&(Gdx.input.getY()<WINDOWS_HEIGHT-WINDOWS_HEIGHT/4)&&(Gdx.input.getY()>-WINDOWS_HEIGHT/4-exitbutton.getHeight()+WINDOWS_HEIGHT)){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                Gdx.app.exit();
            }
        }
        // System.out.println(WINDOWS_HEIGHT/2);
        // System.out.println(WINDOWS_HEIGHT/2+playbutton.getHeight());
        // System.out.println(WINDOWS_WIDTH/2);

        menu.batch.end();
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

    @Override
    public void create() {

    }

    
    
}