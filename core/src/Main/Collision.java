/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author chesj2479
 */
public class Collision {

    Main main;

    Collision() {
    }

    public boolean isHit() {
        if (main.sprBul.getBoundingRectangle().overlaps(main.sprPlat.getBoundingRectangle())) {
            return true;
        } else {
            return false;
        }
    }
}
