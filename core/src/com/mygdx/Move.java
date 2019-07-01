package com.mygdx;

import java.util.ArrayList;

import com.mygdx.objects.Asteroids;
import com.mygdx.objects.DynamicGameObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Move;

//import com.badlogic.gdx.Game;

public class Move implements Runnable {

    ArrayList<Asteroids> asteroides = new ArrayList<Asteroids>();
    ArrayList<DynamicGameObject> objetos = new ArrayList<DynamicGameObject>();
    World world;
    float generateTick;
    float generateCounter;
    Sprite[] spriteArray;
    int WINDOWS_WIDTH;
    int WINDOWS_HEIGHT;

    public Move(ArrayList<Asteroids> asteroides, ArrayList<DynamicGameObject> objetos, World world, float generateTick, float generateCounter, Sprite[] spriteArray,
            int WINDOWS_WIDTH, int WINDOWS_HEIGHT) {

        this.asteroides = asteroides;
        this.objetos = objetos;
        this.world = world;
        this.generateCounter = generateCounter;
        this.generateTick = generateTick;
        this.spriteArray = spriteArray;
        this.WINDOWS_HEIGHT = WINDOWS_HEIGHT;
        this.WINDOWS_WIDTH = WINDOWS_WIDTH;


    }

    @Override
    public void run() {

        generateTick = Asteroids.generateAsteroids(world, generateTick, generateCounter, spriteArray, asteroides, 1200,
                600);

        // Movimenta os tiros e o player
        for (DynamicGameObject objeto : objetos)
            objeto.move();

        // Movimenta os asteroids
        for (Asteroids asteroid : asteroides)
          asteroid.move();

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");

      }

}