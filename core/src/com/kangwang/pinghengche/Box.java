package com.kangwang.pinghengche;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kangwang.world.Constant;

public class Box {
    private float x;
    private float y;
    private float w;
    private float h;
    private Body body;
    public Box(float x,float y,float w, float h){
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
        fixtureDef.density = 100;
        fixtureDef.density = 1.0F;
        fixtureDef.friction = 0.5F;
        fixtureDef.restitution = 0.2f;
        fixtureDef.filter.maskBits = 0;

        Filter filter = fixtureDef.filter;



//        fixtureDef.linearDamping = 0.1;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        this.body = Constant.world.createBody(bodyDef);
    }

    public void display(){

    }
    /**
     *
     *     display() {
     *
     *         let pos = scaleToPixels(this.body.GetPosition());
     *         let a = this.body.GetAngleRadians();
     *
     *         rectMode(CENTER);
     *         push();
     *         translate(pos.x, pos.y);
     *         rotate(a);
     *         fill(127);
     *         stroke(200);
     *         strokeWeight(2);
     *         rect(0, 0, this.w, this.h);
     *         pop();
     *
     *     }
     *
     *     deletion() {
     *
     *         let pos = scaleToPixels(this.body.GetPosition());
     *
     *         if(pos.y > window.innerHeight) {
     *             world.DestroyBody(this.body);
     *             return true;
     *         }
     *
     *         return false;
     *
     *     }
     *
     *     contains(x, y) {
     *         let worldPoint = scaleToWorld(x, y);
     *         let f = this.body.GetFixtureList();
     *         let inside = f.TestPoint(worldPoint);
     *         return inside;
     *     }
     */
}
