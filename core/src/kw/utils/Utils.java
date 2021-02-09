package kw.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kangwang.world.Constant;

public class Utils {
    public Body createBox(float posX, float posY, float boxWidth, float boxHeight, boolean isStatic){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(posX,posY);
        Body body = Constant.world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(boxWidth, boxHeight);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        return body;
    }
    public FixtureDef fixtureDef;
    public Body createRadio(float posX, float posY, boolean isStatic){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(posX,posY);
        Body body = Constant.world.createBody(bodyDef);
        CircleShape polygonShape = new CircleShape();
        polygonShape.setRadius(5);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        return body;
    }

    public void xx(){

//        for (int i= 0; i < 50; i++) {
//            BodyDef bodyDef = new BodyDef();
//            bodyDef.type = BodyDef.BodyType.DynamicBody;
//            bodyDef.fixedRotation = false;
//            //定义形状需求
//            PolygonShape shape = new PolygonShape();
//            FixtureDef def = new FixtureDef();
//            //形状的密度、摩擦系数、反馈
//            def.density = 0;
//            def.friction = 0.3F;
//            def.restitution = 0.2F;
//            def.shape = shape;
//            //计算每个线段的角度、坐标
//            float angle = (float)(i/100.0F *Math.PI*2);
//            float bx = (float) (100 * Math.cos(angle));
//            float by = (float) (100 * Math.sin(angle));
//            //创建有方向的矩形刚体，合成总的圆形刚体
//            shape.setAsBox(5/30, 100/30, new Vector2(bx,by),angle);
//            bodyDef.position.set(100,100);
//            Body body = Constant.world.createBody(bodyDef);
//            //4.b2Body刚体工厂根据需求createShape生产形状
//            body.createFixture(def);
//        }

    }
}
