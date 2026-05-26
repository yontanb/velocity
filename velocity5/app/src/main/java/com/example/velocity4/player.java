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
    /**
     *  creates a new player, saves the starting position
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of player
     * @param height height of player
     * @param texture texture name in drawable
     * @param context context of level
     */
    public player(int x, int y, int width, int height, String texture, Context context) {
        super(x, y, width, height, texture,context);
        health = 100;
        startX = x;
        startY = y;
    }

    /**
     * empty constructor for firebase
     */
    public player() {
        super();

    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    /**
     * sets the spawn of the player
     * @param x the new spawn X
     * @param y the new spawn Y
     */
    public void setSpawn(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    /**
     * kills the player and returns to spawn
     */
    public void death() {
            health = 100;
            rect.offsetTo(startX, startY);

    }

    /**
     * win event
     */
    public void win() {
    }
    /**
     * moves the player left
     */
    public void moveLeft() {
        if(canLeft) {
            rect.offset(-dx, 0);

        }
    }
    /**
     * moves the player right
     */
    public void moveRight() {
        if(canRight)
            rect.offset(dx, 0);
    }
    boolean canUp = true , canRight = true , canLeft = true , canDown = true;

    /**
     * applies gravity to the player
     */
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
