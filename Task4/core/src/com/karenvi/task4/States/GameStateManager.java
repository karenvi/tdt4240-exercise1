package com.karenvi.task4.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Brent on 6/25/2015.
 */
// This file is borrowed from Brent Aureli: https://github.com/BrentAureli/FlappyDemo/blob/master/core/src/com/brentaureli/flappybird/States/GameStateManager.java
    // Since its a good way of organizing the states, and does not really affect the task itself
// State structure inspired by Brent Aureli: https://github.com/BrentAureli/FlappyDemo/tree/master/core/src/com/brentaureli/flappybird/States
public class GameStateManager {
    private final Stack<State> states;

    public GameStateManager(){
        states = new Stack<>();
    }

    public void push(State state){
        states.push(state);
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}