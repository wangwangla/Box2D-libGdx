package kw.chapter06;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo05 extends Group {
    private Utils utils;
    private Body box;
    public Demo05(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        Body box = utils.createBox(3, 0, 2, 30, true);
        utils.createBox(Constant.width  - 3,0,2,30,true);
        Body box1 = utils.createBox(15, 32.1F, 2, 2, false);

        RevoluteJointDef def = new RevoluteJointDef();
        def.initialize(box,box1,new Vector2(15,22.1F));
        def.enableMotor = true;
        def.maxMotorTorque = 1000;

        Constant.world.createJoint(def);
        box1.setLinearVelocity(new Vector2(5,0));
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