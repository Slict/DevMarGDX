/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;

/**
 *
 * @author chesj2479
 */
public class Platform {

    private Point pPosition;
    private Point pPos;
    float fWidth = 200;
    float fHeight = 44;
    int nPlatx;
    int nPlaty;
    final static TextureRegion texPlat = new TextureRegion(new Texture(Gdx.files.internal("plat.png")));

    Platform() {
    }

    Platform(Point point) {
        pPosition = point;
    }
    public Point main() {
        pPos = randomPlat();
        return pPos;
    }

    public Point randomPlat() {
        Random rn = new Random();
        nPlatx = rn.nextInt(1280) + 1;
        nPlaty = rn.nextInt(720) + 1;
        pPosition = new Point(nPlatx, nPlaty);
        return pPosition;
    }

    public float getX() {
        main();
        return pPos.x;
    }

    public float getY() {
        main();
        return pPos.y;
    }
}