package com.kangwang.word;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kangwang.world.MainGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "wrod squares";
        config.width =360; //485
        config.height =640;
        config.x = 0;
        config.y = 0;
        new LwjglApplication(new MainGame(),config);
    }
}