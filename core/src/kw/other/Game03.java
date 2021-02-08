package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Game03 extends Group {
    private Array<Body> gameArray = new Array<>();
    private Utils utils;
    private Body box;
    private boolean down ;
    public Game03(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        utils.createBox(Constant.width/2,60,30,2,true);
        createBody();

        Constant.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                Object userData = contact.getFixtureA().getBody().getUserData();
                Object userData1 = contact.getFixtureB().getBody().getUserData();
                if (userData!=null) {
                    if (userData instanceof String) {
                        if (userData.equals("box")) {
                            if (contact.getFixtureA().getBody().getLinearVelocity().y <0) {
                                down = true;
                            }
                        }
                    }
                }
                if (userData1!=null) {
                    if (userData1 instanceof String) {
                        if (userData1.equals("box")) {
                            if (contact.getFixtureB().getBody().getLinearVelocity().y <0) {
                                down = true;
                            }
                        }
                    }
                }
                if (!down){
                    contact.setEnabled(false);
                }

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 50, 2, 2, false);
        down = false;
        box.setLinearVelocity(vector2);
        box.setUserData("box");
    }

    private Vector2 vector2 = new Vector2(0,40);

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
