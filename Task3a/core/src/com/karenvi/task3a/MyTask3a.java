package com.karenvi.task3a;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class MyTask3a extends ApplicationAdapter {
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

	private float xSpeed = 4;
	private float ySpeed = 3;

	// Tracking elapsed time
	private float stateTime;

	@Override
	public void create () {
		int index = 0;
		helicopterImg = new Texture(Gdx.files.internal("heli_sprite_sheet.png"));

		// creating the textureregion for the sprite sheet, which helps divide into the individual frames
		TextureRegion[][] helicopter_rows_cols = TextureRegion.split(helicopterImg, helicopterImg.getWidth() / sprite_sheet_cols, helicopterImg.getHeight() / sprite_sheet_rows);
		TextureRegion[] helicopter_frames = new TextureRegion[sprite_sheet_cols * sprite_sheet_rows];
		heliAnimation = new Animation<TextureRegion>(0.1f, helicopter_frames);
		sb = new SpriteBatch();
		stateTime = 0f;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		helicopter = new Rectangle();
		// the size of the heli1.png
		helicopter.width = 162;
		helicopter.height = 65;

		for(int i = 0; i < sprite_sheet_rows; i++){
			for(int j = 0; j < sprite_sheet_cols; j++){
				helicopter_frames[index++] = helicopter_rows_cols[i][j];
			}
		}

		for (TextureRegion textureRegion:heliAnimation.getKeyFrames()) {
			textureRegion.flip(true, false);
		}
	}

	@Override
	public void render () {
		// incrementing speed at the helicopter rectangle location/pos
		helicopter.x += xSpeed;
		helicopter.y += ySpeed;

		ScreenUtils.clear(255, 0, 254, 1); // the background color
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
}

