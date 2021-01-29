package com.doc.demo;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Box2DFactory;
import com.kangwang.world.Constant;

public class Demo02 extends Group {

    public Demo02() {
        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
        factory.setSize(Constant.width, 1);
        factory.setDensity(0.5F);
        factory.setFriction(0.4F);
        factory.getBody();
        factory.reset();



        BodyDef bodyDef = new BodyDef();
//        bodyDef.active = false; //是否可用
//        bodyDef.allowSleep = true;  //允许睡眠
        bodyDef.awake = true;
        bodyDef.bullet = true ; //开始子弹高速 开启ccD碰撞检测
        float angle = bodyDef.angle; // 角度
        float angularDamping = bodyDef.angularDamping;
        bodyDef.fixedRotation = true;

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(50,40);
        Body body = Constant.world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(10, 10);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.2F;
        fixtureDef.restitution = 0.3F;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        body.isActive();
        body.setActive(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1 / 6f, 6, 2);
        Constant.renderer.render(Constant.world, Constant.combined);
    }
}