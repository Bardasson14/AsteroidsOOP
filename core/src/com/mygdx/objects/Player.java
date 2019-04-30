package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.objects.DynamicGameObject;

public class Player extends DynamicGameObject{

	int SPEED = 750;
	
	
    // CONSTRUTOR DA CLASSE
    public Player(int x, int y, World world){
    
        super(x, y, world);

    }

    // Pega inputs e move na direção(a ser adaptado para rotações)
    public void move() {
    	

		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			
			System.out.println("foi");
			this.move_x(SPEED);
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			
			System.out.println("AAAA");
			this.move_x(-1 * SPEED);
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			
			this.move_y(SPEED);
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			
			this.move_y(-1 * SPEED);
			
		}
		
		
		
    }
    
    

}