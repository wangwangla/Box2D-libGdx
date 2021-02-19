package wk.box.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Group;

import finnstr.libgdx.liquidfun.ParticleDebugRenderer;
import finnstr.libgdx.liquidfun.ParticleDef;
import finnstr.libgdx.liquidfun.ParticleGroupDef;
import finnstr.libgdx.liquidfun.ParticleSystem;
import finnstr.libgdx.liquidfun.ParticleSystemDef;
import wk.box.game.Box2dGame;
import wk.box.game.screen.utils.Box2dUtils;

public class XXXXX extends Group {
    Input input;
    Box2dUtils utils;
    Vector2 gravity = new Vector2();
    ParticleDebugRenderer mParticleDebugRenderer;
    ParticleSystem mParticleSystem;
    public XXXXX(){
        input = Gdx.input;
        utils = new Box2dUtils();
        utils.createBox(0,63,1,62,true);
        utils.createBox(36,0,36,1,true);
        utils.createBox(72,63,1,62,true);
        utils.createBox(0,126,72,1,true);
        utils.radis(10,10,2,false);
//        utils.radis(10,10,2,false);
        createPri();
    }

    public void createPri(){
        ParticleSystemDef systemDef = new ParticleSystemDef();
        systemDef.radius = 6f;
        systemDef.dampingStrength = 0.2f;
        mParticleSystem = new ParticleSystem(Box2dGame.world, systemDef);
        mParticleDebugRenderer = new ParticleDebugRenderer(new Color(0, 1, 0, 1), mParticleSystem.getParticleCount());
        ParticleGroupDef mParticleGroupDef2;
        mParticleGroupDef2 = new ParticleGroupDef();
        PolygonShape parShape = new PolygonShape();
        parShape.setAsBox(width * (20f / 100f)  / 2f, width * (20f / 100f) / 2f);
        mParticleGroupDef2.shape = parShape;
        mParticleGroupDef2.flags.add(ParticleDef.ParticleType.b2_waterParticle);
//        mParticleGroupDef2.groupFlags = mParticleGroupDef1.groupFlags;
        mParticleGroupDef2.position.set(width * (70f / 100f), height * (80f / 100f));
        mParticleGroupDef2.color.set(0.2f, 1f, 0.3f, 1);
        mParticleSystem.createParticleGroup(mParticleGroupDef2);

        mParticleGroupDef2.position.set(4, 4);
        mParticleSystem.createParticleGroup(mParticleGroupDef2);
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
        mParticleDebugRenderer.render(mParticleSystem, 12, Box2dGame.combined);
    }
}
