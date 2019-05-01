package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

// Esta classe é para qualquer objeto com movimentação
public class DynamicGameObject{

    //Vector2 = classe de manipulação de vetores 2x2
    public Vector2 pos;     //posição
    
    //Rotação em graus
    public float rotacao = 0;

    //Objetos Box2D precisam estar conectados a um mundo
    public World world;
    
    //Sprite do objeto
    public Sprite sprite;
    
    //Sprite width and height
    public float spriteWidth;
    public float spriteHeight;

	//Constantes de tamanho da tela
	int WINDOWS_WIDTH = Gdx.graphics.getWidth();
	int WINDOWS_HEIGHT = Gdx.graphics.getHeight();
	
	
    
    //Construtor da classe
    public DynamicGameObject(int x, int y, World world, Sprite sprite){

        pos = new Vector2(x, y);
        sprite.setPosition(x, y);
        
        this.world = world;
        this.sprite = sprite;
        
        this.spriteWidth = this.sprite.getWidth();
        this.spriteHeight = this.sprite.getHeight();

    }

    //Bota o objeto na posição indicada
    public void set_position(int x, int y){

        this.pos.x = x;
        this.pos.y = y;

    }

    //Move o objeto no eixo X
    public void move_x(int speed){

        this.pos.x += speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(this.pos.x, this.pos.y);

    }

    //Move o objeto no eixo y
    public void move_y(int speed){

        this.pos.y += speed * Gdx.graphics.getDeltaTime();
        sprite.setPosition(this.pos.x, this.pos.y);

    }
    
    public void rotaciona(float rotacao) {
    	
    	sprite.setRotation(rotacao);
    	
    }

}