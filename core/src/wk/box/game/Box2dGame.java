package wk.box.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import finnstr.libgdx.liquidfun.ParticleDebugRenderer;
import finnstr.libgdx.liquidfun.ParticleSystem;
import finnstr.libgdx.liquidfun.ParticleSystemDef;
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
    public static ParticleDebugRenderer mParticleDebugRenderer;

    public static float WorldBoxWidth = 12;
    public static float WorldBoxHight = 16;

    public void createRender(){
        ParticleSystemDef systemDef = new ParticleSystemDef();
        systemDef.radius = 6f * 1/120.0F;
        systemDef.dampingStrength = 0.6f;
        ParticleSystem mParticleSystem = new ParticleSystem(Box2dGame.world, systemDef);
        mParticleDebugRenderer = new ParticleDebugRenderer(new Color(0, 1, 0, 1), mParticleSystem.getParticleCount());
    }

    @Override
    public void create() {
        camera = new OrthographicCamera(WorldBoxWidth,WorldBoxHight);
        viewport = new ExtendViewport(720,1280);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new CpuPolygonSpriteBatch();
        world = new World(new Vector2(0,-9.8F),true);
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
        camera.position.x = WorldBoxWidth / 2;
        camera.position.y = WorldBoxHight / 2;
        combined = camera.combined;
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }
}
