package com.example.velocity4;

import android.graphics.Bitmap;
import android.util.Log;

public class checkpoint extends shape
{   int checkpointx, checkpointy;
    boolean winner;
    public checkpoint(int x, int y, int width, int height, Bitmap bitmap, boolean winner) {
        super(x, y, width, height, bitmap);
        this.winner = winner;
        checkpointx = x;
        checkpointy = y;
    }
    @Override
    public void gotCheckpoint(player player) {
        Log.d("checkpointTest","got it!");
        if(winner)
            player.win();
        else {
            player.starty = checkpointy;
            player.startx = checkpointx;
        }
    }

}
