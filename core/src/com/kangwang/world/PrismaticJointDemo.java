package com.kangwang.world;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

/**
 * 这个关节的作用时候
 *
 *
 * //bodyA 固定转轴
 * //bodyB 运动的
 * //开始位置
 * //方向    axis的值，确定了从开始到结束的夹角`
 *
 * 作用就是告诉他们从你位置怎样走，
 */
public class PrismaticJointDemo {
    PrismaticJoint m_joint;
    protected void createWorld (World world) {
        Body ground;

        {
            BodyDef bd = new BodyDef();
            ground = world.createBody(bd);
            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

        {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(2, 5);

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(10, 20);
            bd.angle = 0.5f * (float)Math.PI;
            bd.allowSleep = false;

            Body body = world.createBody(bd);
            body.createFixture(shape, 5.0f);

            PrismaticJointDef pjd = new PrismaticJointDef();

            Vector2 axis = new Vector2(11, 2);
//            axis.nor();
            //bodyA 固定转轴
            //bodyB 运动的
            //开始位置
            //方向    axis的值，确定了从开始到结束的夹角
            axis.nor();
            pjd.initialize(ground, body, new Vector2(0, 0), axis);

            pjd.motorSpeed = 10.0f; //最大速度
            pjd.maxMotorForce = 10000.0f;//为了到底最大速度所加的力
            pjd.enableMotor = true;//开启马达
            pjd.lowerTranslation = 0;//最小位置
            pjd.upperTranslation = 20.0f; // 最大位字
            pjd.enableLimit = true;//范围需要限制
            pjd.referenceAngle = 90;//夹角

            //最大力最大夹角只有在开启马达的时候有作用

            m_joint = (PrismaticJoint)world.createJoint(pjd);
        }
    }

    public boolean keyDown (int keyCode) {
        if (keyCode == Input.Keys.L) m_joint.enableLimit(!m_joint.isLimitEnabled());
        if (keyCode == Input.Keys.M) m_joint.enableMotor(!m_joint.isMotorEnabled());
        if (keyCode == Input.Keys.S) m_joint.setMotorSpeed(-m_joint.getMotorSpeed());

        return false;
    }
}
