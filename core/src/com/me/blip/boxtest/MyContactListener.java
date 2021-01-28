package com.me.blip.boxtest;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener
{
    public boolean touchingGround = false;
    public int footContacts = 0;
    private Buoyancy2D buoyancy2D;

    public MyContactListener(Buoyancy2D buoyancy2D)
    {
        this.buoyancy2D = buoyancy2D;
    }

    @Override
    public void beginContact(Contact contact)
    {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //FLUID
        if((fa.getBody().getType() == BodyDef.BodyType.DynamicBody) && fb.getUserData().equals("water"))
        {
            buoyancy2D.addObjectFluidPair(fa,fb);
        }
        else if((fb.getBody().getType() == BodyDef.BodyType.DynamicBody) && fa.getUserData().equals("water"))
        {
            buoyancy2D.addObjectFluidPair(fb,fa);
        }

        //TOUCHING GROUND
        if((fa.getUserData().equals("platform") || fa.getUserData().equals("crate")) && fb.getUserData().equals("foot"))
        {
            touchingGround = (++footContacts > 0);
        }
        else if(fa.getUserData().equals("foot") && (fb.getUserData().equals("platform") || fb.getUserData().equals("crate")))
        {
            touchingGround = (++footContacts > 0);
        }
    }

    @Override
    public void endContact(Contact contact)
    {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //FLUID - NOT PERFECT - LEAVING ONE BODY OF WATER MEANS LEAVING ALL BODIES OF WATER ... unlikely to matter as multiple water bodies won't touch!
        if((fa.getBody().getType() == BodyDef.BodyType.DynamicBody) && fb.getUserData().equals("water"))
        {
            buoyancy2D.removeObjectFluidPair(fa);
        }
        else if((fb.getBody().getType() == BodyDef.BodyType.DynamicBody) && fa.getUserData().equals("water"))
        {
            buoyancy2D.removeObjectFluidPair(fb);
        }

        //Class called user data which has a boolean isFloor?
        //Give each object a class (as usual) which contains user data. Make all objects extends abstract class GameObject
        if((fa.getUserData().equals("platform") || fa.getUserData().equals("crate")) && fb.getUserData().equals("foot"))
        {
            touchingGround = (--footContacts > 0);
        }
        else if(fa.getUserData().equals("foot") && (fb.getUserData().equals("platform") || fb.getUserData().equals("crate")))
        {
            touchingGround = (--footContacts > 0);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
