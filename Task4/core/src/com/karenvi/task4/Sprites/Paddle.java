package com.karenvi.task4.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    private final Rectangle paddle;
    private final ShapeRenderer paddleShape;
    private final float shapePosX;
    private float shapePosY;


    public Paddle(float shapePosX, float shapePosY){
        this.shapePosX = shapePosX;
        this.shapePosY = shapePosY;
        paddleShape = new ShapeRenderer();
        paddle = new Rectangle(shapePosX, shapePosY, 10, 100);
    }

    public void createPaddle(){
        paddle.setPosition(shapePosX, shapePosY);
        paddleShape.begin(ShapeRenderer.ShapeType.Filled);
        paddleShape.setColor(Color.WHITE);
        paddleShape.rect(shapePosX, shapePosY, 10, 100);
        paddleShape.end();
    }

    public float getShapePosY() {
        return shapePosY;
    }

    public void setShapePosY(float shapePosY) {
        this.shapePosY = shapePosY;
    }

    public boolean collisionDetection(Circle ballCircle) {
        return Intersector.overlaps(ballCircle, paddle);
    }
}

