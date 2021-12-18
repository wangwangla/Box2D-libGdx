package com.doc.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.example.bodyimage.BodyImage;
import com.kangwang.world.Box2DFactory;
import com.kangwang.world.Constant;

public class Demo01 extends Group {

    public Demo01() {
        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
        factory.setSize(Constant.width, 1);
        factory.setDensity(0.5F);
        factory.setFriction(0.4F);
        factory.getBody();
        factory.reset();
        PolygonShape polygonShape = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(20, 0);
        vertices[1] = new Vector2(0, 40);
        vertices[2] = new Vector2(0, 0);
        polygonShape.set(vertices);
        factory.setShape(polygonShape);
        factory.setRestitution(0.3F);
        factory.setType(BodyDef.BodyType.DynamicBody);
        Body body = factory.getBody();
        body.setTransform(0, 0, 0);
        BodyImage bodyImage = new BodyImage(new TextureRegion(new Texture("2.png")));
        bodyImage.setPosition(10, 60);
        bodyImage.createBox2DImage();
        bodyImage.getBody().setFixedRotation(false);
        addActor(bodyImage);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1 / 6f, 6, 2);
        Constant.renderer.render(Constant.world, Constant.combined);
    }
}