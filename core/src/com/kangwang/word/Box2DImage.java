package com.kangwang.word;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Box2DImage extends Image {
    private Body body;
    public Box2DImage(Body body){
        this.body = body;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(body.getAngle());
        if (body.getPosition().x!=getX() || body.getPosition().y != getY()) {
            setPosition(body.getPosition().x, body.getPosition().y);
        }
    }
}
