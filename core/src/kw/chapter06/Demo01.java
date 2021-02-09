package kw.chapter06;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;
/*
        JointDef def = new MotorJointDef();
        def.bodyA = box;
        def.bodyB = box1;
        def.collideConnected = true; // true / false  false:不发生碰撞，可以相互穿透
        def.type = null;  //无需关心   自己会创建*/
public class Demo01 extends Group {
    public Demo01(){
        Utils utils = new Utils();
        Body box = utils.createBox(Constant.width/2 + 10, 0, Constant.width / 2, 1, true);
        box.setUserData("box1");
//        Body box1 = utils.createBox(20, 70, 3, 3, false);

        Body box1 = utils.createRadio(20, 70, false);
        box1.setUserData("box2");
        Body box2 = utils.createBox(20, 20, 10, 10, false);


        RevoluteJointDef def = new RevoluteJointDef();
        Vector2 position = box1.getPosition();
        position.x = position.x;
        def.enableMotor = true;
        def.maxMotorTorque = 100;
        def.initialize(box2,box1,box1.getWorldVector(position));
        Constant.world.createJoint(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
