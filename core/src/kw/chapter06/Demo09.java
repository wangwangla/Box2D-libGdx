package kw.chapter06;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 *  滑轮
 *
 *   这个写完了，有点蒙蔽
 */
public class Demo09 extends Group {
    private Utils utils;
    private Body box;
    public Demo09(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        Body box1 = utils.createBox(3, 50, 1, 1, false);
        Body box2 = utils.createBox(10, 50, 1, 2, false);
        FrictionJointDef def = new FrictionJointDef();
        def.bodyA = box1;
        def.bodyB = box2;
        def.localAnchorA.set(0,0);
        def.localAnchorB.set(0,0);
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