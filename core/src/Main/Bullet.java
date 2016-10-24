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
    private Point pPosition;
    private float rotation;
    private float fWidth = 15;
    private float fHeight = 9;

    Bullet(float originX, float originY) {
        this(new Point(originX, originY));
    }

    Bullet(Point point) {
        pPosition = point;
        Point clickPoint = Point.fromCursor();
        rotation = MathUtils.atan2(clickPoint.getY() - point.getY(), clickPoint.getX() - point.getX()) * (180 / MathUtils.PI);
        /* if (rotation > 90) {
         rotation+=5;
         }*/

    }

    void updatePosition() {
        fSpeed += 2;
        float currentX = pPosition.getX();
        float currentY = pPosition.getY();
        float newX = currentX + MathUtils.cos(rotation / 180 * MathUtils.PI) * fSpeed;
        float newY = currentY + MathUtils.sin(rotation / 180 * MathUtils.PI) * fSpeed;
        pPosition = new Point(newX, newY);
    }

    boolean isOffScreen() {
        if (pPosition.getX() < -34 || pPosition.getX() > Gdx.graphics.getWidth()) {
            return true;
        } else if (pPosition.getY() < -34 || pPosition.getY() > Gdx.graphics.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return pPosition.getX();
    }

    float getY() {
        return pPosition.getY();
    }

    float getRotation() {
        return rotation;
    }

    float getW() {
        fWidth -= 0.25;
        return fWidth;
    }

    float getH() {
        fHeight += 2;
        return fHeight;
    }
}