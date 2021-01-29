package com.kangwang.world;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Constant {
    public static World world;
    public static Box2DDebugRenderer renderer;
    public static Matrix4 combined;

    public static float width;
    public static float hight;

    public static float PPM = 1/10.0F;


    public static final short BUTT_BIT = 1;
    public static final short WALL_BIT = 1 << 1;
    public static final short BLACK_BIT = 1 << 2;
    public static final short BUTTOM_BIT = 1 << 3;
}
