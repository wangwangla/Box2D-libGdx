package com.kangwang.pinghengche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.kangwang.WorldConstant;
import com.kangwang.word.Constant;

public class PhcView extends Group {
    private Pendulum pendulum;
    private Array<Boundary> array;
    private float targetX;
    private Pid pid;
    private Spring spring;
    private Filter filter;
    private Debug  debug;
    private Array<Body> aa  =new Array<>();

    public PhcView(){
        //地板
        array = new Array<>();
        array.add(new Boundary(Constant.width, 2,Constant.width,10));
        aa.add(new Boundary(0, 12,10,10).getBody());
        aa.add(new Boundary(0, 22,10,10).getBody());
        pendulum = new Pendulum(Constant.width/2,Constant.hight/2);
        targetX = WorldConstant.convert(Constant.width/2.0f);
        pid = new Pid(25, 20, 20);
        spring = new Spring();
        filter = new Filter();
        debug  = new Debug();
    }

    public float rectify(float x,float minValue,float maxValue) {
        if (x > maxValue) return maxValue;
        if (x < minValue) return minValue;
        return x;
    }

    private float time = 0;
    private Matrix4 combined = new Matrix4();
    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60f, 10, 10);
        combined.set(Constant.combined);
        combined.scale(WorldConstant.PPM,
                WorldConstant.PPM,
                WorldConstant.PPM);


        Constant.renderer.render(Constant.world,combined);
        time += delta;
        if (time > 4) {
        spring.update(Gdx.input.getX(), Gdx.input.getX());


        Vector2 pos = pendulum.getPosition();
        Vector2 velocity = pendulum.getVelocity();
        filter.push(velocity.x);

        float smoothVelocity = filter.getValue();

        float predictPos = pos.x + 15 * smoothVelocity;
        if((predictPos - targetX) * (pos.x-targetX) < 0) {
            predictPos = pos.x + 100 * smoothVelocity;
        }




        float targetAngle = rectify(-(predictPos-targetX)/Constant.width*3.1415f/5.0f,
                -0.25f, 0.25f);
        float nowAngle = -pendulum.getAngleRadians();
        if(
                Math.abs(10 * smoothVelocity) > 60 &&
                        (predictPos - targetX) * (pos.x-targetX) >= 0 &&
                        (predictPos - targetX) * smoothVelocity < 0
        ) {
            targetAngle = 0;
        }
        pid.setcError(targetAngle-nowAngle);
        pid.step(delta);
        float speed = -pid.getOutput();
        pendulum.setMotorSpeed(speed*100);
        }
        pendulum.display();
    }

    public Array<Body> getElememt() {
        Array<Body> elements = pendulum.getElements();
        return elements;
    }

    public void userTouch(float x, float y) {

        for (Body body : aa) {
//            spring.bind(x,y,body);
        }
        targetX = x;
        Vector2 pos = pendulum.getPosition();
        System.out.println(pos.x +"     "+targetX);
    }
}
