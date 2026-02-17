package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class gameView extends View {
    level lvl;
    boolean ismovingleft, ismovingright;
    player player;

    public gameView(Context context, level lvl) {
        super(context);
        this.lvl = lvl;
        this.player = lvl.player;
    }

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
        lvl.playerCollide();
        lvl.didWin();
//        lvl.playerdeathcheck();

    }
//    public boolean onTouchEvent(MotionEvent event) {
//
//        if(event. getAction() == MotionEvent.ACTION_DOWN) {
//            int x = (int)event.getX();
//            int y = (int)event.getY();
//            shape left = lvl.playr.getLayer().get(1);
//            shape right = lvl.playr.getLayer().get(2);
//            shape jump = lvl.playr.getLayer().get(3);
//            player player1 = (player) lvl.playr.getLayer().get(0);
//            if(left.isInside(x,y)) {
//                ismovingleft = true;
//            }
//            if(right.isInside(x,y)) {
//                ismovingright = true;
//            }
//            if(jump.isInside(x,y)) {
//                player1.dy = -60;
//            }
//
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP) {
//            ismovingleft = false;
//            ismovingright = false;
//        }
//        invalidate();
//        return true;
//
//    }


}
