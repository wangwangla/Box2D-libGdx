package kw.chapter06;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo06 extends Group {
    private Utils utils;
    private Body box;
    public Demo06(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        Body box = utils.createBox(3, 0, 100, 30, true);
        Array<Body> array = new Array<>();
        for (int i = 0; i < 2; i++) {
            Body boxxx = utils.createBox(15+6*i, 44, 2, 2, false);
            array.add(boxxx);
        }

        Body lastBody = null;
        for (Body body : array) {
            if (lastBody == null) {
                lastBody = body;
                continue;
            }

            /**
             * 各自在自己的节点上进行旋转，节点的距离不会发生改变
             */
            DistanceJointDef def = new DistanceJointDef();
//            def.initialize(lastBody,body,new Vector2(lastBody.getWorldVector(body.getPosition())),new Vector2(box.getWorldVector(body.getPosition())));
            def.initialize(lastBody,body,new Vector2(1,3),new Vector2(3,4));
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