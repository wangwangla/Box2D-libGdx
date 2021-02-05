package com.kangwang.word;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AAA extends Game {
    PolygonSpriteBatch batch;
    TextureRegion textureRegion;
    PolygonRegion polygonRegion;
    @Override
    public void create() {
        batch=new PolygonSpriteBatch();

        Texture texture=new Texture("android/assets/1.png");
         textureRegion=new TextureRegion(texture);

        float[] vs=new float[]{
                0,0,
                0,261,
                477,261,
        };
        short[] ts=new short[]{
                0,1,2
        };
         polygonRegion=new PolygonRegion(textureRegion,vs,ts);
    }

    @Override
    public void render() {
        super.render();

        batch.begin();

//        batch.draw(textureRegion,100,100);

        batch.draw(polygonRegion,100,100);

        batch.end();
    }

    public static void main(String[] args) {
        new LwjglApplication(new AAA());
    }
}
