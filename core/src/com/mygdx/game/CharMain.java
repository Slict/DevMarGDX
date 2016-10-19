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
public class CharMain {

    Vector2 v1, v1temp;
    Texture textureRun[] = new Texture[14];
    Texture textureStand[] = new Texture[4];
    Texture textureJump[] = new Texture[19];
    TextureRegion texRegion;
    int nRunBuffer = 0, nRunIndex = 0;
    int nStandBuffer = 0, nStandIndex = 0;
    int nDir = 0, nGravCounter = 0;
    int nJumpBuffer = 0, nJumpIndex = 0;
    boolean isJumpUp = false, isJumpDown = false, isJump = false;
    boolean bLR = false;
    double dGrav = 8;

    public CharMain() {
        v1 = new Vector2(100, 0);
        v1temp = new Vector2();
        //Populate texture arrays with local pngs
        for (int i = 0; i < 14; i++) {
            textureRun[i] = new Texture(Gdx.files.internal("run/" + i + ".png"));
        }
        for (int i = 0; i < 4; i++) {
            textureStand[i] = new Texture(Gdx.files.internal("standing/" + i + ".png"));
        }
        for (int i = 0; i < 19; i++) {
            textureJump[i] = new Texture(Gdx.files.internal("jump/" + i + ".png"));
        }
    }

    public TextureRegion drawMove() {
        //Increases buffer of each texture array, delays the changes between sprites
        nRunBuffer++;
        nJumpBuffer++;
        nStandBuffer++;
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
        nDir = whichAnim();
        if (nDir == 0) {
            texRegion = new TextureRegion(textureStand[nStandIndex]);
        }
        if (nDir == 1) {
            v1.x -= 3;
            texRegion = new TextureRegion(textureRun[nRunIndex]);
        } else if (nDir == 2) {
            v1.x += 3;
            texRegion = new TextureRegion(textureRun[nRunIndex]);
        }
//Need to put entire jump "method" into this, otherwise it won't move horizontally while jumping
        if (isJumpUp || isJumpDown) {
            texRegion = new TextureRegion(textureJump[nJumpIndex]);
        }
        if (!isJumpUp && !isJumpDown) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                dGrav = 6;
                nJumpIndex = 0;
                isJumpUp = true;
            }
        }
        nGravCounter++;
        if (nGravCounter % 2 == 0) {
            dGrav -= 0.5;
        }
        if (v1.y < 0) {
            v1.y = 0;
            dGrav = 0;
            isJumpUp = false;
            isJumpDown = false;
        }
        if (isJumpUp) {
            if (v1.y < 100) {
                v1.y += dGrav;
            } else {
                isJumpDown = true;
                isJumpUp = false;
            }
        }
        if (isJumpDown) {
            if (v1.y > 0) {
                v1.y -= dGrav;
                System.out.println("down");
            } else if (v1.y <= 0) {
                dGrav = 0;
                v1.y = 0;
                isJumpUp = false;
                isJumpDown = false;
            }
        }
        texRegion.flip(bLR, false);
        return texRegion;
    }

    public int whichAnim() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            bLR = true;
            return 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            bLR = false;
            return 2;
        } else {
            return 0;
        }
    }

    public Vector2 getPos() {
        return v1;
    }

    public void boundaries(Vector2 v1) {
        if (Gdx.input.getX() < v1.x) {
            bLR = true;
        }
        if (Gdx.input.getX() > v1.x) {
            bLR = false;
        }
        if (v1.x > Gdx.graphics.getWidth() - texRegion.getRegionWidth()) {
            v1.x = Gdx.graphics.getWidth() - texRegion.getRegionWidth();
        }
        if (v1.x < 0) {
            v1.x = 0;
        }
        if (v1.y < 0) {
            v1.y += 3;
        }
    }
}
