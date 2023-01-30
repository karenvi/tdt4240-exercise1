package com.karenvi.task3b;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;
// For this task I found it easier to separate task 3 a into two files, as duplicating one helicopter in one file would be really ugly code etc.

public class MyTask3b extends ApplicationAdapter {
// Strongly inspired by this tutorial by LibGdx: https://libgdx.com/wiki/start/a-simple-game
// And this tutorial: https://happycoding.io/tutorials/libgdx/hello-world
// And this tutorial: https://libgdx.com/wiki/graphics/2d/2d-animation

	Helicopter helicopter1, helicopter2, helicopter3;
	AnimatedHelicopter heli1, heli2, heli3;
	Random rand = new Random();
	float heli1_xspeed, heli2_xspeed, heli3_xspeed;
	float heli1_yspeed, heli2_yspeed, heli3_yspeed;

	@Override
	public void create () {
		heli1 = new AnimatedHelicopter(30, 100);
		heli2 = new AnimatedHelicopter(60, 200);
		heli3 = new AnimatedHelicopter(90, 300);


	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 0, 254, 1); // the background color

		heli1_xspeed = heli1.getxSpeed();
		heli2_xspeed = heli2.getxSpeed();
		heli3_xspeed = heli3.getxSpeed();

		heli1_yspeed = heli1.getySpeed();
		heli2_yspeed = heli2.getySpeed();
		heli3_yspeed = heli3.getySpeed();

		if(heli1.helicoptersColliding(heli2.getHelicopter())) {
			heli1.setxSpeed(0-heli1.getxSpeed());
			heli1.setySpeed(0-heli1.getySpeed());
			heli2.setxSpeed(0-heli2.getxSpeed());
			heli2.setySpeed(0-heli2.getySpeed());
			heli1.setFlipHeli(true);
			heli2.setFlipHeli(true);

		}

		if(heli3.helicoptersColliding(heli1.getHelicopter())) {
			heli3.setxSpeed(0-heli3.getxSpeed());
			heli3.setySpeed(0-heli3.getySpeed());
			heli1.setxSpeed(0-heli1.getxSpeed());
			heli1.setySpeed(0-heli1.getySpeed());
			heli3.setFlipHeli(true);
			heli1.setFlipHeli(true);
		}

		if(heli2.helicoptersColliding(heli3.getHelicopter())) {
			heli2.setxSpeed(0-heli2.getxSpeed());
			heli2.setySpeed(0-heli2.getySpeed());
			heli3.setxSpeed(0-heli3.getxSpeed());
			heli3.setySpeed(0-heli3.getySpeed());
			heli2.setFlipHeli(true);
			heli3.setFlipHeli(true);
		}


		// "drawing" the helicopters
		heli1.generateAnimatedHelicopter();
		heli2.generateAnimatedHelicopter();
		heli3.generateAnimatedHelicopter();

	}

}

