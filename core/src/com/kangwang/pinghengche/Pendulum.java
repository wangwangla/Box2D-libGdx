package com.kangwang.pinghengche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.utils.Array;
import com.kangwang.word.Constant;

import java.util.Map;

public class Pendulum {
    private final float PI = 3.141f;
    private Box cart;
    private Circle wheel;
    private Box stick;
    private Box handle;
    private Array<Body> elements = new Array();
    private  WheelJoint wheelJoint;

    public Pendulum(float x,float y){
        float len = 96.0F;
        this.cart = new Box(x,y,30,16);
        this.wheel = new Circle(x,y,24);
        this.stick = new Box(x,y,8,len,true);
        this.handle = new Box(x,y,32,16);
//
        WeldJointDef weldJointDef = new WeldJointDef();
        weldJointDef.bodyA = this.cart.body;
        weldJointDef.bodyB = this.stick.body;
        weldJointDef.localAnchorB.set(0,-len/2);
        Constant.world.createJoint(weldJointDef);
//
        weldJointDef = new WeldJointDef();
        weldJointDef.bodyA = this.handle.body;
        weldJointDef.bodyB = this.stick.body;
        weldJointDef.localAnchorB.set(0,len/2);
        Constant.world.createJoint(weldJointDef);
//
//        weldJointDef = new WeldJointDef();
//        weldJointDef.bodyA = this.wheel.body;
//        weldJointDef.bodyB = this.cart.body;
//        weldJointDef.localAnchorB.set(0,-len/2);
//        Constant.world.createJoint(weldJointDef);
//
        WheelJointDef weldJointDef1 = new WheelJointDef();
        weldJointDef1.bodyA = this.wheel.body;
        weldJointDef1.bodyB = this.cart.body;
//        weldJointDef1.localAnchorB.set(0,-len/2);
//        Constant.world.createJoint(weldJointDef1);

        weldJointDef1.frequencyHz = 30;
        weldJointDef1.dampingRatio = 1.0F;
        weldJointDef1.maxMotorTorque = 1000;

        wheelJoint = (WheelJoint) Constant.world.createJoint(weldJointDef1);
        wheelJoint.enableMotor(true);
        wheelJoint.setMotorSpeed(0);

        elements.add(wheel.body,stick.body,handle.body);
    }

    public Vector2 getPosition() {
        return this.wheel.body.getPosition();
    }

    public Vector2 getVelocity() {
        return this.cart.body.getLinearVelocity();
    }

    public float getAngleRadians() {
        float radians = (float) Math.toRadians(this.stick.body.getAngle());
        while(radians >  PI) radians -= 2*PI;
        while(radians < -PI) radians += 2*PI;
        return radians;
    }

    public void setMotorSpeed(float speed) {
        wheelJoint.enableMotor(true);
        this.wheelJoint.setMotorSpeed(speed);
    }

    public void display() {

        this.wheel.display();
        this.cart.display();
        this.handle.display();
        this.stick.display();

    }

    public Array getElements() {
        return this.elements;
    }
}
