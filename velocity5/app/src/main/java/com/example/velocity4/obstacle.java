package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class obstacle extends shape {
    int damage;

    /**
     * creates a new obstacle
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the obstacle width
     * @param height the obstacle height
     * @param texture the texture name
     * @param context the context for the obstacle
     * @param damage the damage it does to the player
     */
    public obstacle(int x, int y, int width, int height, String texture, Context context, int damage) {
        super(x, y, width, height, texture,context);
        this.damage = damage;
    }

    /**
     * empty constructor for Firebase
     */
    public obstacle() {
        super();
    }

    public int getDamage() {
        return damage;
    }

    /**
     * damages the player
     * @param player the game player
     */
    @Override
    public void damagePlayer(player player) {
        if(player.health - damage <= 0) {
            player.health = 0;
        } else  {
            player.health -= damage;
        }
        String newhp = "" + player.health;
        Log.d("dmg test", "ow damage, new health:  " + newhp);
    }




}
