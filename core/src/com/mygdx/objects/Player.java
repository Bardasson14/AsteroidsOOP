package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.objects.DynamicGameObject;

public class Player extends DynamicGameObject{

	int SPEED = 200;
	
    // CONSTRUTOR DA CLASSE
    public Player(int x, int y, World world, Sprite sprite){
    
        super(x, y, world, sprite);

    }

    // Pega inputs e move na direção(a ser adaptado para rotações)
    public void move() {
    	

		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			
			move_x(SPEED);
			
			//se o sprite já saiu da tela pela direita, ele aparece do outro lado			
			if(pos.x > WINDOWS_WIDTH)	pos.x = 0 - spriteWidth;

		}
		
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			
			move_x(-1 * SPEED);
			
			//se o sprite já saiu da tela pela esquerda, ele aparece do outro lado
			if((pos.x + spriteWidth) < 0) pos.x = WINDOWS_WIDTH;
			
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			
			move_y(SPEED);
			
			//se o sprite já saiu da tela para cima, aparece em baixo
			if(pos.y > WINDOWS_HEIGHT) pos.y = 0 - spriteHeight ;
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			
			move_y(-1 * SPEED);
			
			//se o sprite já saiu da tela para baixo, aparece em cima
			if((pos.y + spriteHeight) < 0) pos.y = WINDOWS_HEIGHT;
			
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			
			rotacao -= 4;
			rotaciona(rotacao);
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			
			rotacao +=4;
			rotaciona(rotacao);
			
		}
		
		
    }
    
    

}