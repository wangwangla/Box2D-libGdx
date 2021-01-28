package wk.box.util;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class MyBox2D extends Body {
    /**
     * Constructs a new body with the given address
     *
     * @param world the world
     * @param addr  the address
     */
    private Body body;
    private Handler handler;

    protected MyBox2D(World world, long addr) {
        super(world, addr);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void handler(){
        if (handler != null){
            handler.beginContactHanlder();
        }
    }
}

interface Handler{
    public void beginContactHanlder();
}