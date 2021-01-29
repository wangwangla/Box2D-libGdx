package com.demo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDemo extends Image {
    ShaderProgram shaderProgram;

    public ImageDemo(){
        this.shaderProgram = shaderProgram;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setShader(shaderProgram);
        batch.setShader(null);

    }
}
