package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class checkpoint extends shape
{   int checkpointX, checkpointY;
    boolean winner;
    /**
     *  creates a new checkpoint
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of checkpoint
     * @param height height of checkpoint
     * @param texture texture name in drawable
     * @param context context of level
     * @param winner if finishline or not
     */
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
    /**
     *changes the spawn of the player to the checkpoint
     * @param player the player of the game
     */
    @Override
    public void gotCheckpoint(player player) {
        if(winner)
            player.win();
        else {
            player.setSpawn(checkpointX,checkpointY);
        }
    }
}
