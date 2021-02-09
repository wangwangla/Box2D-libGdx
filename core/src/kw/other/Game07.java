package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * 将多个刚体链接起来
 */
public class Game07 extends Group {
    private Utils utils;
    private Body box;
    public Game07(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        Body box = utils.createBox(Constant.width/2, 0, Constant.width/2-10, 13, true);
//        Body box2 = utils.createBox(Constant.width - 3, 14, 2, 2, true);
//        Body box1 = utils.createBox(15, 14, 2, 2, false);

        /**
         * 看作一个轴，是的二者绕着轴转动
         */
//        RevoluteJointDef def = new RevoluteJointDef();
//        def.initialize(box2,box1,new Vector2(15,22.1F));
//        def.enableMotor = false;
//        def.maxMotorTorque = 1000;
//        Constant.world.createJoint(def);
//        box1.setLinearVelocity(new Vector2(5,0));
        Array<Body> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            Body boxxx = utils.createBox(15+6*i, 44, 2, 2, false);
            array.add(boxxx);
        }

        Body lastBody = null;
        for (Body body : array) {
            if (lastBody == null) {
                lastBody = body;
                continue;
            }
            RevoluteJointDef def = new RevoluteJointDef();
            def.initialize(lastBody,body,new Vector2(box.getWorldVector(body.getPosition())));
            def.enableMotor = false;
            def.maxMotorTorque = 1000;
            Constant.world.createJoint(def);
            lastBody = body;
        }

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