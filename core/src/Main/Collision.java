/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author chesj2479
 */
public class Collision {

    private Main main;

    Collision() {
    }

    public boolean isHit(Sprite sprBul, Sprite sprPlat) {
        if (sprBul.getBoundingRectangle().overlaps(sprPlat.getBoundingRectangle())) {
            return true;
        } else {
            return false;
        }
    }
}
