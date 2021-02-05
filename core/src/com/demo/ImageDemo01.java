package com.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * 绘制多边形  比如矩形
 */
public class ImageDemo01 extends Group {
    float[] vertices;
    TextureRegion region = new TextureRegion(new Texture("1.png"));
    PolygonSpriteBatch polygonSpriteBatch;
    PolygonSprite sprite;
    PolygonRegion PolygonRegion = null;
    private int idx = 0;
    short tri [];
    public ImageDemo01(){
        x = 0;
        y = 0;
        vertices = new float[2000];
        int idx = this.idx;

        for (float i = 0; i <= 360; i=i+0.5F) {
            vertices[idx ++] = (float) (100+ 100*Math.sin(i));
            vertices[idx ++] = (float) (100+ 100*Math.cos(i));
        }
        vertices[idx++] = 100;
        vertices[idx++] = 100;
        tri = new short[1200];
        for (int i = 0; i < 360; i++) {
            tri[3*i] = 360;
            tri[3*i+1] = (short) i;
            tri[3*i+2] = (short) (i+1);
        }

        PolygonRegion = new PolygonRegion(region,vertices,tri);
        sprite = new PolygonSprite(PolygonRegion);
//        Image image = new Image(new TextureRegion(sprite));
//        addActor();
        polygonSpriteBatch = new PolygonSpriteBatch();
        sprite.setPosition(100,100);
    }







    public void draw(Batch batch, float parentAlpha) {
        polygonSpriteBatch.begin();

        sprite.draw(polygonSpriteBatch);
        polygonSpriteBatch.end();
    }
}
