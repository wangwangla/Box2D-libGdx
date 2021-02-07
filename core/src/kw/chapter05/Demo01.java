package kw.chapter05;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo01 extends Group {
    public Demo01(){
        Utils utils = new Utils();
        Body box = utils.createBox(Constant.width/2 + 10, 10, Constant.width / 2, 1, true);
        box.setUserData("box1");
        Body box1 = utils.createBox(20, 40, 10, 10, false);
        box1.setUserData("box2");
        Constant.world.setContactListener(new ContactListener() {

            /**
             *  撞击  弹起  撞击  弹起   撞击
             *
             *  三次 begin     两次  end
             */
            @Override
            public void beginContact(Contact contact) {
                System.out.println("begin");
//                System.out.println("contact.getFixtureA()"+contact.getFixtureA().getBody().getUserData());
//                System.out.println("contact.getFixtureB()"+contact.getFixtureB().getBody().getUserData());
                //
                Vector2[] points = contact.getWorldManifold().getPoints();
                System.out.println(points[0].x+"======"+points[0].y);

                //是不是发生重叠   挨住
                System.out.println(contact.isTouching());

                String userData = (String) contact.getFixtureB().getBody().getUserData();


            }

            @Override
            public void endContact(Contact contact) {
//                System.out.println("end");
//                System.out.println();
            }

            //begin开始之后就执行   一直到不活跃
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
