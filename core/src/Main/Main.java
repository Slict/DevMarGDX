package Main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    TextureRegion texRegion;
    Texture texBG;
    Vector2 v1, v1temp;
    int nGravCounter = 0, nBulletTimer = 0;
    Timer time;
    double dGrav = 8;
    boolean isJumpUp = false, isJumpDown = false;
    boolean bLR;
    CharMain charmain;
    Bullet bul;
    ArrayList<Bullet> ArrBul;

    @Override
    public void create() {
        texBG = new Texture(Gdx.files.internal("mkombat.jpg"));
        ArrBul = new ArrayList<Bullet>();
        batch = new SpriteBatch();
        charmain = new CharMain();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(100, 100, 100, 20);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texBG, 0, 0, 1280, 720);
        v1 = charmain.getPos();
        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            nBulletTimer++;
            if (nBulletTimer % 10 == 0) {
                ArrBul.add(new Bullet(v1.x, v1.y));
                System.out.println("add");
            }
        }

        for (int i = 0; i < ArrBul.size(); i++) {
            Bullet bulTemp = ArrBul.get(i);
            bulTemp.update();
            if (bulTemp.isOffScreen()) {
                ArrBul.remove(i);
            }
            batch.draw(bulTemp.imgOut, bulTemp.nLoc.x, bulTemp.nLoc.y, (float) 25 / 2, (float) 25 / 2, (float) 34.0, (float) 8.0, (float) 1, (float) 1, bulTemp.fRot);
        }

        texRegion = charmain.drawMove();
        batch.draw(texRegion, v1.x, v1.y);
        charmain.boundaries(v1);
        batch.end();
    }
}
