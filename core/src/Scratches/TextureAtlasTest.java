package Scratches;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextureAtlasTest extends ApplicationAdapter {

    SpriteBatch batch;
    Texture textureRun[] = new Texture[14];
    Texture textureStand[] = new Texture[4];
    private float elapsedTime = 0;
    Vector2 v1;
    Sprite spr1;
    Texture texture;
    TextureAtlas textureAtlasStand;
    TextureAtlas textureAtlasRun;
    Animation animationStill;
    Animation animationRun;
    int nTimer = 0, nAnim1 = 0, nAnim2 = 0;
    int nDir = 1;
    Timer time;
    boolean bLR;

    @Override
    public void create() {
        textureAtlasStand = new TextureAtlas(Gdx.files.internal("sonicstand.atlas"));
        animationStill = new Animation(5 / 10f, textureAtlasStand.getRegions());
        textureAtlasRun = new TextureAtlas(Gdx.files.internal("sonicrun.atlas"));
        animationRun = new Animation(1 / 20f, textureAtlasRun.getRegions());
        batch = new SpriteBatch();
        v1 = new Vector2(200, 200);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(100, 100, 100, 20);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        int nAnim = whichAnim();
        if (nAnim == 2) {
            batch.draw(animationStill.getKeyFrame(elapsedTime, true), v1.x, v1.y);
        }
        else if (nAnim == 1) {
            batch.draw(animationRun.getKeyFrame(elapsedTime, true), v1.x, v1.y);
        }else if (nAnim == 0) {
         batch.draw(animationRun.getKeyFrame(elapsedTime, true), v1.x, v1.y);   
        }
        batch.end();
    }

    public int whichAnim() {
        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
            v1.x-=5;
            return 0;
        } else if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
            
            v1.x+=5;
            return 1;
        } else {
            return 2;
        }


    }
}