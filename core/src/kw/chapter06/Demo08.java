package kw.chapter06;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 *  滑轮
 *
 *   这个写完了，有点蒙蔽
 */
public class Demo08 extends Group {
    private Utils utils;
    private Body box;
    public Demo08(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
//        Body box = utils.createBox(3, 50, 1, 1, true);

        Body box1 = utils.createBox(3, 50, 1, 1, false);

        Body box2 = utils.createBox(10, 50, 1, 2, false);

//        Body box3 = utils.createBox(3, 50, 1, 1, true);
//
//        Body box4 = utils.createBox(3, 50, 1, 1, true);


        PulleyJointDef def = new PulleyJointDef();
        def.initialize(box1,box2,
                box1.getWorldVector(new Vector2(box1.getPosition().x ,box1.getPosition().y+10))
                ,box2.getWorldVector(new Vector2(box2.getPosition().x ,box2.getPosition().y+10)),

                box1.getWorldVector(new Vector2(box1.getPosition().x ,box1.getPosition().y+70))
                ,box2.getWorldVector(new Vector2(box2.getPosition().x ,box2.getPosition().y+70)),

                30


                );
        Constant.world.createJoint(def);
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