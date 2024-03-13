package com.kangwang.pinghengche;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.kangwang.word.Constant;

public class Box {
    private float x;
    private float y;
    private float w;
    private float h;
    public Body body;

    public Box(float x,float y,float w, float h){
        this(x,y,w,h,false);
    }

    public Box(float x,float y,float w, float h,boolean falg){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.x,this.y);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(this.w/2, this.h/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0F;
        fixtureDef.friction = 0.5F;
        fixtureDef.restitution = 0.2f;

        if (falg) {
            fixtureDef.filter.maskBits = 0;
            bodyDef.linearDamping = 0.1F;
        }

        this.body = Constant.world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void display(){

    }

    private boolean deletion(){
        Vector2 positon = this.body.getPosition();
        if (positon.y > Constant.width){
            return true;
        }
        return false;
    }

    public boolean contains(float x,float y){
        Array<Fixture> fixtureList = this.body.getFixtureList();
        for (Fixture fixture : fixtureList) {
            Vector2 position = fixture.getBody().getPosition();
            return position.x == x && position.y == y;
        }
        return false;
    }
}
