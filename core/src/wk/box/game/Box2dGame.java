package wk.box.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wk.box.game.screen.GameScreen;


public class Box2dGame extends Game {
    public static float worldWidth;
    public static float worldHeight;
    public static Viewport viewport;
    public static Batch batch;
    public static World world;
    public static Box2DDebugRenderer renderer;
    public static Matrix4 combined;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera(72,128);
        viewport = new ExtendViewport(720,1280);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new CpuPolygonSpriteBatch();
        world = new World(new Vector2(0,0F),true);
        renderer = new Box2DDebugRenderer();
        setScreen(new GameScreen());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
        viewport.apply();
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        camera.update();
        camera.position.x = 72 / 2;
        camera.position.y = 128 / 2;
        combined = camera.combined;
    }
}
