package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Game08 extends Group {
    private Utils utils;
    private Body box;
    Array<bean> array = new Array();
    Vector2 prePoint = new Vector2();

    public Game08(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        utils.createBox(50,90,1,1,false);


        BodyDef bodyRequest = new BodyDef();
        bodyRequest.type = BodyDef.BodyType.StaticBody;
        bodyRequest.position.set(0 ,0);//记得米和像素的转换关系
        //2.Box2D世界工厂更具需求创建createBody()生产刚体
        Body body=Constant.world.createBody(bodyRequest);
        //3.创建敢提形状需求b2ShapeDef的子类
        //创建矩形刚体形状需求
        FixtureDef fixtureRequest = new FixtureDef();
        fixtureRequest.density = 3;
        fixtureRequest.friction = 0.3F;
        fixtureRequest.restitution = 0.2F;

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                array.clear();
                prePoint.set(x,y);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                Vector2 curren = new Vector2();
                curren.set(x,y);
                float dst = prePoint.dst(curren);
                if (dst > 1){

                    Vector2 middle = prePoint.add(curren);
                    middle.set(middle.x / 2,middle.y/2);

                    System.out.println(dst);
                    bean b = new bean();
                    b.x=x;
                    b.y=y;
                    b.len = curren.dst(prePoint);
                    b.angle = curren.angle(prePoint);
                    array.add(b);
                    prePoint.set(x,y);
                    fixtureRequest.shape = createDemo(b);
                    body.createFixture(fixtureRequest);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //1.创建刚体需求b2BodyDef

//segment.length /2/ 30, 2 / 30, new b2Vec2(segment.centerX/30, segment.centerY/30), segment.rotation

//                for (Vector2 vector2 : array) {
//                    PolygonShape polygonShape = new PolygonShape();
//                    polygonShape.setAsBox(1,1,vector2,4);
//                    fixtureRequest.shape = polygonShape;
//                    body.createFixture(fixtureRequest);
//                }

                for (bean bean : array) {

                }
            }
        });



    }

    public PolygonShape createDemo(bean bean){
        //设置中心
        //旋转
        PolygonShape polygonShape = new PolygonShape();
//        polygonShape.setAsBox(bean.len,1,new Vector2(bean.x/2,bean.y/2),0);
        polygonShape.setAsBox(bean.len,0.3F,new Vector2(bean.x,bean.y),bean.angle);
        return polygonShape;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }

    class bean{
        float len;
        float x;
        float y;
        float angle;
    }
}