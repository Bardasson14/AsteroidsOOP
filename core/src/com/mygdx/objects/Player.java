package com.mygdx.objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.objects.DynamicGameObject;

public class Player extends DynamicGameObject{

	Vector2 Speed = new Vector2();
	float MAX_SPEED = 240;
	public float shoot_delay = 0.5f;
    public float shoot_tick = shoot_delay;
	Vector2 ACCELERATION = new Vector2(250, 250);
	
	public List<Shoot> shoots = new ArrayList<Shoot>();
    // CONSTRUTOR DA CLASSE
	public Player(Vector2 pos, World world, Sprite sprite){
		super(pos, world, sprite);
	}

    // Pega inputs e move na direção(a ser adaptado para rotações)
	public void move() {
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			this.accelerate(Speed, ACCELERATION, MAX_SPEED);
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			this.rotacao -= 4;
			this.rotaciona(rotacao);
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			this.rotacao += 4;
			this.rotaciona(rotacao);
		}

		move_xy(Speed);
		looseSpeed(Speed, ACCELERATION);
		//se o sprite já saiu da tela pela direita, ele aparece do outro lado			
		if(this.pos.x > WINDOWS_WIDTH)	this.pos.x = 0 - spriteWidth;

		//se o sprite já saiu da tela pela esquerda, ele aparece do outro lado
		if((this.pos.x + spriteWidth) < 0) this.pos.x = WINDOWS_WIDTH;
				
		//se o sprite já saiu da tela para cima, aparece em baixo
		if(this.pos.y > WINDOWS_HEIGHT) this.pos.y = 0 - spriteHeight ;

		//se o sprite já saiu da tela para baixo, aparece em cima
		if((this.pos.y + spriteHeight) < 0) this.pos.y = WINDOWS_HEIGHT;
			
	}
	
	public void player_shoot(Player player){
		Texture img2 = new Texture("imgs/shoot.png");
		Sprite shoot_sprite = new Sprite(img2);
		if (Gdx.input.isKeyPressed(Keys.K) && player.shoot_tick > player.shoot_delay){
			int dir_x=1, dir_y=1;
			float p1 = player.pos.x;
			float p2 = player.pos.y;
			Vector2 vel = new Vector2(p1+player.spriteWidth/2,p2+player.spriteHeight/2);
			Shoot shoot = new Shoot(player,vel, world, shoot_sprite);
			//if (shoot.rotacao >= 0 && shoot.rotacao<=180) dir_x = -1;
            //if (shoot.rotacao >= 90 && shoot.rotacao<=270) dir_y = -1;
            //System.out.println("dir_x = ", dir;
            shoot.speed_x *= dir_x;
			shoot.speed_y *= dir_y;
			float radianos = (shoot.rotacao * 2 * MathUtils.PI)/360.f;
			shoot.SHOOT_SPEED = new Vector2(-shoot.speed_x*MathUtils.sin(radianos), -shoot.speed_y*MathUtils.cos(radianos)*-1);
			System.out.println(shoot.speed_x*MathUtils.sin(radianos));
			
			player.shoots.add(shoot);	
			player.shoot_tick = 0f;
		}
		
	}
    
}