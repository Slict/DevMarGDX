/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author chesj2479
 */
public class Movement {

    Vector2 v1, v1temp;
    Texture textureRun[] = new Texture[14];
    Texture textureStand[] = new Texture[4];
    Texture textureJump[] = new Texture[19];
    TextureRegion texRegion;
    int nRunBuffer = 0, nRunIndex = 0;
    int nStandBuffer = 0, nStandIndex = 0;
    int nDir = 0, nCounter = 0;
    int nJumpBuffer = 0, nJumpIndex = 0;
    boolean isJumpUp = false, isJumpDown = false;
    boolean bLR = false;
    double dGrav = 8;

    public Movement() {
        v1 = new Vector2(100, 0);
        v1temp = new Vector2();
        for (int i = 0; i < 14; i++) {
            textureRun[i] = new Texture(Gdx.files.internal("run/" + i + ".png"));
        }
        for (int i = 0; i < 4; i++) {
            textureStand[i] = new Texture(Gdx.files.internal("run/" + i + ".png"));
        }
        for (int i = 0; i < 19; i++) {
            textureJump[i] = new Texture(Gdx.files.internal("jump/" + i + ".png"));
        }
    }

    public TextureRegion drawMove() {
        if (nRunBuffer == 4) {
            nRunIndex++;
            nRunBuffer = 0;
        }
        if (nRunIndex == 6) {
            nRunIndex = 0;
        }
        if (nStandBuffer == 15) {
            nStandIndex++;
            nStandBuffer = 0;
        }
        if (nStandIndex == 4) {
            nStandIndex = 0;
        }
        if (nJumpBuffer == 3) {
            nJumpIndex++;
            nJumpBuffer = 0;
        }
        if (nJumpIndex == 19) {
            nJumpIndex = 0;
        }
        System.out.println(nRunIndex);
        nDir = whichAnim(v1);
        if (nDir == 1) {
            v1.x -= 3;
            texRegion = new TextureRegion(textureRun[nRunIndex]);
        }
        if (nDir == 2) {
            v1.x += 3;
            texRegion = new TextureRegion(textureRun[nRunIndex]);
        }
        if (nDir == 3) {
            texRegion = drawJump(v1);
        }
        if (nDir == 0) {
            texRegion = new TextureRegion(textureStand[nStandIndex]);
        }
        System.out.println(textureStand[nStandIndex]);
        texRegion.flip(bLR, false);
        return texRegion;
    }

    public int whichAnim(Vector2 _v1) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bLR = true;
            return 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bLR = false;
            return 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            return 3;
        } else {
            return 0;
        }
    }

    public Vector2 getPos() {
        return v1;
    }

    public TextureRegion drawJump(Vector2 _v1) {
        if (nJumpBuffer == 3) {
            nJumpIndex++;
            nJumpBuffer = 0;
        }
        if (nJumpIndex == 19) {
            nJumpIndex = 0;
        }
        if (isJumpUp || isJumpDown) {
            texRegion = new TextureRegion(textureJump[nJumpIndex]);
        }
        if (!isJumpUp && !isJumpDown) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                dGrav = 6;
                nJumpIndex = 0;
                isJumpUp = true;
            }
        }
        nCounter++;
        if (nCounter % 2 == 0) {
            dGrav -= 0.5;
        }
        if (_v1.y < 0) {
            _v1.y = 0;
            dGrav = 0;
            isJumpUp = false;
            isJumpDown = false;
        }
        if (isJumpUp) {
            if (_v1.y < 100) {
                _v1.y += dGrav;
            } else {
                isJumpDown = true;
                isJumpUp = false;
            }
        }
        if (isJumpDown) {
            if (_v1.y > 0) {
                _v1.y -= dGrav;
                System.out.println("down");
            } else if (_v1.y <= 0) {
                dGrav = 0;
                _v1.y = 0;
                isJumpUp = false;
                isJumpDown = false;
            }
        }
        return texRegion;
    }
}