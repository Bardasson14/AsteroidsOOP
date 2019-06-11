package com.mygdx.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


public class Asteroids extends DynamicGameObject{
   //public Polygon sprite;
    public Vector2 SPEED;

    Shape2D shape = new Circle(this.pos, this.sprite.getWidth()/2);
    public Asteroids(Vector2 posIni, World world, Sprite sprite, float speed_x, float speed_y){
        super(posIni, world, sprite);
        this.SPEED = new Vector2(speed_x, speed_y);
    }

    public void moveAsteroid(Asteroids asteroid){
        asteroid.move_xy(asteroid.SPEED);
    }

    public static float generateAsteroids(World world, float generateTick, float generateCounter, Sprite[] spriteArray, ArrayList<Asteroids> asteroids, int WINDOWS_WIDTH, int WINDOWS_HEIGHT){
        if (generateTick > generateCounter){
            Random r = new Random();
            int asteroidSelection = r.nextInt(2);
            int y = r.nextInt(WINDOWS_HEIGHT);
            int dir_y = 1;
            if (y > (WINDOWS_HEIGHT/2)) dir_y = -1;
            int random_speed_x = 60 + r.nextInt(30);
            int random_speed_y = r.nextInt(80);
            int random_x = r.nextInt(2);
            int dir_x = 1;
            float x = 0;
            //System.out.println(random_x);
            if (random_x == 1) {
              x = WINDOWS_HEIGHT + spriteArray[asteroidSelection].getWidth()*4;
              dir_x = -1;
            }
            else x = 0 - spriteArray[asteroidSelection].getWidth();
            asteroids.add(new Asteroids(new Vector2(x, y), world, spriteArray[asteroidSelection], random_speed_x*dir_x/*Gdx.graphics.getDeltaTime()*/, random_speed_y*dir_y/*Gdx.graphics.getDeltaTime()*/));
            return 0;
          }
      return generateTick;
    }

    @Override
    public boolean collided(DynamicGameObject otherObject) {
        return false;
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