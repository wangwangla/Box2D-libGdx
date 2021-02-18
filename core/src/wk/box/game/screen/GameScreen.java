package wk.box.game.screen;

import wk.box.game.Box2dGame;
import wk.box.game.screen.base.BaseScreen;

public class GameScreen extends BaseScreen {
    public GameScreen(){
//        Box2dUtils utils = new Box2dUtils();
//        utils.radis(0,  0,2,false);
    }

    @Override
    public void show() {
        super.show();
        GameView view = new GameView();
        stage.addActor(view);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        Box2dGame.world.step(1/60.0F,6,3);
        Box2dGame.renderer.render(Box2dGame.world,Box2dGame.combined);
    }
}
