package com.me.blip.boxtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.me.blip.boxtest.Constants.CRATE_BITS;
import static com.me.blip.boxtest.Constants.PLATFORM_BITS;
import static com.me.blip.boxtest.Constants.PLAYER_BITS;
import static com.me.blip.boxtest.Constants.SENSOR_BITS;
import static com.me.blip.boxtest.Constants.UPM;
import static com.me.blip.boxtest.Constants.WATER_BITS;

public class BoxTest extends ApplicationAdapter
{
    private boolean DEBUG = false;
    private final float SCALE = 2.0f;
    private final float playerForce = 10;
    private final float playerVelocityCap = 8;
    private OrthographicCamera cam;
    private World world;
    private Body player,platform,crate,crate2,water;
    private Box2DDebugRenderer b2dr;
    private ShapeRenderer sr;
    private MyContactListener contactListener;
    private Vector2 gravity = new Vector2(0,-9.81f);
    private Buoyancy2D buoyancy2D;

    @Override
    public void create()
    {
        cam = new OrthographicCamera(/*parameters here that are like scaled based on a co
        nstant with width and height*/);
        cam.setToOrtho(false,20,20);
        world = new World(gravity,false);
        b2dr = new Box2DDebugRenderer();
        player = createPlayer(50,100,32,32);
        crate = createCrate(100,100,40,120);
        platform = createPlatform(0,0,300,32);
        water = createWater(300,-150,300,300);
        sr = new ShapeRenderer();
        buoyancy2D = new Buoyancy2D(gravity);
        contactListener = new MyContactListener(buoyancy2D);
        world.setContactListener(contactListener);
    }

    public void update(float dt) {
        world.step(1/60f,6,2);
        buoyancy2D.update();
        inputUpdate(dt);
        camUpdate(dt);
    }

    public void inputUpdate(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.applyForceToCenter(-playerForce,0f,false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.applyForceToCenter(playerForce,0f,false);
        }
        //make timer or something after key is pressed before this refreshes,
        //then change to iskeypressed so you can just hold it before touching ground and jump
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(contactListener.touchingGround)player.applyForceToCenter(0f,500f,false);
            System.out.println(contactListener.touchingGround);
        }
        player.setLinearDamping(1f);
    }

    public void camUpdate(float dt) {
        Vector3 position = cam.position;
        position.x = player.getPosition().x * UPM;
        position.y = player.getPosition().y * UPM;
        cam.position.set(position);
        cam.update();
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world,cam.combined.scl(UPM));
    }

    @Override
    public void dispose() {
        sr.dispose();
        world.dispose();
        b2dr.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        cam.setToOrtho(false,width/2,height/2);
    }

    public Body createWater(float x,float y,float width,float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x/UPM,y/UPM);
        Body boxBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = WATER_BITS;
        fixtureDef.filter.maskBits = PLAYER_BITS | CRATE_BITS;

        PolygonShape shape = new PolygonShape();
        fixtureDef.shape = shape;
        shape.setAsBox(width/2/UPM,height/2/UPM);

        boxBody.createFixture(fixtureDef).setUserData("water");
        shape.dispose();

        return boxBody;
    }

    public Body createPlayer(float x,float y,float width,float height) {
        //BODY DEFINITION
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/UPM,y/UPM);
        bodyDef.fixedRotation = true;
        Body boxBody = world.createBody(bodyDef);

        //FIXTURE DEFINITION
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0f;
        fixtureDef.density = 1.1f;
        fixtureDef.restitution = 0f;

        /*
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(width*2f/2/UPM,-height/2/UPM);
        vertices[1] = new Vector2(0f,height/2/UPM);
        vertices[2] = new Vector2(-width * 2f/2/UPM,-height/2/UPM);
        */

        PolygonShape shape = new PolygonShape();
        fixtureDef.shape = shape;

        //shape.set(vertices);
        shape.setAsBox(width/2f/UPM,height/2f/UPM);
        fixtureDef.filter.categoryBits = PLAYER_BITS;
        fixtureDef.filter.maskBits = PLATFORM_BITS | CRATE_BITS | WATER_BITS;
        boxBody.createFixture(fixtureDef).setUserData("body");

        shape.setAsBox((width-4f)/2f/UPM,1/UPM,new Vector2(0f,-height/2f/UPM),0f);
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = SENSOR_BITS;
        fixtureDef.filter.maskBits = PLATFORM_BITS | CRATE_BITS;
        boxBody.createFixture(fixtureDef).setUserData("foot");

        shape.dispose(); //put after creating fixture!

        return boxBody;
    }

    public Body createCrate(float x,float y,float width,float height) {
        //BODY DEFINITION
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / UPM, y / UPM);
        bodyDef.fixedRotation = false;

        //FIXTURE DEFINITION
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 1f;
        fixtureDef.density = 0.75f;
        fixtureDef.restitution = 0.5f;

        //set the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / UPM, height / 2 / UPM);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CRATE_BITS;
        fixtureDef.filter.maskBits = PLATFORM_BITS | PLAYER_BITS | CRATE_BITS | SENSOR_BITS | WATER_BITS;

        //ADD TO WORLD AND RETURN
        Body boxBody = world.createBody(bodyDef);
        boxBody.createFixture(fixtureDef).setUserData("crate");
        shape.dispose(); //put after creating fixture!
        return boxBody;
    }

    public Body createPlatform(float x,float y,float width,float height) {
        //BODY DEFINITION
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x/UPM,y/UPM);

        //FIXTURE DEFINITION
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.5f;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0f;
        //set the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/UPM,height/2/UPM);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = PLATFORM_BITS;
        fixtureDef.filter.maskBits = PLAYER_BITS | CRATE_BITS | SENSOR_BITS;
        //ADD TO WORLD AND RETURN
        Body boxBody = world.createBody(bodyDef);
        boxBody.createFixture(fixtureDef).setUserData("platform");
        shape.dispose(); //put after creating fixture!
        return boxBody;
    }
}
