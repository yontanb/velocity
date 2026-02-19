package com.example.velocity4;

import android.graphics.Bitmap;

public class player extends shape {
    int health;
    int dx = 20 ,dy = 10;
    int startX, startY;
    public player(int x, int y, int width, int height, Bitmap bitmap) {
        super(x, y, width, height, bitmap);
        health = 100;
        startX = x;
        startY = y;
    }
    public void death() {

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
    int grav = 3;
    boolean isTooFast = false;
    public void gravity() {
        if((canDown && dy > 0) || (canUp && dy < 0)) {
            rect.offset(0,dy);
        } else {
            dy = 0;

        }
        if(dy > 60) {
            dy = 60;

        } else {
            dy += grav;
        }
    }



}
