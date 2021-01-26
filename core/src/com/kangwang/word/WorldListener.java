package com.kangwang.word;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        short categoryBits = fixtureA.getFilterData().categoryBits;
        short categoryBits1 = fixtureB.getFilterData().categoryBits;

        if (categoryBits == Constant.BUTT_BIT){
            if (categoryBits1 == Constant.BLACK_BIT) {
                fixtureA.getBody().setLinearVelocity(0, -71);
            }else if (categoryBits1 == Constant.BUTTOM_BIT){
                fixtureA.getBody().setLinearVelocity(0, 71);
            }
        }else if (categoryBits1 == Constant.BUTT_BIT){
            if (categoryBits == Constant.BLACK_BIT) {
                fixtureB.getBody().setLinearVelocity(0, -71);
            }else if (categoryBits == Constant.BUTTOM_BIT){
                fixtureB.getBody().setLinearVelocity(0, 71);
            }
        }



//        Fixture fixtureA = contact.getFixtureA();
//        Fixture fixtureB = contact.getFixtureB();
//        if (fixtureA.getFilterData().categoryBits == Constant.PILL_BIT ||
//                fixtureB.getFilterData().categoryBits == Constant.PILL_BIT) {
//            // pill
//            if (fixtureA.getFilterData().categoryBits == Constant.PLAYER_BIT) {
//            }else{
//            }
//        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        System.out.println("------start-------");
        System.out.println(oldManifold.getLocalNormal());
        System.out.println(oldManifold.getLocalPoint());
        System.out.println(oldManifold.getPointCount());
        System.out.println("-------end-------");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
