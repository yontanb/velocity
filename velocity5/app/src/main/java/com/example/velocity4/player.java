package com.example.velocity4;

import android.graphics.Bitmap;

public class player extends shape {
    int health;
    int dx = 20 ,dy = 10;
    int startx, starty;
    public player(int x, int y, int width, int height, Bitmap bitmap) {
        super(x, y, width, height, bitmap);
        health = 100;
        startx = x;
        starty = y;
    }
    public void damagePlayer( int dmg) {
        if(health - dmg <=0) {
            health = 0;
        }
        health -= dmg;
    }
    public void death() {

            health = 100;
            rect.offsetTo(startx,starty);

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
