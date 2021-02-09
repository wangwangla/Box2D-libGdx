package kw.chapter04;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Group;

import kw.utils.Utils;

public class Demo01 extends Group {
    public Demo01(){
        Utils utils = new Utils();
        Body radio = utils.createRadio(0, 0,  true);
        radio.getFixtureList().get(0);
    }
}
