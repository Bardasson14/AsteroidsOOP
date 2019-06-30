package com.mygdx;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.objects.Asteroids;
import com.mygdx.objects.DynamicGameObject;

//import com.badlogic.gdx.Game;

public class Desenha implements Runnable {

    ArrayList<Asteroids> asteroides = new ArrayList<Asteroids>();
    ArrayList<DynamicGameObject> objetos = new ArrayList<DynamicGameObject>();
    SpriteBatch batch = new SpriteBatch();

    public Desenha(ArrayList<Asteroids> asteroides, ArrayList<DynamicGameObject> objetos) {

        this.asteroides = asteroides;
        this.objetos = objetos;

    }

    @Override
    public void run() {

        batch.begin();
        // Desenha os asteroids
        for (Asteroids asteroid : asteroides)
            asteroid.sprite.draw(batch);

        // Desenha os tiros do jogador
        for (DynamicGameObject objeto : objetos) {

            objeto.sprite.draw(batch);

        }

        for (Asteroids asteroid : asteroides) {
            asteroid.sprite.draw(batch);
            asteroid.rotacao -= 1;
            asteroid.rotaciona(asteroid.rotacao);
        }

        batch.end();

    }

}