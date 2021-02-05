package kw.chapter01;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kangwang.world.Constant;

import kw.utils.Utils;

/**
 * 重力作用下，发生形变  刚体的恢复过程
 *
 * private float timeStemp = 1/60F;    //每次之间的延迟，采样点   值太小就会慢放   值太大就会快放
 *
 * 时间步中迭代的次数
 *
 * private int velocityIterations = 6;    //碰撞之后，需要进行校正，值越大改正越块
 * private int psoitionIterations = 2;     //形变  值越大  恢复越块

 */
public class Demo02 extends Group {

    //值为6又穿透效果
    private float timeStemp = 1/600F;
    private int velocityIterations = 6;
    private int psoitionIterations = 2;

    public Demo02(){
        kw.utils.Utils utils = new Utils();
        utils.createBox(10,5, Constant.width-10,5,true);
//        utils.createRedis();
        for (int i = 0; i < 10; i++) {
            utils.createRadio(20+i,35+i*5, 5,5,false);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Constant.world.step(timeStemp, velocityIterations, psoitionIterations);
        Constant.renderer.render(Constant.world,Constant.combined);
    }
}
