package kw.chapter06;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo14  extends Group {
    private Utils utils;
    private Body box;
    Body box1;
    Body box2;
    Body body;
    public Demo14(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        utils.createBox(33, 0, Constant.width, 1, true);
        body = utils.createBox(Constant.width / 2,10,3,20,true);
        box1 = utils.createBox(Constant.width / 2,30,30,3,false);

        RevoluteJointDef def = new RevoluteJointDef();
        def.initialize(body,box1,new Vector2(box1.getPosition()));
        Constant.world.createJoint(def);

        box2 = utils.createBox(Constant.width / 2 - 10,50,2,3,false);
        WeldJointDef def1 = new WeldJointDef();
        def1.initialize(box1,box2,new Vector2(box2.getPosition()));
        Constant.world.createJoint(def1);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);

    }
}
