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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.awt.Label;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chesj2479
 */
public class James extends ApplicationAdapter {

    SpriteBatch batch;
    Texture texture;
    TextureAtlas textureAtlasJump;
    Vector2 vec1, backVec;
    Animation animationJump;
    private float elapsedTime = 0;
    float MaxJumpHeight, START_JUMP_POSITION;
    Sprite spr1;
    int nCounter = 0, nCounter2 = 0;
    Timer time;
    boolean isJumpUP = false, isJumpDOWN = false;

    //final Rectangle screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    @Override
    public void create() {
        textureAtlasJump = new TextureAtlas(Gdx.files.internal("jump.pack"));
        animationJump = new Animation(1/10f, textureAtlasJump.getRegions());
        
        batch = new SpriteBatch();
        vec1 = new Vector2(200, 50);

        //Set the max jump height

        START_JUMP_POSITION = vec1.y;
        MaxJumpHeight = vec1.y + 50;





    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(100, 0, 0, 1000);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (isJumpUP) {
            if (vec1.y >= MaxJumpHeight) {
                isJumpUP = false;
                isJumpDOWN = true;
            } else {
                vec1.y += 5;
            }
            
            if (isJumpDOWN) {
                if (vec1.y <= START_JUMP_POSITION) {
                    isJumpDOWN = false;
                    vec1.y -= 5;
                } else {
                    
                }
            }
        }


            if (Gdx.input.isKeyJustPressed(Keys.UP)) {
                isJumpUP = true;
            }
            System.out.println(isJumpUP);
            batch.draw(animationJump.getKeyFrame(elapsedTime, true), vec1.x, vec1.y);
            batch.end();

        }
    }