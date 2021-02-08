package kw.chapter05;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo02 extends Group {
    public Demo02(){
        Utils utils = new Utils();
        Body box = utils.createBox(Constant.width/2 + 10, 10, Constant.width / 2, 1, true);
        box.setUserData("box1");
        Body box1 = utils.createBox(20, 40, 10, 10, false);
        box1.setUserData("box2");
        Constant.world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                System.out.println("begin");


            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                contact.setEnabled(false);
//                System.out.println("pre");
//                System.out.println();
            }


            /**
             *
             * @param contact
             * @param impulse
             */
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
//                System.out.println("post");
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
