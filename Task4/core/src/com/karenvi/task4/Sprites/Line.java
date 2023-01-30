package com.karenvi.task4.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
// The line in the middle that helps player see where the ball is
public class Line {
    private final float shapePosX, shapePosY;
    private final Rectangle line;
    private final ShapeRenderer lineRectangle;

    public Line(float shapePosX, float shapePosY){
        this.shapePosX = shapePosX;
        this.shapePosY = shapePosY;
        lineRectangle = new ShapeRenderer();
        line = new Rectangle(shapePosX, shapePosY, 10, Gdx.graphics.getHeight());
    }

    public void createLine(){
        line.setPosition(shapePosX, shapePosY);
        lineRectangle.begin(ShapeRenderer.ShapeType.Filled);
        lineRectangle.setColor(Color.ROYAL);
        lineRectangle.rect(shapePosX, shapePosY, 10, Gdx.graphics.getHeight());
        lineRectangle.end();
    }
}

