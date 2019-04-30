package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

// Esta classe é para qualquer objeto com movimentação
public class DynamicGameObject{

    //Vector2 = classe de manipulação de vetores 2x2
    public Vector2 pos;     //posição

    //Objetos Box2D precisam estar conectados a um mundo
    public World world;

    
    //Construtor da classe
    public DynamicGameObject(int x, int y, World world){

        pos = new Vector2(x, y);
        this.world = world;

    }

    //Bota o objeto na posição indicada
    public void set_position(int x, int y){

        this.pos.x = x;
        this.pos.y = y;

    }

    //Move o objeto no eixo X
    public void move_x(int speed){

        this.pos.x += speed * Gdx.graphics.getDeltaTime();

    }

    //Move o objeto no eixo y
    public void move_y(int speed){

        this.pos.y += speed * Gdx.graphics.getDeltaTime();

    }

}