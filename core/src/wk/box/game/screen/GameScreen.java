package wk.box.game.screen;

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

    }
}
