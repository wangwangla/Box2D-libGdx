package com.kangwang.example.change;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.chapter08.B2DSeparator;
import kw.utils.Utils;

public class ChangeSize extends Group {
    B2DSeparator separator;
    private float timeStemp = 1/60F;
    private int velocityIterations = 6;
    private int psoitionIterations = 8;
    private Body box;
    private Body box1;
    public ChangeSize(){
        separator = new B2DSeparator();
        Utils utils = new Utils();
        box = utils.createBox(20, 20, 10, 1, true);

        box1 = utils.createBox(20,60,4,1,false);
    }
    Vector2 position;
    public boolean flag = false;
    public void scale(){
        flag = true;
        position = box.getPosition();
        position.y = position.y+9F;
//        Fixture fixture = box.getFixtureList().get(0);
//        PolygonShape shape = (PolygonShape) fixture.getShape();
//        shape.setAsBox(13,12);
//        box.setAwake(true);
//        box1.setAwake(true);
    }
    float xxx = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
        if (flag) {

            if (xxx<0.6F){
                xxx+=delta;
                position.y = position.y+xxx;
            }else {

            }

            box.setTransform(position, box.getAngle());
            box.setAwake(true);
            box1.setAwake(true);
        }
    }
}

