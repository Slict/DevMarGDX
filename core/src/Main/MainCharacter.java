/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author chesj2479
 */
public class MainCharacter {

    final static MainCharacter shared = new MainCharacter();

    private Point position = new Point(100, 0);

    private Texture[] textureRun = new Texture[14];
    private Texture[] textureStand = new Texture[4];
    private Texture[] textureJump = new Texture[19];

    private TextureRegion texRegion;
    int nRunBuffer = 0;
    int nRunIndex = 0;
    int nStandBuffer = 0;
    int nStandIndex = 0;
    int nDir = 0;
    int nGravCounter = 0;
    int nJumpBuffer = 0;
    int nJumpIndex = 0;
    boolean isJumpUp = false;
    boolean isJumpDown = false;
    boolean isJump = false;
    boolean bLR = false;
    double dGrav = 8;

    private MainCharacter() {
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

        switch (nDir) {
            case 0:
                texRegion = new TextureRegion(textureStand[nStandIndex]);
                break;
            case 1:
                position.x -= 3;
                texRegion = new TextureRegion(textureRun[nRunIndex]);
                break;
            case 2:
                position.x += 3;
                texRegion = new TextureRegion(textureRun[nRunIndex]);
                break;
            default:
                break;
        }

        //Need to put entire jump "method" into this, otherwise it won't move horizontally while jumping
        if (isJumpUp || isJumpDown) {
            texRegion = new TextureRegion(textureJump[nJumpIndex]);
        }

        if (!isJumpUp && !isJumpDown) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                dGrav = 6;
                nJumpIndex = 0;
                isJumpUp = true;
            }
        }

        nGravCounter++;
        if (nGravCounter % 2 == 0) {
            dGrav -= 0.5;
        }
        if (position.y < 0) {
            position.y = 0;
            dGrav = 0;
            isJumpUp = false;
            isJumpDown = false;
        }
        if (isJumpUp) {
            if (position.y < 100) {
                position.y += dGrav;
            } else {
                isJumpDown = true;
                isJumpUp = false;
            }
        }
        if (isJumpDown) {
            if (position.y > 0) {
                position.y -= dGrav;
                System.out.println("down");
            } else if (position.y <= 0) {
                dGrav = 0;
                position.y = 0;
                isJumpUp = false;
                isJumpDown = false;
            }
        }
        texRegion.flip(bLR, false);
        return texRegion;
    }

    public int whichAnim() {
        //Determine which way the sprite should be moving & facing
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

    Point getPosition() {
        return position;
    }


    public void boundaries(Point point) {
        //Making sprite flip to position of mouse, making sure it can't run off the screen

        bLR = (Gdx.input.getX() < point.x);

        if (point.x > Gdx.graphics.getWidth() - texRegion.getRegionWidth()) {
            point.x = Gdx.graphics.getWidth() - texRegion.getRegionWidth();
        }
        if (point.x < 0) {
            point.x = 0;
        }
        if (point.y < 0) {
            point.y += 3;
        }
    }
}
