package com.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDemo extends Image {
    ShaderProgram shaderProgram;

    public ImageDemo(){
        super(new Texture("1.png"));
        shaderProgram = new ShaderProgram(Gdx.files.internal("gray.vert"),Gdx.files.internal("gray.frag"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setShader(shaderProgram);
        super.draw(batch,parentAlpha);
        batch.setShader(null);

    }
}
