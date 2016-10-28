package Main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    Sprite sprBul;
    Sprite sprPlat;
    private Texture texBG;
    private Texture texPlat;
    private TextureRegion texRegion;
    private int bulletTimer = 0;
    private ArrayList<Bullet> bulletArrayList = new ArrayList<Bullet>();
    private ArrayList<Platform> platformArrayList = new ArrayList<Platform>();
    Point platPoint;
    Collision collision = new Collision();

    @Override
    public void create() {
        texBG = new Texture(Gdx.files.internal("mkombat.jpg"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(100, 100, 100, 20);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texBG, 0, 0, 1280, 720);
        Platform plat = new Platform();
        MainCharacter mainchar = MainCharacter.shared;
        Point mainCharPos = mainchar.getPosition();
        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            bulletTimer++;
            if (bulletTimer % 5 == 0) {
                bulletArrayList.add(new Bullet(mainCharPos));
                System.out.println("add");
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.P)) {
            platPoint = plat.randomPlat();
            platformArrayList.add(new Platform(platPoint));
        }
        for (Platform plat1 : platformArrayList) {
            sprPlat = new Sprite(plat.texPlat);
            batch.draw(sprPlat, platPoint.x, platPoint.y);
        }
        ArrayList<Bullet> newBulletArrayList = new ArrayList<Bullet>();
        for (Bullet bullet : bulletArrayList) {
            bullet.updatePosition();
            sprBul = new Sprite(Bullet.texture);
            Iterator<Bullet> iter = bulletArrayList.iterator();

            while (iter.hasNext()) {
                if (!bullet.isOffScreen() && !sprBul.getBoundingRectangle().overlaps(sprPlat.getBoundingRectangle())) {
                    newBulletArrayList.add(bullet);
                    batch.draw(sprBul,
                            bullet.getX(), // X-coord of bottom left
                            bullet.getY(), // Y-coord of bottom left
                            25 / 2, // Starting X
                            25 / 2, // Starting Y
                            bullet.getW(), // Width of sprite
                            bullet.getH(), // Height of sprite
                            1, // X scale
                            1, // Y scale
                            bullet.getRotation() // Rotation in degrees
                            );
                    System.out.println(bullet.getX() + " " + bullet.getY());
                } else {
                    System.out.println("Hit");
                }
            }
        }
        bulletArrayList = newBulletArrayList;
        mainchar.boundaries(mainCharPos);
        texRegion = mainchar.drawMove();
        batch.draw(texRegion, mainCharPos.x, mainCharPos.y);
        bulletArrayList = newBulletArrayList;
        batch.end();

    }
}
