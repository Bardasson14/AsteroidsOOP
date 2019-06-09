package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Shoot extends DynamicGameObject {

    float speed_x = 200;
    float speed_y = 200;
    Vector2 SHOOT_SPEED = new Vector2(1000*Gdx.graphics.getDeltaTime(), 100*Gdx.graphics.getDeltaTime());

    

    public Shoot(Player player,Vector2 positions, World world,Sprite sprite) {
        super(positions , world, sprite);
        this.rotacao = player.rotacao;
        this.rotaciona(rotacao);
    }


	public void moveShoot(Shoot shoot){
        

        //PROBLEMA
        shoot.pos.x += SHOOT_SPEED.x * Gdx.graphics.getDeltaTime();
        shoot.pos.y += SHOOT_SPEED.y * Gdx.graphics.getDeltaTime();
        //shoot.pos.mulAdd(SHOOT_SPEED, Gdx.graphics.getDeltaTime());
        shoot.sprite.setPosition(shoot.pos.x, shoot.pos.y);	

    }

}