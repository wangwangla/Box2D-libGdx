package com.kangwang.pinghengche;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;

public class Spring {
    private MouseJoint mouseJoint;

    public Spring(){

    }

    public Spring(float x,float y){

    }

    public void updata(float x,float y){
        if (this.mouseJoint != null){
//            Vector2 anchorA = mouseJoint.getAnchorA();
//            Vector2 anchorB = mouseJoint.getAnchorB();
            mouseJoint.setTarget(new Vector2(x,y));
        }
    }

    public void bind(float x,float y){
//        let md = new box2d.b2MouseJointDef();
//        md.bodyA = world.CreateBody(new box2d.b2BodyDef());
//        md.bodyB = ele.body;
//
//        ele.body.SetFixedRotation(true);
//
//        let mp = scaleToWorld(x, y);
//        md.target = mp;
//
//        md.maxForce = 1000.0 * ele.body.m_mass;
//        md.frequencyHz = 5.0;
//        md.dampingRatio = 0.9;
//
//        this.mouseJoint = world.CreateJoint(md);
    }
}
