package kw.other;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * 冲量
 */
public class Game04 extends Group {
    private Utils utils;
    private Body box;
    private boolean down ;
    public Game04(){
        setSize(Constant.width,Constant.hight);
        utils = new Utils();
        //base
        utils.createBox(Constant.width/2,0,30,2,true);
        createBody();


        for (int i = 0; i < 10; i++) {
            box = utils.createBox(Constant.width-20, 20+i*2, 2, 2, false);
            box.setUserData("box");
        }
    }

    public void createBody(){
        //活动
        box = utils.createBox(Constant.width / 2 - 14, 20, 2, 2, false);
        down = false;
        box.setLinearVelocity(vector2);
        box.setUserData("box");
    }

    private Vector2 vector2 = new Vector2(40,0);

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(1/60F,40,40);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
