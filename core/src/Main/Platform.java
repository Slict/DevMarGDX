/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author chesj2479
 */
public class Platform {
        final static TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("plat.png")));
        private float fWidth = 200;
        private float fHeight = 44;
        
        
        Platform () {
            
        }
}
