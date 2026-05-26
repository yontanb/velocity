package com.example.velocity4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;


import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;

public class gameView extends View {
    level lvl;
    boolean ismovingleft, ismovingright, notWon = true;
    player player;
    Context context;
    boolean pause;
    boolean jumped;
    Paint paint;

    /**
     * creates a new gameView and initializes variables
     * @param context the context of the gameView
     * @param lvl the level were playing
     */
    public gameView(Context context, level lvl) {
        super(context);
        this.context = context;
        this.lvl = lvl;
        this.player = lvl.player;
        pause = false;
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
    }
    float offsetX;
    float offsetY;

    /**
     * draws the level and handles camera translation
     * @param canvas the canvas on which the the level is drawn
     */
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        float playerX = player.rect.centerX();
        float playerY = player.rect.centerY();
        float screenCenterX = getWidth() / 2f;
        float screenCenterY = getHeight() / 2f;
        float offsetX = screenCenterX - playerX;
        float offsetY = screenCenterY - playerY;

        canvas.translate(offsetX, offsetY);
        lvl.drawLevel(canvas);
        lvl.healthDisplay(context);
        updateGame();
        invalidate();
    }

    /**
     * updates the game events, like collisions and damage and stuff
     */
    public void updateGame() {
        if (!pause) {

            if (!(lvl.player.isOnground)) {
                player.gravity();
            }
            if (ismovingright) player.moveRight();
            if (ismovingleft) player.moveLeft();

            lvl.playerCollide();

            if (jumped) {
                if (lvl.player.isOnground) {
                    lvl.player.dy = -60;
                    lvl.player.isOnground = false;
                    lvl.player.canDown = true;
                    jumped = false;
                }

            }
            lvl.gotCheckpoint();
            lvl.playerdamage();

            if (player.health == 0) {
                player.death();
                player.health = 100;
            }
            if (lvl.didWin() && notWon) {
                notWon = false;
                win();
            }
        }
    }

    /**
     * draws the background for the level
     * @param canvas the canvas to draw the background on
     */
    private void Griddots(Canvas canvas) {
        float leftcorner = -offsetX;
        float topcorner = -offsetY;
        float startX = (float)Math.floor(leftcorner / 100) * 100;
        float startY = (float)Math.floor(topcorner / 100) * 100;

        for (float x = startX; x <= leftcorner + getWidth(); x += 100) {
            for (float y = startY; y <= topcorner + getHeight(); y += 100) {
                canvas.drawPoint(x + offsetX,y + offsetY,paint);
            }
        }
    }
    OnWinListener onWinListener;

    /**
     * win event
     */
    private void win() {
        onWinListener.OnWin();
    }

    /**
     * sets a listener that triggers when the player wins so it can handle it
     * @param onWinListener the listener so it can detect the win
     */
    public void setOnWinListener(OnWinListener onWinListener) {
        this.onWinListener = onWinListener;
    }

    /**
     * toggles the game updates
     */
    public void togglePause() {
        pause = !pause;
    }
}
