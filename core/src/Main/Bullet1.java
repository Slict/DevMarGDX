///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.MathUtils;
////Bullet with trig (From Albert)
//public class Bullet1 {
//
//    Vector2 nLoc;
//    Vector2 vPlayer, vClick;
//    float fX, fY, fRot, fSpeed, fRotation;
//    Texture imgBul = new Texture(Gdx.files.internal("bulletsprite.png"));
//    TextureRegion imgOut = new TextureRegion(imgBul);
//    CharMain charpos;
//    public Bullet1(float nX, float nY) {
//        nLoc = new Vector2(nX, nY);
//        fX = Gdx.input.getX();
//        fY = (Gdx.input.getY() - 500) * -1;
//        Vector2 vPlayer = charpos.getPos();
//        Vector2 vClick = new Vector2(Gdx.input.getX(),(Gdx.input.getY() - 500) * -1);
//        fRot = MathUtils.atan2(fY - nLoc.y, fX - nLoc.x) / MathUtils.PI * 180;
//        fSpeed = 10;
//        fRotation = MathUtils.PI * fRot / 180;
//    }
//
//    public void update() {
//        vClick.x += (vClick.x-vPlayer.x);
//        vClick.y+=(vClick.y-vPlayer.y);
//    }
//
//    public boolean isOffScreen() {
//        if (nLoc.x + 25 < 0 || nLoc.x > Gdx.graphics.getWidth()) {
//            System.out.println("removed");
//            return true;
//        }
//        if (nLoc.y + 25 < 0 || nLoc.y > Gdx.graphics.getHeight()) {
//            System.out.println("removed");
//            return true;
//        }
//        return false;
//    }
//}
