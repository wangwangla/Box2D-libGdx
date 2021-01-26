package com.kangwang.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

public class GameView extends Group {
    static final int e_columnCount = 5;
    static final int e_rowCount = 16;

    Body m_bullet;
    Body[] m_bodies = new Body[e_rowCount * e_columnCount];
    int[] m_indices = new int[e_rowCount * e_columnCount];
    public GameView(){
        Constant.world.setContactListener(new WorldListener());
        setSize(Constant.width,Constant.hight);
        //0,0为创建的左下角
        Box2DFactory factory = new Box2DFactory();
        factory.setSize(Constant.width,1);
        factory.setDensity(0.5F);
        factory.setFriction(0.4F);
//        factory.setRestitution(0.6F);
        factory.getBody();

        factory.reset();

        PolygonShape polygonShape = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(0, 0);
        vertices[1] = new Vector2(100, 100);
        vertices[2] = new Vector2(200, 0);
        polygonShape.set(vertices);
        factory.setShape(polygonShape);
        factory.setRestitution(0.3F);
        factory.setType(BodyDef.BodyType.DynamicBody);
        Body body = factory.getBody();
        body.setTransform(0,0,0);


        BodyImage bodyImage = new BodyImage(new TextureRegion(new Texture("2.png")));
        bodyImage.setPosition(120,600);
        bodyImage.createBox2DImage();
        bodyImage.getBody().setFixedRotation(false);
        addActor(bodyImage);
    }

    private void initKey() {
        bottom.getBody().setLinearVelocity(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){

        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            bottom.getBody().setLinearVelocity(-100,0);
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            bottom.getBody().setLinearVelocity(100,0);
        }
    }

    private void initRedis() {
        BodyImage buut = new BodyImage(new TextureRegion(new Texture("2.png")));
        buut.setSize(20,20);
        buut.setType(BodyDef.BodyType.DynamicBody);
        buut.setPosition(Constant.width/2,50,Align.bottom);
        buut.setRestitution(1F);
        buut.setCategoryBits(Constant.BUTT_BIT);
        buut.setMaskBits(Constant.BLACK_BIT | Constant.WALL_BIT|Constant.BUTTOM_BIT);
        buut.createBox2DImage();
        buut.getBody().setLinearVelocity(0,71);
        addActor(buut);
    }
    BodyImage bottom;
    private void initBottomBlack() {
        bottom = new BodyImage(new TextureRegion(new Texture("1.png")));
        bottom.setSize(100,5);
        bottom.setPosition(Constant.width/2,20, Align.bottom);
        addActor(bottom);
        bottom.setDensity(100);
        bottom.setFriction(100);
        bottom.setType(BodyDef.BodyType.DynamicBody);
        bottom.setCategoryBits(Constant.BUTTOM_BIT);
        bottom.setMaskBits(Constant.BUTT_BIT);
        bottom.createBox2DImage();
    }


    public void initBlack(){
        float v = Constant.width / 10;
        for (int i = 0; i < 10; i++) {
            BodyImage image = new BodyImage(new TextureRegion(new Texture("1.png")));
            image.setSize(v-3,v);
            image.setPosition(v*i+1.5F,Constant.hight - 10, Align.topLeft);
            addActor(image);
            image.setType(BodyDef.BodyType.StaticBody);
            image.setCategoryBits(Constant.BLACK_BIT);
            image.setMaskBits(Constant.BUTT_BIT);
            image.createBox2DImage();
        }
    }
    enum State {
        Unknown, Above, Below
    }

    Fixture m_platform;
    Fixture m_character;
    float m_bottom;
    float m_top;
    float m_radius;
    State m_state;

    public void xx5(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-20.0f, 0), new Vector2(20.0f, 0f));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

        {
            BodyDef bd = new BodyDef();
            bd.position.set(0, 10);
            Body body = Constant.world.createBody(bd);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(3, 0.5f);
            m_platform = body.createFixture(shape, 0);
            m_bottom = 10.0f - 0.5f;
            m_top = 10.0f + 0.5f;
        }

        {
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(0, 12);
            Body body = Constant.world.createBody(bd);

            m_radius = 0.5f;
            CircleShape shape = new CircleShape();
            shape.setRadius(m_radius);
            m_character = body.createFixture(shape, 20.0f);
            shape.dispose();

            m_state = State.Unknown;
        }

        Constant.world.setContactFilter(new ContactFilter() {

            @Override
            public boolean shouldCollide (Fixture fixtureA, Fixture fixtureB) {
                if ((fixtureA == m_platform && fixtureB == m_character) || (fixtureB == m_platform && fixtureA == m_character)) {
                    Vector2 position = m_character.getBody().getPosition();
                    if (position.y < m_top + m_radius - 3.0f * 0.005f)
                        return false;
                    else
                        return true;
                } else
                    return true;
            }

        });

    }

    public void xx4(){
        Body ground;

        {
            BodyDef bd = new BodyDef();
            ground = Constant.world.createBody(bd);
            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

        {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(2, 5);

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(-10, 10);
            bd.angle = 0.5f * (float)Math.PI;
            bd.allowSleep = false;

            Body body = Constant.world.createBody(bd);
            body.createFixture(shape, 5.0f);

            PrismaticJointDef pjd = new PrismaticJointDef();

            Vector2 axis = new Vector2(2, 1);
            axis.nor();
            pjd.initialize(ground, body, new Vector2(0, 0), axis);

            pjd.motorSpeed = 10.0f;
            pjd.maxMotorForce = 10000.0f;
            pjd.enableMotor = true;
            pjd.lowerTranslation = 0;
            pjd.upperTranslation = 20.0f;
            pjd.enableLimit = true;

            m_joint = (PrismaticJoint)Constant.world.createJoint(pjd);
        }
    }

    PrismaticJoint m_joint;
    public void xx3(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0.0f);
            shape.dispose();
        }

        {
            float a = 0.5f;
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(a, a);

            Vector2 x = new Vector2(-7.0f, 0.75f);
            Vector2 y = new Vector2();
            Vector2 deltaX = new Vector2(0.5625f, 1.25f);
            Vector2 deltaY = new Vector2(1.125f, 0.0f);

            for (int i = 0; i < 20; i++) {
                y.set(x);

                for (int j = i; j < 20; j++) {
                    BodyDef bd = new BodyDef();
                    bd.type = BodyDef.BodyType.DynamicBody;
                    bd.position.set(y);
                    Body body = Constant.world.createBody(bd);
                    body.createFixture(shape, 5.0f);

                    y.add(deltaY);
                }

                x.add(deltaX);
            }

        }
    }

    public void xx2(){
        // next we create a static ground platform. This platform
        // is not moveable and will not react to any influences from
        // outside. It will however influence other bodies. First we
        // create a PolygonShape that holds the form of the platform.
        // it will be 100 meters wide and 2 meters high, centered
        // around the origin
        PolygonShape groundPoly = new PolygonShape();
        groundPoly.setAsBox(50, 1);

        // next we create the body for the ground platform. It's
        // simply a static body.
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = Constant.world.createBody(groundBodyDef);

        // finally we add a fixture to the body using the polygon
        // defined above. Note that we have to dispose PolygonShapes
        // and CircleShapes once they are no longer used. This is the
        // only time you have to care explicitly for memomry managment.
        groundBody.createFixture(groundPoly, 10);
        groundPoly.dispose();

        // next we create 50 boxes at random locations above the ground
        // body. First we create a nice polygon representing a box 2 meters
        // wide and high.
        PolygonShape boxPoly = new PolygonShape();
        boxPoly.setAsBox(1, 1);

        // next we create the 50 box bodies using the PolygonShape we just
        // defined. This process is similar to the one we used for the ground
        // body. Note that we reuse the polygon for each body fixture.
        for (int i = 0; i < 20; i++) {
            // Create the BodyDef, set a random position above the
            // ground and create a new body
            BodyDef boxBodyDef = new BodyDef();
            boxBodyDef.type = BodyDef.BodyType.DynamicBody;
            boxBodyDef.position.x = -24 + (float)(Math.random() * 48);
            boxBodyDef.position.y = 10 + (float)(Math.random() * 100);
            Body boxBody = Constant.world.createBody(boxBodyDef);

            // add the boxPoly shape as a fixture
            boxBody.createFixture(boxPoly, 10);
        }

        // we are done, all that's left is disposing the boxPoly
        boxPoly.dispose();

        // next we add a few more circles
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1);

        for (int i = 0; i < 10; i++) {
            BodyDef circleBodyDef = new BodyDef();
            circleBodyDef.type = BodyDef.BodyType.DynamicBody;
            circleBodyDef.position.x = -24 + (float)(Math.random() * 48);
            circleBodyDef.position.y = 10 + (float)(Math.random() * 100);
            Body circleBody = Constant.world.createBody(circleBodyDef);

            // add the boxPoly shape as a fixture
            circleBody.createFixture(circleShape, 10);
        }
    }

    int e_count = 10;
    public void xx1(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

        {
            CircleShape shape = new CircleShape();
            shape.setRadius(1.0f);

            for (int i = 0; i < e_count; i++) {
                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.DynamicBody;
                bd.position.set(0, 4.0f + 3.0f * i);
                Body body = Constant.world.createBody(bd);
                body.createFixture(shape, 1.0f);
            }

            shape.dispose();
        }
    }

    public void xxx(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0.0f);
            shape.dispose();
        }

        {
            CircleShape shape = new CircleShape();
            shape.setRadius(1);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 1.0f;

            float restitution[] = {0, 0.1f, 0.3f, 0.5f, 0.75f, 0.9f, 1.0f};

            for (int i = 0; i < restitution.length; i++) {
                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.DynamicBody;
                bd.position.set(-10.0f + 3.0f * i, 20.0f);

                Body body = Constant.world.createBody(bd);
                fd.restitution = restitution[i];
                body.createFixture(fd);
            }

            shape.dispose();
        }
    }

    public void xx(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0.0f);

            shape.set(new Vector2(20, 0), new Vector2(20, 20));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

        float xs[] = {0, -10, -5, 5, 10};

        for (int j = 0; j < e_columnCount; j++) {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.5f, 0.5f);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 1.0f;
            fd.friction = 0.3f;

            for (int i = 0; i < e_rowCount; i++) {
                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.DynamicBody;

                int n = j * e_rowCount + i;
                m_indices[n] = n;

                float x = 0;
                bd.position.set(xs[j] + x, 0.752f + 1.54f * i);
                Body body = Constant.world.createBody(bd);
                body.setUserData(n);

                m_bodies[n] = body;
                body.createFixture(fd);
            }

            shape.dispose();
        }

        m_bullet = null;
    }

    @Override
    public void act(float delta) {
//        initKey();
        super.act(delta);

        Constant.world.step(1/60f, 6, 2);
        Constant.renderer.render(Constant.world,Constant.combined);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
