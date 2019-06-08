package com.mygdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Shoot extends DynamicGameObject {
    
    public Shoot(Player player, Sprite sprite) {
        super(player.pos, player.world, sprite);
        this.dir.set(player.dir);
        this.rotaciona(player.rotacao);
    }
    
}