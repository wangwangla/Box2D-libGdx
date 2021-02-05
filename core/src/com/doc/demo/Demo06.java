package com.doc.demo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

/**
 * 旋转
 *
 * 保持一个速度转动
 */
public class Demo06 extends Group {
    public Demo06(){
        init();
    }
    Body radis;
    Body round;
    private boolean flag = false;
    private void init() {
        radis = createRadis(new Vector2(13, 90), 2, BodyDef.BodyType.DynamicBody);
        radis.setUserData("QIU");
        round = createRadis(new Vector2(10,70),10, BodyDef.BodyType.StaticBody);
        Constant.world.setContactListener(new MyContactListener());

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

    private class MyContactListener implements ContactListener {
        @Override
        public void beginContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();
            changeStatus(fixtureA,fixtureB);
            changeStatus(fixtureB,fixtureA);
        }

        public boolean changeStatus(Fixture fixture,Fixture fixture1){
            Object userData = fixture.getBody().getUserData();
            if (userData != null){
                if (userData instanceof String) {
                    if (((String) userData).equals("QIU")) {
                        flag = true;
                        return true;
                    }
                }
            }
            return false;
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
    DistanceJointDef def;
    @Override
    public void act(float delta) {
        super.act(delta);
        if (flag){
            if (def == null) {
                def = new DistanceJointDef();
                def.initialize(round, radis,
                        round.getWorldCenter(),
                        radis.getWorldCenter());
                Constant.world.createJoint(def);
            }
            radis.applyTorque(100,false);
            radis.applyForce(new Vector2(0,-radis.getMass()*Constant.world.getGravity().y),radis.getWorldCenter(),false);
        }
    }
}
