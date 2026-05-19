package com.example.velocity4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;

public class gameView extends View {
    level lvl;
    boolean ismovingleft, ismovingright;
    player player;
    Context context;
    public gameView(Context context, level lvl) {
        super(context);
        this.context = context;
        this.lvl = lvl;
        this.player = lvl.player;
        
    }
    boolean notWon = true;
    float offsetX;
    float offsetY;
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        float playerX = player.rect.centerX();
        float playerY = player.rect.centerY();
        float screenCenterX = getWidth()  / 2f;
        float screenCenterY = getHeight() / 2f;
        float offsetX = screenCenterX - playerX;
        float offsetY = screenCenterY - playerY;
        canvas.translate(offsetX,offsetY);
        Griddots(canvas);
        lvl.drawLevel(canvas);
        lvl.healthDisplay(context);
//        long now = System.nanoTime();
//        float dt = (now - lastTime) / 1_000_000_000f;
//        lastTime = now;
//        player.dt = dt;
        player.gravity();
        if(ismovingright) {
            player.moveRight();
        }
        if(ismovingleft) {
            player.moveLeft();
        }
        if(player.health == 0) {
            player.death();
            player.health = 100;
        }
        if(lvl.didWin() && notWon) {
            notWon = false;
            win();
        }

        lvl.playerCollide();
        lvl.playerdeathcheck();
        invalidate();
    }
    private void Griddots(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        float leftcorner = - offsetX;
        float topcorner = -offsetY;
        float startX = (float)Math.floor(leftcorner / 100) * 100;
        float startY = (float)Math.floor(topcorner / 100) * 100;

        for (float x = startX; x <= leftcorner + getWidth(); x += 100) {
            for (float y = startY; y <= topcorner + getHeight(); y += 100) {
                canvas.drawPoint(x,y,paint);
            }
        }

    }
    private void win() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("you beat the level!");
        builder.setMessage("try again?");
        builder.setCancelable(false);
        builder.setPositiveButton("restart?", new HandleAlertDialogClickListener());
        builder.setNegativeButton("go to title screen", new HandleAlertDialogClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();

    }
//    private void GameOver() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        builder.setTitle("Game over");
//        builder.setMessage("try again");
//        builder.setCancelable(false);
//        builder.setPositiveButton("restart", new HandleAlertDialogClickListener());
//        builder.setNegativeButton("go to title screen", new HandleAlertDialogClickListener());
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    private class HandleAlertDialogClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            String msg =  "" + which;
            Log.d("dialog",msg);
            if(which == -1 ) {
                player.death();
                notWon = true;
            }
            if(which == -2) {
                ((Activity) context).finish();
            }
        }
    }


}
