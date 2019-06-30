package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Shoot extends DynamicGameObject {

    // Velocidade do tiro
    float speed_x = 1000;
    float speed_y = 1000;
    // Vector2 SHOOT_SPEED = new Vector2(1000 * Gdx.graphics.getDeltaTime(), 1000 *
    // Gdx.graphics.getDeltaTime());

    // Posiciona o tiro corretamente de acordo com a rotação do player
    public Shoot(Player player, Vector2 positions, World world, Sprite sprite) {
        super(positions, world, sprite);
        this.rotacao = player.rotacao;
        this.rotaciona(rotacao);

        this.SPEED = new Vector2(1000 * Gdx.graphics.getDeltaTime(), 1000 * Gdx.graphics.getDeltaTime());
    }

    // Movimenta o tiro
    @Override
    public void move() {
        move_xy(this.SPEED);
    }

    @Override
    public boolean collided(DynamicGameObject otherObject) {
        Rectangle r = this.sprite.getBoundingRectangle();
        Rectangle c = otherObject.sprite.getBoundingRectangle();

        // Circle c = new Circle(otherObject.pos, otherObject.spriteWidth/2);
        return r.overlaps(c);
    }

}