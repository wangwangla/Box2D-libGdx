package kw.chapter08;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo02 extends Group {
    B2DSeparator separator;
    private float timeStemp = 1/60F;
    private int velocityIterations = 6;
    private int psoitionIterations = 2;

    public Demo02(){
        separator = new B2DSeparator();
        Utils utils = new Utils();
        Body box = utils.createBox(20, 0, 20, 2, true);
        Array<Body> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            Body box1 = utils.createBox(10 + i * 5, 26+i*2, 2, 2, false);
            array.add(box1);
        }
        Body tem = null;
        for (Body body : array) {
            if (tem == null){
                tem = body;
                continue;
            }
            WeldJointDef def = new WeldJointDef();
            Vector2 position = tem.getPosition();
            position.x=position.x + 6;
            def.frequencyHz = 30;
            def.dampingRatio = 0.3F;
            def.initialize(body,tem,tem.getPosition());
            Constant.world.createJoint(def);
            tem = body;
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
