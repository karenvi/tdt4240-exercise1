package com.karenvi.task4.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.karenvi.task4.Sprites.Line;
import com.karenvi.task4.Sprites.Paddle;
import com.karenvi.task4.Sprites.PongBall;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class PlayState extends State {
    // Inspired by the following video https://youtu.be/QAI-zRLsU7Y
    // State structure inspired by Brent Aureli: https://github.com/BrentAureli/FlappyDemo/tree/master/core/src/com/brentaureli/flappybird/States

    int player1_score, player2_score; // the score of player 1 & 2
    boolean gameover; // when this is true, the game is over
    private final Paddle paddle1, paddle2;
    private final PongBall pongBall;
    private final Circle pongBallOutline;
    private final Line line;
    private final Random rand;
    private final BitmapFont font;
    private int timeSpent = 0;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        player1_score = 0;
        player2_score = 0;

        font = new BitmapFont();
        gameover = false;

        paddle1 = new Paddle(0, Gdx.graphics.getHeight()/2f);
        paddle2 = new Paddle(Gdx.graphics.getWidth()-10, Gdx.graphics.getHeight() / 2f);
        pongBall = new PongBall();
        line = new Line(Gdx.graphics.getWidth()/2f-10, 0);
        pongBallOutline = pongBall.getPongBall();
        rand = new Random();
    }

    @Override
    public void handleInput() {
        // Move paddle on input for player 1
        // Player 1 is on the left side, as the keys W and S are also on the left side, its more natural that they have
        // these keys. It moves the paddle 5px up/down for each click
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(paddle1.getShapePosY() + 100 < Gdx.graphics.getHeight()){
                paddle1.setShapePosY(paddle1.getShapePosY() + 5);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if(paddle1.getShapePosY()  > 0){
                paddle1.setShapePosY(paddle1.getShapePosY() - 5);
            }
        }

        // Move paddle on input for player 2
        // Player 2 is on the right side
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(paddle2.getShapePosY() + 100 < Gdx.graphics.getHeight()){
                paddle2.setShapePosY(paddle2.getShapePosY() + 5);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(paddle2.getShapePosY() > 0){
                paddle2.setShapePosY(paddle2.getShapePosY() - 5);
            }
        }

        // Make sure when paddle and ball collides, the ball bounces back in the opposite direction
        if(paddle1.collisionDetection(pongBallOutline)){
            pongBall.setySpeed(rand.nextInt(12));
            pongBall.setxSpeed(5);
        }

        if(paddle2.collisionDetection(pongBallOutline)){
            pongBall.setySpeed(rand.nextInt(12));
            pongBall.setxSpeed(-5);


        }

        // Ensure that the ball moves back to the middle when it goes outside screen, also giving points when
        if(pongBall.getPongBallPosX() < 0f){
            pongBall.setPongBallPosX(Gdx.graphics.getWidth()/2f);
            pongBall.setPongBallPosY(Gdx.graphics.getHeight()/2f);
            player2_score += 1; // when paddle1 cant take the ball, paddle2 gets point

        }

        else if(pongBall.getPongBallPosX() > Gdx.graphics.getWidth()) {
            pongBall.setPongBallPosX(Gdx.graphics.getWidth()/2f);
            pongBall.setPongBallPosY(Gdx.graphics.getHeight()/2f);
            player1_score += 1; // when paddle2 cant take the ball, paddle1 gets point
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        // check if we have a winner yet... and change state if we have one!
        if(player1_score >= 21 || player2_score >= 21) {
            gameover = true;
            cam.update();
        }

        // If no one "scores", the speed will increase after about 1000 renders (there are 60fps)
        if(timeSpent>=1000 && player1_score <=0 && player2_score <=0){
            pongBall.setxSpeed(5);
            pongBall.setySpeed(5);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        timeSpent+=1;
//        If the game is over, display final results, if not, continue render game
        if(gameover) {
            ScreenUtils.clear(0, 0, 0, 1); // the background color
            if (player1_score >= 21) {
                sb.begin();
                font.draw(sb, "Congratulations, player 1! You won!", Gdx.graphics.getWidth()/2f-110, Gdx.graphics.getHeight()/2f);
                sb.end();
            } else {
                sb.begin();
                font.draw(sb, "Congratulations, player 2! You won!", Gdx.graphics.getWidth()/2f-110, Gdx.graphics.getHeight()/2f);
                sb.end();
            }
        } else {
            ScreenUtils.clear(0, 0, 0, 1); // the background color

            line.createLine();
            paddle1.createPaddle();
            paddle2.createPaddle();
            pongBall.createPongBall();
            pongBall.screenBoundaries();
            pongBall.moveBall();

            sb.begin();
            // The display of each player's score
            font.draw(sb, "Player 1 score: " + player1_score, Gdx.graphics.getWidth()/2f-350, Gdx.graphics.getHeight()-10);
            font.draw(sb, "Player 2 score: " + player2_score, Gdx.graphics.getWidth()/2f+250, Gdx.graphics.getHeight()-10);
            sb.end();
        }
    }
}