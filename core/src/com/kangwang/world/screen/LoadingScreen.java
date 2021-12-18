package com.kangwang.world.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kangwang.example.bodyimage.BodyImage;
import com.kangwang.example.change.ChangeSize;
import com.kangwang.world.MainGame;
import com.kangwang.world.screen.base.BaseScreen;

import com.kangwang.example.OneBodyMuFixture.OneBodyHaveMuFixture;

public class LoadingScreen extends BaseScreen {

    private ScrollPane pane;

    public LoadingScreen(MainGame mainGame) {
        super(mainGame);
    }

    @Override
    public void show() {
        super.show();
//        OneBodyHaveMuFixture oneBodyHaveMuFixture = new OneBodyHaveMuFixture();
//        stage.addActor(oneBodyHaveMuFixture);
//

        ChangeSize changeSize = new ChangeSize();
        stage.addActor(changeSize);
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                changeSize.scale();
            }
        });


//        BodyImage bodyImage = new BodyImage(new TextureRegion(new Texture("")));
//        stage.addActor(bodyImage);
//        Table table = new Table(){{
//            String [] str = {
//                    "WORLD",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "WORLD",
//                    "JIEJIAN",
//                    "XXXXXX",
//                    "JIEJIAN",
//                    "XXXXXX"
//            };
//            for (String s : str) {
//                Label label = new Label(s+"",new Label.LabelStyle(){{
//                    font = MainGame.resource.bitmapFont;
//                }});
//                add(label);
//                label.addListener(new ClickListener(){
//                    @Override
//                    public void clicked(InputEvent event, float x, float y) {
//                        super.clicked(event, x, y);
//
//                    }
//                });
//                label.setFontScale(0.3F);
//                row();
////                Image image = new Image(new Texture("banana.png"));
////                add(image);
//
//            }
//        }};
//        pane = new ScrollPane(table,new ScrollPane.ScrollPaneStyle());
//        pane.setSize(Constant.width,Constant.hight);
//        stage.addActor(pane);
    }
}
