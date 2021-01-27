package com.kangwang.pinghengche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Array;
import com.kangwang.word.Constant;

public class Pendulum {
    private Box cart;
    private Circle wheel;
    private Box stick;
    private Box handle;
    private Array array = new Array();
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
        weldJointDef.localAnchorB.set(0,len/2);
        Constant.world.createJoint(weldJointDef);
//
        weldJointDef = new WeldJointDef();
        weldJointDef.bodyA = this.handle.body;
        weldJointDef.bodyB = this.stick.body;
        weldJointDef.localAnchorB.set(0,-len/2);
        Constant.world.createJoint(weldJointDef);
//
        weldJointDef = new WeldJointDef();
        weldJointDef.bodyA = this.wheel.body;
        weldJointDef.bodyB = this.cart.body;
        weldJointDef.localAnchorB.set(0,-len);
        Constant.world.createJoint(weldJointDef);
//
        weldJointDef = new WeldJointDef();
        weldJointDef.bodyA = this.wheel.body;
        weldJointDef.bodyB = this.stick.body;
        weldJointDef.localAnchorB.set(0,-len/2);
        Constant.world.createJoint(weldJointDef);

        weldJointDef.frequencyHz = 30;
        weldJointDef.dampingRatio = 1.0F;
    }

    public Vector2 getPosition(){
        return this.wheel.body.getPosition();
    }

    public Vector2 getVelocity(){
        return this.cart.body.getLinearVelocity();
    }

    public float getAngleRadians(){
        float angle = this.stick.body.getAngle();
        while (angle > Math.PI) angle -= 2* Math.PI;
        while (angle < -Math.PI) angle += 2* Math.PI;
        return angle;
    }

    public void display(){
        this.wheel.display();
        this.cart.display();
        this.handle.display();
        this.stick.display();
    }

    public Array getArray() {
        return array;
    }

    public void execteInput(){
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();System.out.println(wheel.body.getAngle());
//        if (x > this.wheel.body.getPosition().x){
//            if (wheel.body.getAngle()> -0.5F) {
//                wheel.body.setLinearVelocity(10, -100);
//            }else {
//                wheel.body.setLinearVelocity(0,0);
//            }
//        }else if (x < this.wheel.body.getPosition().x){
//            if (wheel.body.getAngle() < 0.5F) {
//                wheel.body.setLinearVelocity(-10, -100);
//            }else {
//                wheel.body.setLinearVelocity(0,0);
//            }
//        }
         if (x > this.wheel.body.getPosition().x){
                wheel.body.setLinearVelocity(100, -100);
        }else if (x < this.wheel.body.getPosition().x){
                wheel.body.setLinearVelocity(-100, -100);
        }
    }
}
