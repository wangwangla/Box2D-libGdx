package wk.box.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

import finnstr.libgdx.liquidfun.ParticleDebugRenderer;
import finnstr.libgdx.liquidfun.ParticleDef;
import finnstr.libgdx.liquidfun.ParticleGroupDef;
import finnstr.libgdx.liquidfun.ParticleSystem;
import finnstr.libgdx.liquidfun.ParticleSystemDef;
import wk.box.game.Box2dGame;
import wk.box.game.screen.utils.Box2dUtils;

public class GameView extends Group {
    private static final float WORLD_TO_BOX = 1/120F;
    Input input;
    Box2dUtils utils;
    Vector2 gravity = new Vector2();
    public GameView(){
        setSize(720,1280);
        input = Gdx.input;
        utils = new Box2dUtils();
        utils.createBox(0,63,1,62,true);
        utils.createBox(16,0,36,1,true);
        utils.createBox(12,63,1,62,true);
        utils.createBox(0,126,72,1,true);
        utils.radis(10,10,2,false);
//        utils.radis(10,10,2,false);
        ParticleSystemDef systemDef = new ParticleSystemDef();
        systemDef.radius = 6f * WORLD_TO_BOX;
        systemDef.dampingStrength = 0.6f;
        mParticleSystem = new ParticleSystem(Box2dGame.world, systemDef);
        mParticleDebugRenderer = new ParticleDebugRenderer(new Color(1, 1, 1, 1), mParticleSystem.getParticleCount());
        createPri();
        crr();
    }

    ParticleSystem mParticleSystem;
    ParticleDebugRenderer mParticleDebugRenderer;
    ParticleGroupDef mParticleGroupDef1;
    public void createPri(){
        ParticleSystemDef systemDef = new ParticleSystemDef();
        systemDef.radius = 8f * WORLD_TO_BOX;
        systemDef.dampingStrength = 0.2f;
        mParticleSystem = new ParticleSystem(Box2dGame.world, systemDef);
        mParticleSystem.setParticleDensity(1.3f);
        //Create a new particlegroupdefinition and set some properties
        //For the flags you can set more than only one
        mParticleGroupDef1 = new ParticleGroupDef();
        mParticleGroupDef1.color.set(1f, 0, 0, 1);
        mParticleGroupDef1.flags.add(ParticleDef.ParticleType.b2_waterParticle);
//        mParticleGroupDef1.position.set(width * (30f / 100f) * WORLD_TO_BOX, height * (80f / 100f) * WORLD_TO_BOX);
        mParticleGroupDef1.position.set(7,7);
        //Create a shape, give it to the definition and
        //create the particlegroup in the particlesystem.
        //This will return you a ParticleGroup instance, but
        //we don't need it here, so we drop that.
        //The shape defines where the particles are created exactly
        //and how much are created
        PolygonShape parShape = new PolygonShape();
        parShape.setAsBox(width * (20f / 100f) * WORLD_TO_BOX / 2f, width * (20f / 100f) * WORLD_TO_BOX / 2f);
        mParticleGroupDef1.shape = parShape;
        mParticleSystem.createParticleGroup(mParticleGroupDef1);
        CircleShape partShape = new CircleShape();
        partShape.setRadius(18.5f * WORLD_TO_BOX);
        mParticleGroupDef1.linearVelocity.set(new Vector2(0, -10f));
        updateParticleCount();
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (Box2dGame.world.isLocked()) {
                    return;
                }
                System.out.println("========");
                crr();
            }
        });
    }

    public void crr(){
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            mParticleGroupDef1.position.set(2+random.nextInt(10),20+random.nextInt(10));
            mParticleSystem.createParticleGroup(mParticleGroupDef1);
        }
        updateParticleCount();
    }

    private void updateParticleCount() {
        if(mParticleSystem.getParticleCount() > mParticleDebugRenderer.getMaxParticleNumber()) {
            mParticleDebugRenderer.setMaxParticleNumber(mParticleSystem.getParticleCount() + 1000);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Box2dGame.world.step(1/60.0F,6,3,1);
        Box2dGame.renderer.render(Box2dGame.world,Box2dGame.combined);
        mParticleDebugRenderer.render(mParticleSystem, 12, Box2dGame.combined);
    }

    //    @Override
//    public void act(float delta) {
//        super.act(delta);
//
////        float accelerometerX = input.getAccelerometerX();
////        float accelerometerY = input.getAccelerometerY();
////        float accelerometerZ = input.getAccelerometerZ();
////        System.out.println(accelerometerX +".  "+accelerometerY+" .  "+accelerometerZ);
////        Box2dGame.world.getGravity().set(accelerometerX,accelerometerY);
////        Box2dGame.world.setGravity(gravity.set(-accelerometerX,-accelerometerY));
//    }
}
