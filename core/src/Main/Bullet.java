/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class Bullet {

    // Every bullet uses the same texture, keep a static instead of making many instances
    final static TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("laser.png")));
    private float fSpeed = 15;
    private Point fPosition;
    private float rotation;
    private float fWidth = 15;
    private float fHeight = 9;
    private int nCounter = 0;

    Bullet(float originX, float originY) {
        this(new Point(originX, originY));
    }

    Bullet(Point point) {
        fPosition = point;
        Point clickPoint = Point.fromCursor();
        rotation = MathUtils.atan2(clickPoint.getY() - point.getY(), clickPoint.getX() - point.getX()) * (180 / MathUtils.PI);
        /* if (rotation > 90) {
         rotation+=5;
         }*/

    }

    void updatePosition() {
        fSpeed += 2;
                nCounter++;

        float currentX = fPosition.getX();
        float currentY = fPosition.getY();
        float newX = currentX + MathUtils.cos(rotation / 180 * MathUtils.PI) * fSpeed;
        float newY = currentY + MathUtils.sin(rotation / 180 * MathUtils.PI) * fSpeed;
        fPosition = new Point(newX, newY);
    }

    boolean isOffScreen() {
        if (fPosition.getX() < -34 || fPosition.getX() > Gdx.graphics.getWidth()) {
            return true;
        } else if (fPosition.getY() < -34 || fPosition.getY() > Gdx.graphics.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return fPosition.getX();
    }

    float getY() {
        return fPosition.getY();
    }

    float getRotation() {
        return rotation;
    }

    float getW() {
        if (nCounter % 20 == 0){
        fWidth--;
        }
        return fWidth;
    }

    float getH() {
        fHeight += 2;
        return fHeight;
    }
}