package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * 鼠标点击之后，body消失
 */
public class Game02 extends Group {
    private Array<Body> gameArray = new Array<>();
    private Utils utils;
    private Body box;
    private boolean down ;
    public Game02(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        utils.createBox(Constant.width/2,0,2,10,true);
        utils.createBox(Constant.width/2,11,30,2,false);
        createBody();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//              点击任意地方消失
//                if (box == null)return;
//                Constant.world.destroyBody(box);
//                box = null;
//                System.out.println("======>>>>>>>>>>>>>");


//                点击销毁指定的
                System.out.println("==>>>>");
                Constant.world.QueryAABB(callback,x-10,y-10,x+10,y+10);
            }
        });
//        Constant.world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                System.out.println("begin");
////                box.setActive(false);
//
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//                System.out.println("end");
////                if (down){
////                    createBody();
////                }
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {
//                System.out.println("pre");
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {
//                System.out.println("post");
//            }
//        });

//        范围内有fixture的时候就调用
        callback = new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                System.out.println("====>>>>");
                Body body = fixture.getBody();
                Constant.world.destroyBody(body);
                return false;
            }
        };
    }

    private QueryCallback callback;

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 61, 2, 2, false);
        down = false;
        box.setLinearVelocity(vector2);
    }

    private Vector2 vector2 = new Vector2(8,0);

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
//        if (!down) {
//            box.applyForceToCenter(new Vector2(0, box.getMass() * 10.0F), true);
//            if (box.getPosition().x > Constant.width - 2){
//                vector2.set(-8,0);
//                box.setLinearVelocity(vector2);
//            }else if (box.getPosition().x < 2){
//                vector2.set(8,0);
//                box.setLinearVelocity(vector2);
//            }
//        }else {
//            if (box.getLinearVelocity().x!=0){
//                box.setLinearVelocity(new Vector2(0,0));
//            }
//        }
//        if (!box.isAwake()){
//            createBody();
//        }
    }
}
