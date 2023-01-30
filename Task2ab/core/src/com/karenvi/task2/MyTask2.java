package com.karenvi.task2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;


//This covers both task 2 (a) and (b)
public class MyTask2 extends ApplicationAdapter {
	// Strongly inspired by this tutorial by LibGdx: https://libgdx.com/wiki/start/a-simple-game
// And this tutorial: https://happycoding.io/tutorials/libgdx/hello-world
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture helicopterImg;
	private Rectangle helicopter;
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		helicopterImg = new Texture(Gdx.files.internal("heli1.png"));
		sprite = new Sprite(helicopterImg);
		sprite.flip(true, false);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		batch = new SpriteBatch();
		helicopter = new Rectangle();

		// the size of the heli1.png
		helicopter.width = 162;
		helicopter.height = 65;
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 0, 254, 1); // the background color
		font = new BitmapFont();
		camera.update();

		// incrementing speed at the helicopter rectangle location/pos
		if(Gdx.input.isTouched()) {
			Vector3 touchPosition = new Vector3();
			touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPosition);

//       Ensures that touch moves the sprite, but only within the screen boundaries.
			if(touchPosition.y > 65/2 && touchPosition.y < 800 - 65/2){
				helicopter.y = touchPosition.y - (65 / 2);
			}

			if(touchPosition.x > 162/2 && touchPosition.x < 480 - 162/2){
				helicopter.x = touchPosition.x - (162 / 2);
			}
		}

//    Draws the sprite continuously
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sprite, helicopter.x, helicopter.y);
		font.getData().setScale(2f);
		font.draw(batch, "X-value: " + Integer.toString(Gdx.input.getX()) + " Y-value: " + Integer.toString(Gdx.input.getY()), 10, 780);
		batch.end();
	}
}

