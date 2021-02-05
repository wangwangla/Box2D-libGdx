package com.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDemo extends Image {
    public ImageDemo(){
        super(new TextureRegion(new Texture("2.png")));

    }
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
    }
}
