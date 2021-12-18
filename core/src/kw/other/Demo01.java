package kw.other;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Group;
import kw.chapter08.B2DSeparator;
import com.kangwang.world.Constant;

import kw.utils.Utils;

public class Demo01 extends Group {
    B2DSeparator separator;
    private float timeStemp = 1/60F;
    private int velocityIterations = 6;
    private int psoitionIterations = 2;

    public Demo01(){
        separator = new B2DSeparator();
        Utils utils = new Utils();
        Body box = utils.createBox(20, 20, 10, 10, true);
        Vector2[] vector2s = {
                new Vector2(10,10),
                new Vector2(20,10),
                new Vector2(20,20),
                new Vector2(15,15),
                new Vector2(10,20)
        };
        separator.separate(box,utils.fixtureDef,vector2s);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
