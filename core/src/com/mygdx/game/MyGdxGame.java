package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture textureRun[] = new Texture[14];
    Texture textureStand[] = new Texture[4];
    Texture textureJump[] = new Texture[19];
    Texture texBullet;
    TextureRegion texRegion;
    Vector2 v1, v1temp;
    Sprite spr1;
    int nTimer = 0, nAnim1 = 0, nAnim2 = 0, nAnim3 = 0;
    int nDir = 1;
    int nRunBuffer = 0, nRunIndex = 0;
    int nStandBuffer = 0, nStandIndex = 0;
    int nJumpBuffer = 0, nJumpIndex = 0;
    int nCounter = 0;
    Timer time;
    double dGrav = 8;
    boolean isJumpUp = false, isJumpDown = false;
    boolean bLR;
    Movement move;

    @Override
    public void create() {
        texBullet = new Texture(Gdx.files.internal("bullet.png"));
        batch = new SpriteBatch();
        move = new Movement();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(100, 100, 100, 20);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        texRegion = move.drawMove();
        v1 = move.getPos();
        batch.draw(texRegion, v1.x, v1.y);
        batch.end();
        //        nRunBuffer++;
//        nJumpBuffer++;
//        nStandBuffer++;
//        if (nRunBuffer == 4) {
//            nRunIndex++;
//            nRunBuffer = 0;
//        }
//        if (nRunIndex == 6) {
//            nRunIndex = 0;
//        }
//        if (nStandBuffer == 15) {
//            nStandIndex++;
//            nStandBuffer = 0;
//        }
//        if (nStandIndex == 4) {
//            nStandIndex = 0;
//        }
//        if (nJumpBuffer == 3) {
//            nJumpIndex++;
//            nJumpBuffer = 0;
//        }
//        if (nJumpIndex == 19) {
//            nJumpIndex = 0;
//        }
//        nDir = whichAnim();
//        if (nDir == 0) {
//            texRegion = new TextureRegion(textureStand[nStandIndex]);
//        } else if (nDir == 1) {
//            texRegion = new TextureRegion(textureRun[nRunIndex]);
//        } else if (nDir == 2) {
//            texRegion = new TextureRegion(textureRun[nRunIndex]);
//        }
//        if (isJumpUp || isJumpDown) {
//            texRegion = new TextureRegion(textureJump[nJumpIndex]);
//        }
//        if (!isJumpUp && !isJumpDown) {
//            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                dGrav = 6;
//                nJumpIndex = 0;
//                isJumpUp = true;
//            }
//        }
//        nCounter++;
//        if (nCounter % 2 == 0) {
//            dGrav -= 0.5;
//        }
//        if (v1.y < 0) {
//            v1.y = 0;
//            dGrav = 0;
//            isJumpUp = false;
//            isJumpDown = false;
//        }
//        if (isJumpUp) {
//            if (v1.y < 100) {
//                v1.y += dGrav;
//            } else {
//                isJumpDown = true;
//                isJumpUp = false;
//            }
//        }
//        if (isJumpDown) {
//            if (v1.y > 0) {
//                v1.y -= dGrav;
//                System.out.println("down");
//            } else if (v1.y <= 0) {
//                dGrav = 0;
//                v1.y = 0;
//                isJumpUp = false;
//                isJumpDown = false;
//            }
//        }
//        bullet(v1);
//        texRegion.flip(bLR, false);
    }

    public int whichAnim() {
        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
            bLR = true;
            v1.x -= 3;
            return 1;
        }
        if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
            bLR = false;
            v1.x += 3;
            return 2;
        } else {
            return 0;
        }
    }

    private void bullet(Vector2 v1) {
    }
}