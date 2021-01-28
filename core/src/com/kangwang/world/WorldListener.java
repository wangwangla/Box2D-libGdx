package com.kangwang.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.Array;

public class WorldListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Array<Contact> contactList = Constant.world.getContactList();
        System.out.println("====>>>>>");
        Fixture fixtureA = contactList.get(0).getFixtureA();
        Fixture fixtureB = contactList.get(0).getFixtureB();


        Body bodyA = contact.getFixtureA().getBody();
//        if (bodyA instanceof MyBox2D){
//            ((MyBox2D)bodyA).handler();
//        }
//
//        Body bodyB = contact.getFixtureB().getBody();
//        if (bodyB instanceof MyBox2D){
//            ((MyBox2D)bodyB).handler();
//        }
//        Fixture fixtureA = contact.getFixtureA();
//        Fixture fixtureB = contact.getFixtureB();
//
//        short categoryBits = fixtureA.getFilterData().categoryBits;
//        short categoryBits1 = fixtureB.getFilterData().categoryBits;
//
//        if (categoryBits == Constant.BUTT_BIT){
//            if (categoryBits1 == Constant.BLACK_BIT) {
//                fixtureA.getBody().setLinearVelocity(0, -71);
//            }else if (categoryBits1 == Constant.BUTTOM_BIT){
//                fixtureA.getBody().setLinearVelocity(0, 71);
//            }
//        }else if (categoryBits1 == Constant.BUTT_BIT){
//            if (categoryBits == Constant.BLACK_BIT) {
//                fixtureB.getBody().setLinearVelocity(0, -71);
//            }else if (categoryBits == Constant.BUTTOM_BIT){
//                fixtureB.getBody().setLinearVelocity(0, 71);
//            }
//        }
        //得到stage的坐标
        WorldManifold worldManifold = contact.getWorldManifold();
        Vector2 point = worldManifold.getPoints()[0];
        System.out.println(point.x+"-----------"+point.y);




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

        //得到stage的坐标
        WorldManifold worldManifold = contact.getWorldManifold();
        Vector2 point = worldManifold.getPoints()[0];
        System.out.println(point.x+"-----------"+point.y);


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
