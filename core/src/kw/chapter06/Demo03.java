package kw.chapter06;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * mouse   不知道为什么老是不对
 */
public class Demo03 extends Group {
    MouseJointDef def;
    public Demo03(){
        setSize(Constant.width, Constant.hight);
        Utils utils = new Utils();
        Body box = utils.createBox(Constant.width/2 + 10, 0, Constant.width / 2, 1, true);
        box.setUserData("box1");
        Body box1 = utils.createBox(20, 70, 10, 10, false);
        box1.setUserData("box2");
        Body box2 = utils.createBox(20, 20, 10, 10, false);

        def = new MouseJointDef();
        def.bodyA = box;
        def.bodyB = box1;

        Constant.world.createJoint(def);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("===>>>>>>>>>>>");

            }
        });

//        RevoluteJointDef def = new RevoluteJointDef();
//        Vector2 position = box1.getPosition();
//        position.x = position.x - 5;
//        def.initialize(box2,box1,box1.getWorldVector(position));
//        Constant.world.createJoint(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
