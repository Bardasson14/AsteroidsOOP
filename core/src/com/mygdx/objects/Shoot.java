package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Shoot extends DynamicGameObject {
    
    Vector2 SHOOT_SPEED = new Vector2(100*Gdx.graphics.getDeltaTime(), 100*Gdx.graphics.getDeltaTime());
    
    public Shoot(Vector2 player, World world,Sprite sprite) {
        super(player , world, sprite);
        //this.dir.set(player.dir);
        //this.rotaciona(player.rotacao);
    }


	public void moveShoot(Shoot shoot){
    
        //PROBLEMA
        shoot.pos.x += SHOOT_SPEED.x * Gdx.graphics.getDeltaTime();
        shoot.pos.y += SHOOT_SPEED.y * Gdx.graphics.getDeltaTime();
        //shoot.pos.mulAdd(SHOOT_SPEED, Gdx.graphics.getDeltaTime());
        shoot.sprite.setPosition(shoot.pos.x, shoot.pos.y);	

    }

}