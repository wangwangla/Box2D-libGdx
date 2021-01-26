package com.kangwang.word;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Box2DFactory {
    private BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
    private Vector2 position = new Vector2(0,0);
    private float width = 10;
    private float height = 10;
    private Shape shape;
    private short categoryBits = -1;
    private short maskBits = -1;
    private float density;
    private float friction;
    private float restitution;

    public void reset(){
        type = BodyDef.BodyType.StaticBody;
        position = new Vector2(0,0);
        float width = 10;
        float height = 10;
        Shape shape;
        short categoryBits = -1;
        short maskBits = -1;
        float density;
        float friction;
        float restitution;
    }

    public void setSize(float width,float height){
        this.width = width;
        this.height = height;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getRestitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public BodyDef.BodyType getType() {
        return type;
    }

    public void setType(BodyDef.BodyType type) {
        this.type = type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public short getCategoryBits() {
        return categoryBits;
    }

    public void setCategoryBits(short categoryBits) {
        this.categoryBits = categoryBits;
    }

    public short getMaskBits() {
        return maskBits;
    }

    public void setMaskBits(short maskBits) {
        this.maskBits = maskBits;
    }

    public Body getBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = this.type;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(position.x,position.y);
        Body body = Constant.world.createBody(bodyDef);
        if (shape == null){
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(width, height);
            shape = polygonShape;
        }
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        if (categoryBits != -1){
            fixtureDef.filter.categoryBits = categoryBits;
        }
        if (maskBits != -1) {
            fixtureDef.filter.maskBits = maskBits;
        }
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
