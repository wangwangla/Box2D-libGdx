package com.kangwang.pinghengche;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.word.Constant;

public class PhcView extends Group {
    private Array boundaries = new Array();
    private Pendulum pendulum;
    private float targetX;
    private Array boxes = new Array();
    public PhcView(){
        //地板
        boundaries.add(new Boundary(0, 0,Constant.width,10));

        pendulum = new Pendulum(Constant.width/2,Constant.hight/2);
//        targetX = Constant.width/2;
//
//        boxes.add(new Box(25,0,25,25));
//        boxes.add(new Box(25,0,25,25));
//
//        Pid pid = new Pid(25, 20, 20);
//        spring = new Spring();
//        Filter filter = new Filter();
//        Debug debug = new Debug();
    }
    Spring spring;
    public float rectify(float x,float minValue,float mxValue){
        if (x > mxValue) return mxValue;
        if (x < minValue) return minValue;
        return x;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        spring.updata(Gdx.input.);

        Constant.world.step(1/60f, 6, 2);
        Constant.renderer.render(Constant.world,Constant.combined);

        pendulum.execteInput();
    }
}
