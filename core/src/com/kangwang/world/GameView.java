package com.kangwang.world;

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
import com.kangwang.example.bodyimage.BodyImage;

import kw.other.Game08;

public class GameView extends Group {
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public GameView(){
        setSize(Constant.width,Constant.hight);
//        Demo01 demo01 = new Demo01();
//        addActor(demo01);
//        kw.chapter03.Demo03 demo02 = new kw.chapter03.Demo03();
//        addActor(demo02);
//        ImageDemo01 demo = new ImageDemo01();
//        addActor(demo);

//        ImageDemo demo = new ImageDemo();
//        addActor(demo);
//        Demo01 demo01 = new Demo01();
//        addActor(demo01);

//        Game02 game01 = new Game02();
//        addActor(game01);

//        Demo01 demo01 = new Demo01();
//        addActor(demo01);

//        Game04 game04 = new Game04();
//        addActor(game04);

//        kw.chapter06.Demo03 demo02 = new kw.chapter06.Demo03();
//        addActor(demo02);

//        Demo05 game05 = new Demo05();
//        addActor(game05);

//        Demo01 demo01 = new Demo01();
//        addActor(demo01);

//        Demo07 demo07 = new Demo07();
//        addActor(demo07);

//        Demo06 demo06 = new Demo06();
//        addActor(demo06);

//        Game07 game07 = new Game07();
//        addActor(game07);

//        Demo11 demo11 = new Demo11();
//        addActor(demo11);

//        Demo12 demo12 = new Demo12();
//        addActor(demo12);

//        Demo13 demo12 = new Demo13();
//        addActor(demo12);

//        Demo14 demo12 = new Demo14();
//        addActor(demo12);

//        Demo08 demo12 = new Demo08();
//        addActor(demo12);
//        Demo11 demo07 = new Demo11();
//        addActor(demo07);

//        Demo02 demo02 = new Demo02() ;
//        addActor(demo02);

//        Demo03 demo03 = new Demo03();
//        addActor(demo03);

//        Game08 game08 = new Game08();
//        addActor(game08);
//
//        Game08 game02 = new Game08();
//        addActor(game02);

        /*
        小车
         */
//        Demo11 demo01 = new Demo11();
//        addActor(demo01);

        Game08 game08 = new Game08();
        addActor(game08);
    }
















    public void iix(){
        //        Demo03 demo03 = new Demo03();
//        addActor(demo03);

//        Demo08 demo06 = new Demo08();
//        addActor(demo06);
//        Constant.world.setContactListener(new WorldListener());
//        setSize(Constant.width,Constant.hight);
//        //0,0为创建的左下角
//        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
//        factory.setPosition(new Vector2(30,80));
//        factory.setType(BodyDef.BodyType.DynamicBody);
//        factory.setSize(20,20);
//        factory.setDensity(0.5F);
//        factory.setFriction(0.4F);
//        body1 = factory.getBody();
//        factory.reset();
//        com.kangwang.world.Box2DFactory fac = new Box2DFactory();
//        fac.setPosition(new Vector2(30,30));
//        fac.setSize(20,20);
//        fac.setType(BodyDef.BodyType.DynamicBody);
//        fac.setDensity(0.5F);
//        fac.setFriction(0.4F);
//        Body body2 = fac.getBody();
//        fac.reset();

//        RevoluteJointDef def = new RevoluteJointDef();
//        def.initialize(body1,body2,new Vector2(10,100));
//        Constant.world.createJoint(def);

//        WheelJointDef def = new WheelJointDef();
//        def.initialize(body2,body1,new Vector2(10,100),
//                new Vector2(0,1));
//        def.enableMotor = true;
//        def.motorSpeed = 100;
//        def.maxMotorTorque = 10;


//        MotorJointDef def = new MotorJointDef();
//        def.initialize(body1,body2);
//        def.linearOffset.set(100,200);
//        def.maxForce = 10;
//        Constant.world.createJoint(def);
    }

        /**********************斜面滑下***********************/
//        Constant.world.setContactListener(new WorldListener());
//        setSize(Constant.width,Constant.hight);
//        //0,0为创建的左下角
//        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
//        factory.setSize(Constant.width,1);
//        factory.setDensity(0.5F);
//        factory.setFriction(0.4F);
//        Body body1 = factory.getBody();
//        factory.reset();
//
////
//        com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage bodyImage = new com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage(new TextureRegion(new Texture("2.png")));
//        bodyImage.setPosition(10,60);
//        Body box2DImage = bodyImage.createBox2DImage();
//        bodyImage.getBody().setFixedRotation(false);
//        addActor(bodyImage);
//
//
//        PolygonShape polygonShape = new PolygonShape();
//        Vector2[] vertices = new Vector2[3];
//        vertices[0] = new Vector2(20, 0);
//        vertices[1] = new Vector2(0, 40);
//        vertices[2] = new Vector2(0, 0);
//        polygonShape.set(vertices);
//        factory.setPosition(new Vector2(10,100));
//        factory.setShape(polygonShape);
//        factory.setRestitution(0.3F);
//        factory.setType(BodyDef.BodyType.DynamicBody);
//        Body body = factory.getBody();
//
//        PrismaticJointDef def = new PrismaticJointDef();
//        def.initialize(box2DImage,body,new Vector2(20,100),new Vector2(20,100));
//        Constant.world.createJoint(def);

//        MouseJointDef def = new MouseJointDef();
//        def.bodyA = body1;
//        def.bodyB = box2DImage;
//        def.target.set(30,10);
//        def.frequencyHz = 0.2F;
//
//        Constant.world.createJoint(def);
//
//        addListener(new ClickListener(){
//            @Override
//            public boolean handle(Event e) {
//                return super.handle(e);
//            }
//
//            @Override
//            public boolean mouseMoved(InputEvent event, float x, float y) {
//
//                return super.mouseMoved(event, x, y);
//            }
//
//            @Override
//            public boolean scrolled(InputEvent event, float x, float y, int amount) {
//                return super.scrolled(event, x, y, amount);
//            }
//
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                return super.keyDown(event, keycode);
//            }
//
//            @Override
//            public boolean keyUp(InputEvent event, int keycode) {
//                return super.keyUp(event, keycode);
//            }
//
//            @Override
//            public boolean keyTyped(InputEvent event, char character) {
//                return super.keyTyped(event, character);
//            }
//        });


//        RevoluteJointDef def = new RevoluteJointDef();
//        def.initialize(box2DImage,body1,new Vector2(10,100));
//        Constant.world.createJoint(def);


//            //创建马达关节需求
//            var revoluteJoint:b2RevoluteJointDef = new b2RevoluteJointDef();
//            //用bodyA、bodyB和anchor节点初始化马达关节
//            revoluteJoint.Initialize( bodyA, bodyB, new b2Vec2(posA.x / 30, posA.y / 30));
//            //设置连接的两个刚体之间不进行碰撞检测
//            revoluteJoint.collideConnected = false;
//            //开启马达
//            revoluteJoint.enableMotor = true;
//            //设置马达的最大角速度，单位为 弧度/秒，如设置为Math.PI，即每秒钟转180度
//            revoluteJoint.motorSpeed = Math.PI;
//            //设置最大的扭力值
//            revoluteJoint.maxMotorTorque = 500;
//            //创建马达关节
//            world.CreateJoint(revoluteJoint);

//        DistanceJointDef def = new DistanceJointDef();
//        def.initialize(body1,box2DImage,
//                new Vector2(body1.getPosition().x,body1.getPosition().y),
//                new Vector2(box2DImage.getPosition().x ,box2DImage.getPosition().y));
//        Constant.world.createJoint(def);
//        B2DSeparator b2DSeparator = new B2DSeparator();
//        b2DSeparator.


//       Vector2 linearVelocity = body.getLinearVelocity();
//        linearVelocity.nor();





//        linearVelocity.mulAdd()


//        PolygonShape polygonShape = new PolygonShape();
//        Vector2[] vertices = new Vector2[3];
//        vertices[0] = new Vector2(20, 0);
//        vertices[1] = new Vector2(0, 40);
//        vertices[2] = new Vector2(0, 0);
//        polygonShape.set(vertices);
//        factory.setShape(polygonShape);
//        factory.setRestitution(0.3F);
//        factory.setType(BodyDef.BodyType.DynamicBody);
//        Body body = factory.getBody();
//        body.setTransform(0,0,0);
//        Array<Fixture> fixtureList = body.getFixtureList();


//        Image image = new Image(new Texture("2.png"));
//        addActor(image);


        /**
         * 缩放
         */
//        PolygonShape shape = (PolygonShape) body.getFixtureList().get(0).getShape();
//        Vector2[] vertices1 = new Vector2[3];
//        vertices1[0] = new Vector2(20, 0);
//        vertices1[1] = new Vector2(20, 40);
//        vertices1[2] = new Vector2(0, 0);
//        shape.set(vertices1);


        /**
         * 方法二  没写好
         */
//        Vector2 vector2 = new Vector2();
//        PolygonShape shape = (PolygonShape) body.getFixtureList().get(0).getShape();
//        for (int i = 0; i < shape.getVertexCount(); i++) {
//            shape.getVertex(i,vector2);
//            vector2.mulAdd(new Vector2(30,30));
//        }

//
//        com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage bodyImage = new com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage(new TextureRegion(new Texture("2.png")));
//        bodyImage.setPosition(10,60);
//        bodyImage.createBox2DImage();
//        bodyImage.getBody().setFixedRotation(false);
//        addActor(bodyImage);

        /*********************************************/

//        xx2();

//        test();
//        PrismaticJointDemo pp =  new PrismaticJointDemo();
//        pp.createWorld(Constant.world);

    public void test(){
//        Constant.world.setContactListener(new WorldListener());
//        setSize(Constant.width,Constant.hight);
//        //0,0为创建的左下角
//        com.kangwang.world.Box2DFactory factory = new Box2DFactory();
//        factory.setSize(Constant.width,1);
//        factory.setDensity(0.5F);
//        factory.setFriction(0.4F);
//        Body body1 = factory.getBody();
//        factory.reset();
//
//
//        PolygonShape polygonShape = new PolygonShape();
//        Vector2[] vertices = new Vector2[3];
//        vertices[0] = new Vector2(20, 0);
//        vertices[1] = new Vector2(0, 40);
//        vertices[2] = new Vector2(0, 0);
//        polygonShape.set(vertices);
//        factory.setShape(polygonShape);
//        factory.setRestitution(0.3F);
//        factory.setType(BodyDef.BodyType.DynamicBody);
//        Body body = factory.getBody();
//        body.setTransform(0,0,0);

//        addListener(new ClickListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                MouseJointDef def = new MouseJointDef();
//                def.bodyA = ;
//                def.bodyB = hitBody;
//                def.collideConnected = true;
//                def.target.set(testPoint.x, testPoint.y);
//                def.maxForce = 1000.0f * hitBody.getMass();
//
//                mouseJoint = (MouseJoint)world.createJoint(def);
//                hitBody.setAwake(true);
//                return super.touchDown(event, x, y, pointer, button);
//            }
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                super.touchDragged(event, x, y, pointer);
//            }
//        });
//        Array<Fixture> fixtureList = body.getFixtureList();
//
//        com.kangwang.world.Box2DFactory factory1 = new Box2DFactory();
//        factory1.setSize(10,100);
//        Body body = factory1.getBody();
//        factory1.reset();
//
//        com.kangwang.world.Box2DFactory factory2 = new Box2DFactory();
//        factory2.setSize(10,100);
//        factory2.setPosition(new Vector2(40,0));
//        Body body2 = factory2.getBody();
//        factory2.reset();

//        com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage bodyImage = new com.kangwang.example.OneBodyMuFixture.bodyimage.BodyImage(new TextureRegion(new Texture("2.png")));
//        bodyImage.setPosition(10,60);
//        Body box2DImage = bodyImage.createBox2DImage();
//        bodyImage.getBody().setFixedRotation(false);
//        addActor(bodyImage);


//        PolygonShape polygonShape = new PolygonShape();
//        Vector2[] vertices = new Vector2[3];
//        vertices[0] = new Vector2(20, 0);
//        vertices[1] = new Vector2(0, 40);
//        vertices[2] = new Vector2(0, 0);
//        polygonShape.set(vertices);
//        factory.setPosition(new Vector2(10,100));
//        factory.setShape(polygonShape);
//        factory.setRestitution(0.3F);
//        factory.setType(BodyDef.BodyType.DynamicBody);
//        Body body = factory.getBody();
//
//        PrismaticJointDef def = new PrismaticJointDef();
//        def.initialize(body2,body,new Vector2(10,100),new Vector2(20,100));
//        Constant.world.createJoint(def);


    }



    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
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

    static final int e_columnCount = 5;
    static final int e_rowCount = 16;

    Body m_bullet;
    Body[] m_bodies = new Body[e_rowCount * e_columnCount];
    int[] m_indices = new int[e_rowCount * e_columnCount];


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

    public void rebound(){
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
                bd.position.set(10.0f + 3.0f * i, 20.0f);

                Body body = Constant.world.createBody(bd);
                fd.restitution = restitution[i];
                body.createFixture(fd);
            }

            shape.dispose();
        }
    }

    public void gavity1(){
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

//        float xs[] = {0, -10, -5, 5, 10};
        float xs[] = {10, 10, 10, 10, 10};

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


                bd.position.set(xs[j], 0.752f + 1.54f * i);
                Body body = Constant.world.createBody(bd);
                body.createFixture(fd);
            }

            shape.dispose();
        }

        m_bullet = null;
    }

    public void temp(){
        {
            BodyDef bd = new BodyDef();
            Body ground = Constant.world.createBody(bd);
            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(-40, 0), new Vector2(40, 0));
            ground.createFixture(shape, 0);
            shape.dispose();
        }

//        CircleShape shape = new CircleShape();
//        shape.setRadius(1.0f);

//        FixtureDef fd = new FixtureDef();
//        fd.shape = shape;
//        fd.density = 1.0f;
//        fd.friction = 0.3f;

        for (int j = 0; j < e_columnCount; j++) {
//            PolygonShape shape = new PolygonShape();
//            shape.setAsBox(0.5f, 0.5f);
//            FixtureDef fd = new FixtureDef();
//            fd.shape = shape;
//            fd.density = 1.0f;
//            fd.friction = 0.3f;
            CircleShape shape = new CircleShape();
            shape.setRadius(1.0f);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 1.0f;
            fd.friction = 0.3f;
            for (int i = 0; i < e_rowCount; i++) {
                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.DynamicBody;
                bd.position.set(10, 0.752f + 1.54f * i);
                Body body = Constant.world.createBody(bd);
                body.createFixture(fd);
            }
            shape.dispose();
        }
        m_bullet = null;
    }

    public void gavity2(){
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

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 1.0f;
            fd.friction = 0.3f;
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < e_count; i++) {
                    BodyDef bd = new BodyDef();
                    bd.type = BodyDef.BodyType.DynamicBody;
                    bd.position.set(10, 4.0f + 6.0f * i);
                    Body body = Constant.world.createBody(bd);
                    body.createFixture(fd);
                }
            }
//                 for (int i = 0; i < e_count*2; i++) {
//                    BodyDef bd = new BodyDef();
//                    bd.type = BodyDef.BodyType.DynamicBody;
//                    bd.position.set(10, 4.0f + 6.0f * i);
//                    Body body = Constant.world.createBody(bd);
//                    body.createFixture(fd);
//
//
//                }
        }
    }
}
