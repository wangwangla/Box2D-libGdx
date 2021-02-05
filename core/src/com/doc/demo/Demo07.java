package com.doc.demo;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Box2DFactory;
import com.kangwang.world.Constant;

public class Demo07 extends Group {
    private Body body;
    public Demo07(){
        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
        factory.setSize(Constant.width, 1);
        factory.setDensity(0.5F);
        factory.setFriction(0.4F);
        Body body1 = factory.getBody();
        factory.reset();
        BodyDef bodyDef = new BodyDef();
//        bodyDef.active = false; //是否可用
//        bodyDef.allowSleep = true;  //允许睡眠
        bodyDef.awake = true;
        bodyDef.bullet = true ; //开始子弹高速 开启ccD碰撞检测
        float angle = bodyDef.angle; // 角度
        float angularDamping = bodyDef.angularDamping;

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(10,70);
        this.body = Constant.world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(3, 3);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
//        fixtureDef.friction = 0.2F;
        fixtureDef.restitution = 0.3F;
        this.body.createFixture(fixtureDef);
        polygonShape.dispose();

        this.body.isActive();
        this.body.setActive(true);

//        WheelJointDef def = new WheelJointDef();
//        def.initialize(body1,body,new Vector2(10,70),
//                new Vector2(0,1));
//        def.enableMotor = true;
//        def.motorSpeed = 100;
//        def.maxMotorTorque = 10;
//        Constant.world.createJoint(def);


//        Vector2 worldCenter = body1.getWorldCenter();
//        //世界远点的位置
//        Vector2 position = body1.getPosition();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        body.getWorldVector(new Vector2(1.5F,1.5F))
        Constant.world.clearForces();
        body.applyTorque(400,true);
    }
}
