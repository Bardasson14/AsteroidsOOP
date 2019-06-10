package com.mygdx.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Asteroids extends DynamicGameObject{
   //public Polygon sprite;
    public Vector2 SPEED;
    
    public Asteroids(Vector2 posIni, World world, Sprite sprite, float speed_x, float speed_y){
        super(posIni, world, sprite);
        this.SPEED = new Vector2(speed_x, speed_y);
    }

    public void moveAsteroid(Asteroids asteroid){
        asteroid.move_xy(asteroid.SPEED);
    }

    

    /*
    public static int generateAsteroids(float generateCounter, float generateTick){
        if (generateTick>generateCounter){
            return 1;
        }
        return 0;
    }
    */
}