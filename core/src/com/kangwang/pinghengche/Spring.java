package com.kangwang.pinghengche;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.utils.Array;
import com.kangwang.WorldConstant;
import com.kangwang.word.Constant;

public class Spring {
    private MouseJoint mouseJoint;

    public Spring(){
        mouseJoint = null;
    }

    public void update(float x,float y){
        if (this.mouseJoint != null){
            mouseJoint.setTarget(new Vector2(WorldConstant.convert(x),WorldConstant.convert(y)));
        }
    }

    public void bind(float x, float y, Body body){
//        WheelJointDef weldJointDef1 = new WheelJointDef();
//        weldJointDef1.bodyA = this.wheel.body;
//        weldJointDef1.bodyB = this.cart.body;
////        weldJointDef1.localAnchorB.set(0,-len/2);
////        Constant.world.createJoint(weldJointDef1);
//
//        weldJointDef1.frequencyHz = 30;
//        weldJointDef1.dampingRatio = 1.0F;
//        weldJointDef1.maxMotorTorque = 1000;

        MouseJointDef md = new MouseJointDef();
        Body body1 = new Boundary(0, 12, 10, 10).getBody();
        md.bodyA = body1;
        md.bodyB = body;
        body.setFixedRotation(true);
        md.target.set(WorldConstant.convert(x),WorldConstant.convert(y));
        md.maxForce = 1000.0f * body.getMass();
        md.frequencyHz = 5.0f;
        md.dampingRatio = 0.9f;

        this.mouseJoint = (MouseJoint) Constant.world.createJoint(md);
    }
}
