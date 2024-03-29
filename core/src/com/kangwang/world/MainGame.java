package com.kangwang.world;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kangwang.asset.FontResource;
import com.kangwang.world.screen.LoadingScreen;

public class MainGame extends Game {
    private AssetManager assetManager;
    private Viewport viewport;
    private Batch batch;
    public static FontResource resource;

    @Override
    public void create() {
        Box2D.init();
        Gdx.input.setCatchBackKey(true);
        assetManager = new AssetManager();
        viewport = new ExtendViewport(62,128);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new CpuPolygonSpriteBatch();
        Vector2 vector2 = new Vector2(0,-10.0F);
        Constant.world = new World(vector2,true);
        Constant.renderer = new Box2DDebugRenderer();
        resource = new FontResource(assetManager);
        assetManager.finishLoading();
        resource.getFont();
        setScreen(new LoadingScreen(this));
    }

    public Batch getBatch() {
        return batch;
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        viewport.update(width,height);
        viewport.apply();
        Constant.combined = viewport.getCamera().combined;
        Constant.width = viewport.getWorldWidth();
        Constant.hight = viewport.getWorldHeight();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
                (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        super.render();
    }
}