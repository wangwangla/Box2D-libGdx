package wk.box.game.screen.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import wk.box.game.Box2dGame;

public class Box2dUtils {
    private World world;
    public Box2dUtils(){
        this.world = Box2dGame.world;
    }

    public Body create(BodyDef bodyDef,FixtureDef fixtureDef){
        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        return body;
    }

    public Body createBox(float x, float y, float boxWidth, float boxHeight,boolean isStatcic){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatcic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x,y);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(boxWidth, boxHeight);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        Body body = create(bodyDef, fixtureDef);
        polygonShape.dispose();
        return body;
    }


    public Body radis(float x, float y, float redis,boolean isStatcic){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatcic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        CircleShape shape = new CircleShape();
        shape.setRadius(redis);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        Body body = create(bodyDef, fixtureDef);
        shape.dispose();
        return body;
    }
}
