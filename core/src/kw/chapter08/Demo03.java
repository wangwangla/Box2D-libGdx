package kw.chapter08;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo03 extends Group {
    B2DSeparator separator;
    private float timeStemp = 1/60F;
    private int velocityIterations = 6;
    private int psoitionIterations = 2;

    public Demo03(){
        separator = new B2DSeparator();
        Utils utils = new Utils();
        utils.createBox(20, 0, 20, 2, true);
        utils.createBox(20, 50, 2, 30, true);

    }
    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
