package Main;

import com.badlogic.gdx.Gdx;

/**
 * Created by Brandy on 2016-10-22.
 */

public class Point {

    float x;
    float y;

    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    static Point fromCursor() {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        return new Point(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
