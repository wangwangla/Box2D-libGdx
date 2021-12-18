package com.kangwang.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontResource {
    private AssetManager assetManager;
    public BitmapFont bitmapFont;

    public FontResource(AssetManager assetManager){
        this.assetManager = assetManager;
        loadFont();
    }

    public void loadFont(){
        assetManager.load("army_stencil.fnt", BitmapFont.class);
    }

    public void getFont(){
        bitmapFont = assetManager.get("army_stencil.fnt", BitmapFont.class);
    }
}
