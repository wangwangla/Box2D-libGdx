package com.kangwang.pinghengche;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.kangwang.WorldConstant;
import com.kangwang.word.Constant;

public class Circle {
    private float x ;
    private float y ;
    private float r ;
    public Body body;
    public Circle(float x,float y,float r){
        this.x = x;
        this.y = y;
        this.r = r;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(
                WorldConstant.convert(this.x),
                WorldConstant.convert(this.y));

        CircleShape polygonShape = new CircleShape();
        polygonShape.setRadius(
                WorldConstant.convert(this.r));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0F;
        fixtureDef.friction = 0.5F;
        fixtureDef.restitution = 0.2f;
        this.body = Constant.world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    private boolean deletion(){
        Vector2 positon = this.body.getPosition();
        if (positon.y > Constant.width){
            return true;
        }
        return false;
    }


    public void display(){

    }
}
