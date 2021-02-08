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
public class Demo02 extends Group {
    private RevoluteJointDef def;
    Body box1;
    public Demo02(){
        Utils utils = new Utils();
        Body box = utils.createBox(Constant.width/2 + 10, 0, Constant.width / 2, 1, true);
        box.setUserData("box1");
        box1 = utils.createBox(20, 70, 10, 10, false);
        box1.setUserData("box2");
        Body box2 = utils.createBox(20, 20, 50, 10, false);


        def = new RevoluteJointDef();
        Vector2 position = box1.getPosition();
        position.y = position.y;
        def.initialize(box2,box1,box1.getWorldVector(position));
        def.enableMotor = true;
        Constant.world.createJoint(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
        box1.applyTorque(1000,true);
    }
}
