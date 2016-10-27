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

    Collision() {
    }

    public boolean isHit(float fObj1x, float fObj1y, float fObj2x, float fObj2y) {
        Platform plat = new Platform();
        if (fObj2x < fObj1x && fObj1x < fObj2x + plat.fWidth && fObj2y < fObj1y && fObj1y < fObj2y + plat.fHeight) {
            return true;
        } else {
            return false;
        }
    }
}
