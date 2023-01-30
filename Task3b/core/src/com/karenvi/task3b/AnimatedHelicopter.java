package com.karenvi.task3b;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;
// realised I could have used vector3 to use velocity when it was too late...
public class AnimatedHelicopter extends ApplicationAdapter {
// Strongly inspired by this tutorial by LibGdx: https://libgdx.com/wiki/start/a-simple-game
// And this tutorial: https://happycoding.io/tutorials/libgdx/hello-world
// And this tutorial: https://libgdx.com/wiki/graphics/2d/2d-animation

    // Constant of rows and columns in the sprite sheet (heli_sprite_sheet.png)
    private static final int sprite_sheet_cols = 4, sprite_sheet_rows = 1;

    private OrthographicCamera camera;
    private Animation<TextureRegion> heliAnimation;
    private Texture helicopterImg;
    private Rectangle helicopter;
    private SpriteBatch sb;
    float startPointX, startPointY;
    Random rand = new Random();
    private float xSpeed = rand.nextFloat();
    private float ySpeed = rand.nextFloat();
    private boolean flipHeli = false;

    // Tracking elapsed time
    private float stateTime;

    public AnimatedHelicopter(float startPointX, float startPointY) {
        int index = 0;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        helicopterImg = new Texture(Gdx.files.internal("heli_sprite_sheet.png"));

        // creating the textureregion for the sprite sheet, which helps divide into the individual frames
        TextureRegion[][] helicopter_rows_cols = TextureRegion.split(helicopterImg, helicopterImg.getWidth() / sprite_sheet_cols, helicopterImg.getHeight() / sprite_sheet_rows);
        TextureRegion[] helicopter_frames = new TextureRegion[sprite_sheet_cols * sprite_sheet_rows];
        heliAnimation = new Animation<TextureRegion>(0.1f, helicopter_frames);
        sb = new SpriteBatch();
        stateTime = 0f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        helicopter = new Rectangle(startPointX, startPointY, helicopterImg.getWidth()/sprite_sheet_cols, helicopterImg.getHeight()/sprite_sheet_rows);

        for(int i = 0; i < sprite_sheet_rows; i++){
            for(int j = 0; j < sprite_sheet_cols; j++){
                helicopter_frames[index++] = helicopter_rows_cols[i][j];
            }
        }

        for (TextureRegion textureRegion:heliAnimation.getKeyFrames()) {
            textureRegion.flip(true, false);
        }
    }

    public void generateAnimatedHelicopter() {
        // incrementing speed at the helicopter rectangle location/pos
        helicopter.x += xSpeed;
        helicopter.y += ySpeed;

//        ScreenUtils.clear(255, 0, 254, 1); // the background color
        camera.update();

        // needed for the animation
        stateTime += Gdx.graphics.getDeltaTime();

        // extracts the snapshot of each frame in the sprite sheet, and looping it
        TextureRegion currentFrame = heliAnimation.getKeyFrame(stateTime, true);

        // make sure that the helicopter doesn't go outside bounds
        if(helicopter.x < 0 || helicopter.x > Gdx.graphics.getWidth() - 162){
            xSpeed *= -1;
            // flipping it <3
            for (TextureRegion textureRegion:heliAnimation.getKeyFrames()) {
                textureRegion.flip(true, false);
            }
        }

        if(helicopter.y < 0 || helicopter.y > Gdx.graphics.getHeight() - 65){
            ySpeed *= -1;
        }

        if(flipHeli){
            for (TextureRegion textureRegion:heliAnimation.getKeyFrames()) {
                textureRegion.flip(true, false);
            }
            setFlipHeli(false);
        }

        // rendering to desktop/mobile
        sb.begin();
        sb.draw(currentFrame, helicopter.x, helicopter.y);
        sb.end();
    }

    // always have to dispose textures and sprite batches
    @Override
    public void dispose() {
        sb.dispose();
        helicopterImg.dispose();
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Rectangle getHelicopter() {
        return helicopter;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    // make helicopters flip if they collide so they drive to the right side
    public void setFlipHeli(boolean flipHeli) {
        this.flipHeli = flipHeli;
    }

    public boolean helicoptersColliding(Rectangle otherHeli) {
        if(Intersector.overlaps(helicopter, otherHeli)){
            return true;
        } else{
            return false;
        }
    }
}
