package wk.box.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import wk.box.game.Box2dGame;
import wk.box.game.screen.utils.Box2dUtils;

public class GameView extends Group {
    Input input;
    Box2dUtils utils;
    Vector2 gravity = new Vector2();
    public GameView(){
        input = Gdx.input;

        utils = new Box2dUtils();
        utils.createBox(0,63,1,62,true);
        utils.createBox(36,0,36,1,true);
        utils.createBox(72,63,1,62,true);
        utils.createBox(0,126,72,1,true);
        utils.radis(10,10,2,false);
//        utils.radis(10,10,2,false);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println();
        float accelerometerX = input.getAccelerometerX();
        float accelerometerY = input.getAccelerometerY();
        float accelerometerZ = input.getAccelerometerZ();
        System.out.println(accelerometerX +".  "+accelerometerY+" .  "+accelerometerZ);
        Box2dGame.world.getGravity().set(accelerometerX,accelerometerY);
        Box2dGame.world.setGravity(gravity.set(-accelerometerX,-accelerometerY));
    }
}
