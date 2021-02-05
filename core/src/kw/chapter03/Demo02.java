package kw.chapter03;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * 阻尼
 */
public class Demo02 extends Group {
    private float timeStemp = 1/60F;
    private int velocityIterations = 6;
    private int psoitionIterations = 2;

    public Demo02(){
        Utils utils = new Utils();
        utils.createBox(0,0, Constant.width,1,true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(10,10,new Vector2(5,5),0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        for (int i = 0; i < 2; i++) {
            bodyDef.position.set(6+30*i,30);
            Body body = Constant.world.createBody(bodyDef);
            body.createFixture(fixtureDef);
            if (i%2 == 0)
            body.setLinearDamping(0.4F);
        }
        polygonShape.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
