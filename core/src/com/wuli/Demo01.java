package com.wuli;

import com.badlogic.gdx.scenes.scene2d.Group;

import wk.box.util.MyBox2DUtils;

public class Demo01 extends Group {
    public Demo01(){
        MyBox2DUtils myBox2DUtils = MyBox2DUtils.getNomalInstance();
        myBox2DUtils.createRedis();
    }
}
