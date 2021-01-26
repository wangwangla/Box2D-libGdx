package com.kangwang.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

class GameScreen implements Screen {
    private Stage stage;
    private InputMultiplexer inputMultiplexer;
    public GameScreen(MainGame mainGame) {
        inputMultiplexer = new InputMultiplexer();
        stage =  new Stage(mainGame.getViewport(),mainGame.getBatch());
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keyListener);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private InputAdapter keyListener = new InputAdapter(){
        private int keycode;
        @Override
        public boolean keyDown(int keycode) {
            this.keycode = keycode;
            return super.keyDown(keycode);
        }

        @Override
        public boolean keyTyped(char character) {
            keyEvent(keycode);
            return super.keyTyped(character);
        }

        @Override
        public boolean keyUp(int keycode) {
            return super.keyUp(keycode);
        }
    };

    protected void keyEvent(int keyCode){

    }

    @Override
    public void show() {
        stage = new Stage();
        GameView view = new GameView();
        stage.addActor(view);
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