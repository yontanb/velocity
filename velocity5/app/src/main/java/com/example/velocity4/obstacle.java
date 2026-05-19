package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class obstacle extends shape {
    int damage;
    public obstacle(int x, int y, int width, int height, String texture, Context context, int damage) {
        super(x, y, width, height, texture,context);
        this.damage = damage;
    }

    public obstacle() {
        super();
    }

    public int getDamage() {
        return damage;
    }

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
