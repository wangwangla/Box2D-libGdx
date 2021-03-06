package com.doc.demo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kangwang.world.Box2DFactory;
import com.kangwang.world.Constant;

public class Demo03 extends Group {
    private Body body;
    public Demo03(){
        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
        factory.setSize(Constant.width, 1);
        factory.setDensity(0.5F);
        factory.setFriction(0.4F);
        Body body1 = factory.getBody();
        factory.reset();
        BodyDef bodyDef = new BodyDef();
//        bodyDef.active = false; //是否可用
//        bodyDef.allowSleep = true;  //允许睡眠
//        bodyDef.awake = true;
//        bodyDef.bullet = true ; //开始子弹高速 开启ccD碰撞检测
//        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(10,70);
        this.body = Constant.world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(3, 3);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
        this.body.createFixture(fixtureDef);
        polygonShape.dispose();

//        body設置位置
        addAction(Actions.forever(Actions.delay(6,Actions.run(()->{
            this.body.setTransform(new Vector2(20,20),10);
        }))));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        body.getWorldVector(new Vector2(1.5F,1.5F))
        Constant.world.clearForces();
        body.applyTorque(40,true);
    }
}
