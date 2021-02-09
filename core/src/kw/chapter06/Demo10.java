package kw.chapter06;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 *  滑轮
 *
 *   这个写完了，有点蒙蔽
 */
public class Demo10 extends Group {
    private Utils utils;
    private Body box;
    public Demo10(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        Body box1 = utils.createBox(33, 50, 1, 1, true);
        utils.createBox(33, 30, 1, 1, true);
        Body box2 = utils.createBox(10, 10, 1, 2, false);
//        MotorJointDef def = new MotorJointDef();
//        def.initialize(box1,box2);
//        Constant.world.createJoint(def);

        RopeJointDef def = new RopeJointDef();
        def.bodyA = box1;
        def.bodyB = box2;
        def.localAnchorA.set(0,0);
        def.localAnchorB.set(0,10);
        def.maxLength = 20;
        Constant.world.createJoint(def);

    }

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 20, 2, 2, false);
        box.setUserData("box");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}