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
    final static TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("bulletsprite.png")));

    private final static float speed = 10;
    private Point position;
    private float rotation;

    Bullet(float originX, float originY) {
        this(new Point(originX, originY));
    }

    Bullet(Point point) {
        position = point;
        Point clickPoint = Point.fromCursor();
        rotation = MathUtils.atan2(clickPoint.getY() - point.getY(), clickPoint.getX() - point.getX()) * (180 / MathUtils.PI);
        /* if (rotation > 90) {
            rotation+=5;
        }*/

    }

    void updatePosition() {
        float currentX = position.getX();
        float currentY = position.getY();

        float newX = currentX + MathUtils.cos(rotation / 180 * MathUtils.PI) * speed;
        float newY = currentY + MathUtils.sin(rotation / 180 * MathUtils.PI) * speed;
        position = new Point(newX, newY);
    }

    boolean isOffScreen() {
        if (position.getX() < -34 || position.getX() > Gdx.graphics.getWidth()) {
            return true;
        } else if (position.getY() < -34 || position.getY() > Gdx.graphics.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return position.getX();
    }

    float getY() {
        return position.getY();
    }

    float getRotation() {
        return rotation;
    }
}