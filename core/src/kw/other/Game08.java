package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Segment;
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
    Array<Segment> array = new Array();
    Vector3 prePoint = new Vector3();

    public Game08(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                array.clear();
                prePoint.set(x,y,0);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                Vector3 curren = new Vector3();
                curren.set(x,y,0);
                float dst = prePoint.dst(curren);
                if (dst > 1){
                    System.out.println(dst);
                    array.add(new Segment(prePoint,curren));
                    prePoint.set(x,y,0);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //1.创建刚体需求b2BodyDef
                BodyDef bodyRequest = new BodyDef();
                bodyRequest.type = BodyDef.BodyType.DynamicBody;
                bodyRequest.position.set(20 ,20);//记得米和像素的转换关系
                //2.Box2D世界工厂更具需求创建createBody()生产刚体
                Body body=Constant.world.createBody(bodyRequest);
                //3.创建敢提形状需求b2ShapeDef的子类
                //创建矩形刚体形状需求
                FixtureDef fixtureRequest = new FixtureDef();
                fixtureRequest.density = 3;
                fixtureRequest.friction = 0.3F;
                fixtureRequest.restitution = 0.2F;
//segment.length /2/ 30, 2 / 30, new b2Vec2(segment.centerX/30, segment.centerY/30), segment.rotation

                for (Segment segment : array) {
                    PolygonShape polygonShape = new PolygonShape();

                    /**
                     * length
                     *
                     * 高
                     *
                     *
                     * x,y
                     *
                     *
                     * 角度
                     */

                    polygonShape.setAsBox(segment.len() /2/ 30,
                            2 / 30, new Vector2(segment./30, segment.centerY/30), segment.rotation);
                    fixtureRequest.shape = polygonShape;
                    body.createFixture(fixtureRequest);
                }



                for (Vector3 vector2 : array) {
                    PolygonShape polygonShape = new PolygonShape();
                    polygonShape.setAsBox(1,1,vector2,4);
                    fixtureRequest.shape = polygonShape;
                    body.createFixture(fixtureRequest);
                }
            }
        });

    }

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 20, 2, 2, false);
        box.setUserData("box");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}