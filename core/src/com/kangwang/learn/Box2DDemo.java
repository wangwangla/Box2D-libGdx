package com.kangwang.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kangwang.world.Constant;

public class Box2DDemo extends Group {
    Body body;
    public Box2DDemo(){
        setSize(Constant.width,Constant.hight);
        BodyDef bground = new BodyDef();
        bground.position.set(0,0);
        bground.type = BodyDef.BodyType.StaticBody;
        Body bg = Constant.world.createBody(bground);

        FixtureDef bgDef = new FixtureDef();
        PolygonShape bgShape = new PolygonShape();
        bgShape.setAsBox(Constant.width,10);
        bgDef.shape = bgShape;
        bg.createFixture(bgDef);


        Body body1 = createBody(800);
        Body body2 = createBody(188);

//        Re jointDef = new DistanceJointDef();
//        jointDef.bodyA = body1;
//        jointDef.bodyB = body2;
//
//        jointDef.initialize(body1,body2,new Vector2(Constant.width/2-100,500),new Vector2(Constant.width/2,500));
//
//        jointDef.localAnchorA.set(100,100);
//        jointDef.localAnchorB.set(100,100);

//        Constant.world.createJoint(jointDef);

//        Body body1 = createBody(800);
//        Body body2 = createBody(188);
//
//        DistanceJointDef  jointDef = new DistanceJointDef();
//        jointDef.bodyA = body1;
//        jointDef.bodyB = body2;
//
//        jointDef.initialize(body1,body2,new Vector2(Constant.width/2-100,500),new Vector2(Constant.width/2,500));
////
////        jointDef.localAnchorA.set(100,100);
////        jointDef.localAnchorB.set(100,100);
//
//        Constant.world.createJoint(jointDef);





//        Body body1 = createBody(800);
//        Body body2 = createBody(188);
//
//        //关节
//        WeldJointDef def = new WeldJointDef();
//        def.collideConnected = false;
//        def.initialize(body1,body2,new Vector2(0,100));
//        Constant.world.createJoint(def);







        //一个关节，用来链接鼠标和拖动的物体
//        MouseJointDef def = new MouseJointDef();
//        def.bodyA :一个
//        def.bodyB :另一个
//        def.maxForce : 限制鼠标关节上的最大的力 这里通常需要乘以刚体的
//        质量multiplier * mass * gravity


//        QueryCallback callback = new QueryCallback() {
//            @Override
//            public boolean reportFixture(Fixture fixture) {
//                return false;
//            }
//        }


//        MouseJoint joint = new MouseJoint(Constant.world,);




//        int x = Gdx.input.getX();
//        int y = Gdx.input.getY();
//
//        Vector2 position = new Vector2();



////        得到身体的总质量。 * @返回质量，通常以千克（kg）为单位。
//        body.getMass();
//
////        获取身体的质量数据。 * @返回包含身体质量，惯性和中心的结构
//        body.getMassData();
//
//


//        createCir();


//        createBody(300);


//        body = createBody(10);

//        TextureBody body = new TextureBody();
//        addActor(body);


        //
//        for (int i = 0; i < 10; i++) {
//            double random = Math.random();
//            random = random * 10;
//            createBody((float) random);
//        }

//        addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                int i = 500 + Math.round(100);
//                createBody(0);
//            }
//        });
    }

    public void createCir(){
        //1.创建刚体需求b2BodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(230, 130);//记得米和像素的转换关系
        //2.Box2D世界工厂更具需求创建createBody()生产刚体
        body=Constant.world.createBody(bodyDef);
        //3.创建敢提形状需求b2ShapeDef的子类

        /*	详细说明我们的需求
            创建多个b2Shape需求
            然后b2Body刚体工厂根据需求createShape生产形状
        */
        //把像素转换成Box2D里的米
//        var b2Width:Number = width / 2 / 30;
//        var b2height:Number = height / 2 / 30;
//        var b2Radius:Number = radius / 2 / 30;
//        var offsetX:Number = b2Width - b2Radius;
//        var offsetY:Number = b2height - b2Radius;

        //------------------------------------
        //     首先创建两个矩形，我分别把它们高和宽减去了radius，留给圆角用，你可以注释掉下面创建圆角的代码，看看效果
        FixtureDef shapeRequest = new FixtureDef();
        shapeRequest.density = 3;
        shapeRequest.friction = 0.3F;
        shapeRequest.restitution = 0.2F;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(100,100);

        FixtureDef bgDef = new FixtureDef();
        bgDef.shape = shape;
        body.createFixture(bgDef);


        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.position.set(230, 130);//记得米和像素的转换关系
        //2.Box2D世界工厂更具需求创建createBody()生产刚体
        body=Constant.world.createBody(bodyDef1);

        FixtureDef shapeRequest1 = new FixtureDef();
        shapeRequest1.density = 3;
        shapeRequest1.friction = 0.3F;
        shapeRequest1.restitution = 0.2F;

        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(50,50);

        FixtureDef bgDef1 = new FixtureDef();
        bgDef1.shape = shape1;
        body.createFixture(bgDef1);

        Image image = new Image(new Texture("1.png"));
        image.setSize(100,100);

        body.setUserData(image);


//
//        //创建两个矩形
//        shapeRequest.SetAsOrientedBox(b2Width, b2height - b2Radius);
//        body.CreateShape(shapeRequest);
//        shapeRequest.SetAsOrientedBox(b2Width - b2Radius, b2height);
//        body.CreateShape(shapeRequest);
        //------------------------------------
        //       然后，分别在四个角上创建四个圆形，实现圆角效果
//        var circleRequest:b2CircleDef = new b2CircleDef();
//        circleRequest.density = 3;
//        circleRequest.friction = 0.3;
//        circleRequest.restitution = 0.3;
//        circleRequest.radius = b2Radius;
//
//        circleRequest.localPosition = new b2Vec2(-offsetX, -offsetY);
//        body.CreateShape(circleRequest);
//        circleRequest.localPosition = new b2Vec2(offsetX, -offsetY);
//        body.CreateShape(circleRequest);
//        circleRequest.localPosition = new b2Vec2(-offsetX, offsetY);
//        body.CreateShape(circleRequest);
//        circleRequest.localPosition = new b2Vec2(offsetX, offsetY);
//        body.CreateShape(circleRequest);
//
//        body.SetMassFromShapes();

    }

    public Body createBody(float y){
        System.out.println("create");
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(Constant.width/2,y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = Constant.world.createBody(bodyDef);

        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        Vector2 [] vertices = new Vector2[4];
        vertices[0] = new Vector2();
        vertices[0].set(50, 50);
        vertices[1] = new Vector2();
        vertices[1].set(200, 50);
        vertices[2] = new Vector2();
        vertices[2].set(100, 200);
        vertices[3] = new Vector2();
        vertices[3].set(200, 200);
        shape.set(vertices);

        def.shape = shape;
        body.createFixture(def);
        return body;
    }

    public Body createBodyVector(float y){
        System.out.println("create");
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(Constant.width/2+y,y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = Constant.world.createBody(bodyDef);

        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10,10);
        def.shape = shape;
        body.createFixture(def);
        return body;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60f, 6, 2);
        Constant.renderer.render(Constant.world,Constant.combined);

        //给一个力
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            body.applyForce(new Vector2(100,0), body.getWorldCenter(),true);
        }

        //叠加
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            body.applyLinearImpulse(new Vector2(-100,0), body.getWorldCenter(),true);
        }

        //设置
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.setLinearVelocity(new Vector2(-100,0));
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        if (body.getUserData()!=null){
//            Object userData = body.getUserData();
//            if (userData instanceof Image) {
//                Image userData1 = (Image) userData;
//                userData1.draw(batch,parentAlpha);
//                userData1.setPosition(body.getPosition().x,body.getPosition().y, Align.center);
//            }
//        }
    }
}
