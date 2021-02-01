package com.kangwang.word;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.Array;
import com.kangwang.world.MainGame;

import org.freyja.libgdx.cocostudio.ui.parser.widget.CCImageView;

import java.io.File;

public class DesktopLauncher {
    public static void main(String[] arg) {
//        texturePack();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "wrod squares";
        config.width =360; //485
        config.height =640;
        config.x = 0;
        config.y = 0;
        new LwjglApplication(new MainGame(),config);
    }

//    static String[] atlasFileName = {"theme_v","theme_h","load","main","yindao","theme_reward"};
//    static String[] atlasFileName = {"egg_jiangli"};
//    static String[] atlasFileName = {"combo_ani"};
//    static String[] atlasFileName = {"main","menu","yindao"};
    static String[] atlasFileName = {"yindao"};

//    static String[] atlasFileName = {"Switzerland","Poland"};
    static Array<String> array = new Array<>();
    public static void pre(){
        File file = new File("G:\\wordstacks\\Assets\\click");
        for (String s : file.list()) {
            System.out.println(s);
            array.add(s);
        }
    }
    private static void texturePack() {
//        pre();
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.pot = false;
        settings.maxHeight = 2048;
        settings.maxWidth = 2048;
        settings.duplicatePadding = true;
        settings.paddingX = 8;
        settings.paddingY = 8;
        settings.edgePadding = true;
        settings.bleed = true;
        settings.combineSubdirectories = true;
        settings.format = Pixmap.Format.RGBA8888;
        settings.filterMag = Texture.TextureFilter.Linear;
        settings.filterMin = Texture.TextureFilter.Linear;
        settings.useIndexes = false;
        settings.stripWhitespaceX = true;
        settings.stripWhitespaceY = true;
//        settings.rotation = true;
//        processDesktop(settings);
       processAndroid(settings);
 //       process(settings,"tishi_fdj");
    }

/*    private static void processDesktop(TexturePacker.Settings setting) {
//        File file = new File("");
//        File dstFile = new File(new File(file.getAbsolutePath()).getParentFile().getParentFile().getAbsolutePath() + "/Assets/pic/");
        for (int i = 0; i < atlasFileName.length; i++) {
            String input = atlasFileName[i];
            if (input == null) return;
            try {
                TexturePacker.process(setting, "desktop/pic/", "desktop/", input);
                //  TexturePacker.process(setting, dstFile.getAbsolutePath() + "/" + input + "/", "desktop/", input);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    private static void process(TexturePacker.Settings setting,String srcDir) {
//        File file = new File("");
//        File dstFile = new File(new File(file.getAbsolutePath()).getParentFile().getParentFile().getAbsolutePath() + "/Assets/pic/");
                TexturePacker.process(setting, "../../Assets/spine/" + srcDir + "/", "../../Assets/atlas/", srcDir);
//                TexturePacker.process(setting, "desktop/pic/", "desktop/", input);

    }


    private static void processAndroid(TexturePacker.Settings setting) {
//        File file = new File("");
//        File dstFile = new File(new File(file.getAbsolutePath()).getParentFile().getParentFile().getAbsolutePath() + "/Assets/pic/");
        for (int i = 0; i < atlasFileName.length; i++) {
            String input = atlasFileName[i];
            if (input == null) return;
            try {
                TexturePacker.process(
                        setting,
                        "../../Assets/" + input + "/",
                        "image/" ,
                        input);


//                TexturePacker.process(setting, "desktop/pic/", "desktop/", input);
                //  TexturePacker.process(setting, dstFile.getAbsolutePath() + "/" + input + "/", "desktop/", input);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        for (int i = 0; i < array.size; i++) {
//            String input = array.get(i);
//            if (input == null) return;
//            try {
//                TexturePacker.process(
//                        setting,
//                        "../../Assets/" + input + "/",
//                        "themes/"+input ,
//                        input);
//
//
////                TexturePacker.process(setting, "desktop/pic/", "desktop/", input);
//                //  TexturePacker.process(setting, dstFile.getAbsolutePath() + "/" + input + "/", "desktop/", input);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}