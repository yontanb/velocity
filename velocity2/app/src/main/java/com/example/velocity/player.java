package com.example.velocity;

import android.graphics.Bitmap;

public class player extends shape {
    int health;
    int dx = 10 ,dy = 10;
    public player(int x, int y, int width, int height, Bitmap bitmap) {
        super(x, y, width, height, bitmap);
        health = 100;
    }
    public void damagePlayer( int dmg) {
        if(health - dmg <=0) {
            health = 0;
        }
        health -= dmg;
    }
    public boolean Isdead() {
        return health == 0;
    }
    public void moveLeft() {
        rect.left -= dx;
        rect.right -= dx;
    }
    public void moveRight() {
        rect.left += dx;
        rect.right += dx;
    }
    public void moveUp() {
        rect.bottom += dy;
        rect.top += dy;
    }
    public void moveDown() {
        rect.bottom -= dy;
        rect.top -= dy;
    }


}
