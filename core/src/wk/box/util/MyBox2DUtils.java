package wk.box.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kangwang.world.Constant;

public class MyBox2DUtils {
    private World world;
    private Box2DDebugRenderer renderer;
    private static MyBox2DUtils instance;
    private MyBox2DUtils(){
        world = new World(new Vector2(0,-9.8F),true);
        renderer = new Box2DDebugRenderer();
    }

    private MyBox2DUtils(Vector2 vector2){
        world = new World(vector2,false);
        renderer = new Box2DDebugRenderer();
    }

    private MyBox2DUtils(Vector2 vector2, boolean doSleep){
        world = new World(vector2,doSleep);
        renderer = new Box2DDebugRenderer();
    }

    public synchronized static MyBox2DUtils getNomalInstance(){
        if (instance == null) {
            instance = new MyBox2DUtils();
        }
        return instance;
    }

    public synchronized static MyBox2DUtils getAgravityInstance(){
        if (instance == null) {
            instance = new MyBox2DUtils(Vector2.Zero,false);
        }
        return instance;
    }

    public synchronized static MyBox2DUtils getCustomInstance(Vector2 vector2, boolean doSleep){
        if (instance == null) {
            instance = new MyBox2DUtils(vector2,doSleep);
        }
        return instance;
    }

    public Box2DDebugRenderer getRenderer() {
        return renderer;
    }

    public World getWorld() {
        return world;
    }

    public void update(){
        if (world == null) throw new NullPointerException("world is null !");
            world.step(1/60f, 6, 2);
        if (renderer == null) throw new NullPointerException("renderer is null !");
        renderer.render(Constant.world,Constant.combined);
    }

    public Body createBox(float posX,float posY,float boxWidth,float boxHeight,boolean isStatic){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = false;
        bodyDef.position.set(posX,posY);
        Body body = Constant.world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(boxWidth, boxHeight);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 3;
        fixtureDef.friction = 0.3F;
        fixtureDef.restitution = 0.2F;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        return body;
    }

    public void createRedis(){
        for (int i= 0; i < 50; i++) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.fixedRotation = false;
            //定义形状需求
            PolygonShape shape = new PolygonShape();
            FixtureDef def = new FixtureDef();
            //形状的密度、摩擦系数、反馈
            def.density = 0;
            def.friction = 0.3F;
            def.restitution = 0.2F;
            def.shape = shape;
            //计算每个线段的角度、坐标
            float angle = (float)(i/100.0F *Math.PI*2);
            float bx = (float) (100 * Math.cos(angle));
            float by = (float) (100 * Math.sin(angle));
            //创建有方向的矩形刚体，合成总的圆形刚体
            shape.setAsBox(5/30, 100/30, new Vector2(bx/30,by/30),angle);
            bodyDef.position.set(bx+100,by+1000);
            Body body = Constant.world.createBody(bodyDef);
            //4.b2Body刚体工厂根据需求createShape生产形状
            body.createFixture(def);
        }
    }
}
