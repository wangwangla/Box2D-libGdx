package com.doc.demo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

/**
 * 旋转
 *
 * 保持一个速度转动
 */
public class Demo08 extends Group {
    public Demo08(){
        init();
    }

    Body round;
    private void init() {
        round = createRadis(new Vector2(10,10),10, BodyDef.BodyType.StaticBody);
//        createRadis(new Vector2(30,30),10, BodyDef.BodyType.StaticBody);
        createRadis(new Vector2(10,70),2, BodyDef.BodyType.DynamicBody);
        Constant.world.setContactListener(new ContactListener());
    }

    public Body createRadis(Vector2 position, int radis, BodyDef.BodyType type){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(position.x,position.y);
        Body body = Constant.world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radis);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        Shape shape = round.getFixtureList().get(0).getShape();
        round.getFixtureList().get(0).getShape().setRadius(shape.getRadius() + 0.3F);
    }

    class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
        @Override
        public void beginContact(Contact contact) {
            System.out.println("===》start");
        }

        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }
    }
}
