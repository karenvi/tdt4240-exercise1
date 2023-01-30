package com.karenvi.task4.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// State structure inspired by Brent Aureli: https://github.com/BrentAureli/FlappyDemo/tree/master/core/src/com/brentaureli/flappybird/States
public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera cam;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
}