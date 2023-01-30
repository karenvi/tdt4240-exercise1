package com.karenvi.task4.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

// State structure inspired by Brent Aureli: https://github.com/BrentAureli/FlappyDemo/tree/master/core/src/com/brentaureli/flappybird/States
public class MenuState extends State{
    private final BitmapFont font = new BitmapFont();
    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        ScreenUtils.clear(0,0,0, 1); // the background color
        sb.begin();
        font.getData().setScale(2f);
        font.draw(sb, "PONG GAME", Gdx.graphics.getWidth()/2f-100, Gdx.graphics.getHeight()/1.5f);
        font.getData().setScale(1f);
        font.draw(sb,"Player 1 uses W and S keys to move paddle.", Gdx.graphics.getWidth()/2f-200, Gdx.graphics.getHeight()/1.7f);
        font.draw(sb,"Player 2 uses the up and down arrow keys to move paddle.", Gdx.graphics.getWidth()/2f-200, Gdx.graphics.getHeight()/2f);
        font.draw(sb,"First one to 21 points wins!", Gdx.graphics.getWidth()/2f-200, Gdx.graphics.getHeight()/2.3f);
        font.draw(sb,"Click anywhere to start game!", Gdx.graphics.getWidth()/2f-200, Gdx.graphics.getHeight()/2.6f);
        sb.end();
    }
}