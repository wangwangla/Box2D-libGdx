package com.kangwang.learn;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kangwang.world.Constant;

public class TextureBody extends Image {
    public TextureBody (){
        super(new Texture("1.png"));
        createBody(600);
    }

    private Body body;

    public void createBody(float y){
        System.out.println("create");
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(Constant.width/2,y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = Constant.world.createBody(bodyDef);

        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2,getHeight()/2);
//        def.friction = 0.4F;
//        def.restitution = 0.3F;
        def.density = 0F;
        def.shape = shape;
        body.createFixture(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(body.getPosition().x,body.getPosition().y, Align.center);

    }
}
