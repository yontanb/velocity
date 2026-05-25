package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class player extends shape {
    int health;
    int dx = 20 ,dy = 10;
    int startX, startY;
    int grav = 3;
    boolean isOnground;
    public player(int x, int y, int width, int height, String texture, Context context) {
        super(x, y, width, height, texture,context);
        health = 100;
        startX = x;
        startY = y;
    }
    public player() {
        super();

    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    public void setSpawn(int x, int y) {
        this.startX = x;
        this.startY = y;
    }
    public void death() {
            health = 100;
            rect.offsetTo(startX, startY);

    }
    public void win() {
    }
    boolean canUp = true , canRight = true , canLeft = true , canDown = true;
    public void moveLeft() {
        if(canLeft) {
            rect.offset(-dx, 0);

        }
    }
    public void moveRight() {
        if(canRight)
            rect.offset(dx, 0);
    }
    public void gravity() {
        Log.d("dy", String.valueOf(dy));
        rect.offset(0,dy);
        if(dy > 67) {
            dy = 67;
        } else {
            dy += grav;
        }
    }



}
