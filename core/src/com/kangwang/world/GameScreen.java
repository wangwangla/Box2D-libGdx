package com.kangwang.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.doc.demo.Demo02;

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
//            keyEvent(keycode);
            return super.keyTyped(character);
        }

        @Override
        public boolean keyUp(int keycode) {
            return super.keyUp(keycode);
        }
    };

//    protected void keyEvent(int keyCode){
//        if (keyCode == Input.Keys.UP){
//
//        }else if (keyCode == Input.Keys.DOWN){
//
//        }else if (keyCode == Input.Keys.LEFT){
//
//        }else if (keyCode == Input.Keys.RIGHT){
//
//        }
//    }

    @Override
    public void show() {
//        GameView view = new GameView();
//        stage.addActor(view);
//        Box2DDemo dDemo = new Box2DDemo();
//        stage.addActor(dDemo);
//        GameView view = new GameView();
//        stage.addActor(view);
//        PhcView view = new PhcView();
//        stage.addActor(view);
//        Demo01 demo01 = new Demo01();
//        stage.addActor(demo01);

        Demo02 demo02 = new Demo02();
        stage.addActor(demo02);
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
