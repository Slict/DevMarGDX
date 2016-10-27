/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author chesj2479
 */
public class Collision {

    Platform plat = new Platform();

    public boolean isHit(float fObj1x, float fObj1y, float fObj2x, float fObj2y) {
        if (fObj2x < fObj1x && fObj1x < fObj2x + plat.fWidth && fObj2y < fObj1y && fObj1y < fObj2y + plat.fHeight) {
            return false;
        } else {
            return true;
        }
    }
}
