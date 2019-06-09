package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// Esta classe é para qualquer objeto com movimentação
public class DynamicGameObject{

	BodyDef bodydef = new BodyDef();

	Body body;
	
	
	
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
    
    //Direction
    public Vector2 dir = new Vector2();

	//Constantes de tamanho da tela
	int WINDOWS_WIDTH = Gdx.graphics.getWidth();
	int WINDOWS_HEIGHT = Gdx.graphics.getHeight();
	
	
    
    //Construtor da classe
    public DynamicGameObject(int x, int y, World world, Sprite sprite){

        pos = new Vector2(x, y);
        sprite.setPosition(x, y);
        
        this.world = world;
        this.sprite = new Sprite(sprite);
        
        this.spriteWidth = this.sprite.getWidth();
        this.spriteHeight = this.sprite.getHeight();
        
    	bodydef.type = BodyType.DynamicBody;
    	bodydef.position.set(x, y);
    	
    	dir.set(MathUtils.sin(this.rotacao), MathUtils.cos(this.rotacao));
    	
    	body = world.createBody(bodydef);
    }
    
    //Bota o objeto na posição indicada
    public void set_position(int x, int y){

        this.pos.x = x;
        this.pos.y = y;

    }

    //Move o objeto nos dois eixos
    public void move_xy(Vector2 speed){

        this.pos.mulAdd(speed, Gdx.graphics.getDeltaTime());
        sprite.setPosition(this.pos.x, this.pos.y);

    }
    
    
    public void rotaciona(float novaRotacao) {
    	
    	if(novaRotacao > 360) this.rotacao -= 360;
    	if(novaRotacao < 0)	this.rotacao += 360;
    	
    	sprite.setRotation(this.rotacao);
    	
    	float radianos = (this.rotacao * 2 * MathUtils.PI)/360.f;
    	
    	dir.set(-1 * MathUtils.sin(radianos), MathUtils.cos(radianos));
    	
    }
    
    
    public void accelerate(Vector2 speed, Vector2 acceleration, int MAX_SPEED) {
    	
    	speed.x += this.dir.x * acceleration.x;
    	speed.y += this.dir.y * acceleration.y;

    }
    
    //A nave perde velocidade quando não está acelerando
    public void looseSpeed(Vector2 speed, Vector2 acceleration) {
    	
    	
    	
    	//Vector2 copy = this.dir.scl(acceleration).cpy();
    	
    	//copy.scl(-1);
    	
    	//speed.add(copy);
    	
    	//speed.add(v)
    	
    	if(speed.x > acceleration.x)	speed.x += -1 * this.dir.x * acceleration.x;
    	else if(-1 * speed.x > acceleration.x)	speed.x += -1 * this.dir.x * acceleration.x;
    	if(speed.y > acceleration.y)	speed.y += -1 * this.dir.y * acceleration.y;
    	else if(-1 *speed.y > acceleration.y)	speed.y += -1 * this.dir.y * acceleration.y;
    	
    }
    
    
    public boolean collided(DynamicGameObject otherObject) {
    	
    	Rectangle rectangle1 = this.sprite.getBoundingRectangle();
    	Rectangle rectangle2 = otherObject.sprite.getBoundingRectangle();

    	return rectangle1.overlaps(rectangle2);
    	
    }

}