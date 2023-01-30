package com.karenvi.task4.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

// This class creates the Pongball, which is used in PlayState
public class PongBall {
    private final ShapeRenderer pongBallShape;
    float pongBallPosX, pongBallPosY;
    private float xSpeed, ySpeed;
    private final Circle pongBall;

    public PongBall(){
        pongBallPosX = Gdx.graphics.getWidth()/2f;
        pongBallPosY = Gdx.graphics.getHeight()/2f;
        pongBallShape = new ShapeRenderer();
        xSpeed = 1;
        ySpeed = 1;
        pongBall = new Circle(pongBallPosX, pongBallPosY, 10);
    }

    public void createPongBall(){
        pongBall.setPosition(pongBallPosX, pongBallPosY);
        pongBallShape.begin(ShapeRenderer.ShapeType.Filled);
        pongBallShape.setColor(Color.MAGENTA);
        pongBallShape.circle(pongBallPosX, pongBallPosY, 10);
        pongBallShape.end();
    }

    public void moveBall(){
        pongBallPosX += xSpeed;
        pongBallPosY += ySpeed;
    }

    public void screenBoundaries(){
        // ensures that the ball doesn't disappear outside the screen (the speed makes it bounce back)
        if(pongBallPosY - 10 < 0 || pongBallPosY + 10 > Gdx.graphics.getHeight()){
            ySpeed *= -1;
        }
    }

    public float getPongBallPosX() {
        return pongBallPosX;
    }

    public void setPongBallPosX(float pongBallPosX) {
        this.pongBallPosX = pongBallPosX;
    }

    public void setPongBallPosY(float pongBallPosY) {
        this.pongBallPosY = pongBallPosY;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Circle getPongBall() {
        return pongBall;
    }


}

