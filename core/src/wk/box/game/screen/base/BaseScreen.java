package wk.box.game.screen.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import wk.box.game.Box2dGame;

public class BaseScreen implements Screen {
    public Stage stage;
    @Override
    public void show() {
        stage = new Stage(Box2dGame.viewport, Box2dGame.batch);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
