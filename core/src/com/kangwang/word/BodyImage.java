package com.kangwang.word;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class BodyImage extends Image {
    private BodyDef.BodyType type = BodyDef.BodyType.DynamicBody;
    private Vector2 position = new Vector2(0,0);
    private Shape shape;
    private short categoryBits = -1;
    private int maskBits = -1;
    private Body body;
    private float density;
    private float friction;
    private float restitution;

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

    public BodyImage(TextureRegion region) {
        super(region);
        setSize(getWidth()/Constant.PPM,getHeight()/Constant.PPM);
        setOrigin(Align.center);
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

    public void setWidth(int width) {
        this.width = width;
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

    public int getMaskBits() {
        return maskBits;
    }

    public void setMaskBits(int maskBits) {
        this.maskBits = maskBits;
    }

    public void createBox2DImage(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = this.type;
        bodyDef.position.set(getX(Align.center),getY(Align.center));
        this.body = Constant.world.createBody(bodyDef);
        if (shape == null){
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(getWidth()/2
                    , getHeight()/2);
            shape = polygonShape;
        }
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        if (categoryBits != -1){
            fixtureDef.filter.categoryBits = categoryBits;
        }
        if (maskBits != -1) {
            fixtureDef.filter.maskBits = (short) maskBits;
        }
        fixtureDef.density = 100;
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);
    }

    float degrees;

    @Override
    public void act(float delta) {
        super.act(delta);
        if (body != null){
            if (body.getPosition().x != getX() || body.getPosition().y != getY()) {
                setPosition(body.getPosition().x, body.getPosition().y,Align.center);
            }
            degrees = (float) Math.toDegrees(body.getAngle());
            if (degrees != getRotation()){
                setRotation(degrees);
            }
        }
    }

    public Body getBody() {
        return body;
    }
}
