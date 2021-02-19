package kw.chapter06;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 *  滑轮
 *
 *   车轮   小车
 */
public class Demo11 extends Group {
    private Utils utils;
    private Body box;
    Body box1;
    Body box2;
    Body body;
    public Demo11(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
         utils.createBox(33, 4, Constant.width, 1, true);

//        MotorJointDef def = new MotorJointDef();
//        def.initialize(box1,box2);
//        Constant.world.createJoint(def);
        body = utils.createBox(13+10,14,20,5,false);
        box2 = utils.createRadio(10,6,false);
        box1 = utils.createRadio(30,6,false);
        WheelJointDef def = new WheelJointDef();
        def.initialize(box2,body,box2.getPosition(),new Vector2(0,1));
        def.frequencyHz = 3;
        def.maxMotorTorque = 100;
        def.motorSpeed = 100;
        Constant.world.createJoint(def);


        def = new WheelJointDef();
        def.initialize(box1,body,box1.getPosition(),new Vector2(0,1));
        def.frequencyHz = 3;
        def.maxMotorTorque = 100;
        def.motorSpeed = 100;
        Constant.world.createJoint(def);


        utils.createBox(63,5,5,2,true);
        RevoluteJointDef de = new RevoluteJointDef();
        de.initialize(body,box1,new Vector2(box1.getWorldVector(box1.getPosition())));
        Constant.world.createJoint(de);
        RevoluteJointDef de1 = new RevoluteJointDef();
        de1.initialize(body,box2,new Vector2(box2.getWorldVector(box2.getPosition())));
        Constant.world.createJoint(de1);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                utils.createBox(x,y,10,10,false);
            }
        });
    }

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 120, 2, 2, false);
        box.setUserData("box");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            box1.setLinearVelocity(new Vector2(5,0));
            box2.setLinearVelocity(new Vector2(5,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            box1.setLinearVelocity(new Vector2(-5,0));
            box2.setLinearVelocity(new Vector2(-5,0));
        }
    }
}