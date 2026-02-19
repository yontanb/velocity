package com.example.velocity4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    boolean notWon =true;
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        float playerX = player.rect.centerX();
        float playerY = player.rect.centerY();
        float screenCenterX = getWidth()  / 2f;
        float screenCenterY = getHeight() / 2f;

        float offsetX = playerX - screenCenterX;
        float offsetY = playerY - screenCenterY;

        canvas.translate(-offsetX, -offsetY);;
        lvl.drawLevel(canvas);
        invalidate();

        player.gravity();
        if(ismovingright) {
            player.moveRight();
        }
        if(ismovingleft) {
            player.moveLeft();
        }
        if(player.health == 0) {
            player.health = 100;
            GameOver();
        }
        if(lvl.didWin() && notWon) {
            win();
            notWon = false;
        }
        lvl.playerCollide();
        lvl.playerdeathcheck();
    }
    private void win() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("you beat the level!");
        builder.setMessage("try again?");
        builder.setCancelable(false);
        builder.setPositiveButton("restart", new HandleAlertDialogClickListener());
        builder.setNegativeButton("go to title screen", new HandleAlertDialogClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void GameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Game over");
        builder.setMessage("try again");
        builder.setCancelable(false);
        builder.setPositiveButton("restart", new HandleAlertDialogClickListener());
        builder.setNegativeButton("go to title screen", new HandleAlertDialogClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class HandleAlertDialogClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            String msg =  "" + which;
            Log.d("dialog",msg);
            if(which == -1 ) {
                player.death();
            }
            if(which == -2) {
                ((Activity) context).finish();
            }
        }
    }


}
