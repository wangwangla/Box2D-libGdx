package com.kangwang.pinghengche;

import com.badlogic.gdx.math.Vector2;

public class utils {
    private float scaleFactor = 10;
    public Vector2 scaleToWorld(Vector2 a) {
        a.x = (a.x)/scaleFactor;
        a.y = (a.y)/scaleFactor;
        return a;
    };
}
