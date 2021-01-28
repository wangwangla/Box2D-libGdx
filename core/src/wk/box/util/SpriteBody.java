package wk.box.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

public class SpriteBody extends Image {

    public SpriteBody() {
    }

    public SpriteBody(NinePatch patch) {
        super(patch);
    }

    public SpriteBody(TextureRegion region) {
        super(region);
    }

    public SpriteBody(Texture texture) {
        super(texture);
    }

    public SpriteBody(Skin skin, String drawableName) {
        super(skin, drawableName);
    }

    public SpriteBody(Drawable drawable) {
        super(drawable);
    }

    public SpriteBody(Drawable drawable, Scaling scaling) {
        super(drawable, scaling);
    }

    public SpriteBody(Drawable drawable, Scaling scaling, int align) {
        super(drawable, scaling, align);
    }


}
