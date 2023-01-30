package com.karenvi.task1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class MyTask1 extends ApplicationAdapter {
	// Strongly inspired by this tutorial by LibGdx: https://libgdx.com/wiki/start/a-simple-game
// And this tutorial: https://happycoding.io/tutorials/libgdx/hello-world
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture helicopterImg;
	private Rectangle helicopter;
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
	private float xSpeed = 2;
	private float ySpeed = 1;
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

		camera.update();

		// incrementing speed at the helicopter rectangle location/pos
		helicopter.x += xSpeed;
		helicopter.y += ySpeed;

		// Senses the screen boundaries (minus the width and height of the helicopter, respectively)
		// the arithmetic is width/height of the predefined screen minus the width/height of the sprite
		// had to do it both times so it can be used both on desktop and mobile
		if(helicopter.x < 0 || helicopter.x > Gdx.graphics.getWidth() - 162 ||helicopter.x > 480 - 162){
			xSpeed *= -1;
			sprite.flip(true, false);
		}

		if(helicopter.y < 0 || helicopter.y > Gdx.graphics.getHeight() - 65 || helicopter.y > 800-65){
			ySpeed *= -1;
		}

//    Draws the sprite continuously
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sprite, helicopter.x, helicopter.y);
		batch.end();
	}
}