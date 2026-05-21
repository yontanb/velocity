package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class checkpoint extends shape
{   int checkpointX, checkpointY;
    boolean winner;
    public checkpoint(int x, int y, int width, int height, String texture, Context context, boolean winner) {
        super(x, y, width, height, texture,context);
        this.winner = winner;
        checkpointX = x;
        checkpointY = y;
    }

    public checkpoint() {
        super();
    }

    @Override
    public boolean isWinner() {
        return winner;
    }

    public int getCheckpointY() {
        return checkpointY;
    }

    public int getCheckpointX() {
        return checkpointX;
    }

    @Override
    public void gotCheckpoint(player player) {
        if(winner)
            player.win();
        else {
            player.startX = checkpointX;
            player.startY = checkpointY;
        }
    }

}
