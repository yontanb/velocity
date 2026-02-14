package com.example.velocity4;

import android.content.Context;
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

    boolean ismovingleft, ismovingright, ismovingUp,ismovingdown;


    public gameView(Context context, level lvl) {
        super(context);
        this.lvl = lvl;
        player = (player)lvl.playr.getLayer().get(0);
    }

    public gameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.lvl = lvl;
    }

    player player;
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        float playerX = player.rect.left + player.rect.width();
        float playerY = player.rect.top;
        float screenCenterX = getWidth()  / 2f;
        float screenCenterY = getHeight() / 2f;

        float offsetX = playerX - screenCenterX;
        float offsetY = playerY - screenCenterY;

        canvas.save();
        canvas.translate(-offsetX, -offsetY);;
        lvl.drawLevel(canvas);
        invalidate();
        player = (player)lvl.playr.getLayer().get(0);
        player.gravity();
        if(ismovingright) {
            player.moveRight();
        }
        if(ismovingleft) {
            player.moveLeft();
        }
        lvl.playerCollide();
        lvl.playerdeathcheck();
        canvas.restore();
    }

//    int x,y,x2,y2;
//    int taps = 0;
//    @Override

//    public boolean onTouchEvent(MotionEvent event) {
//
//        if(event. getAction() == MotionEvent.ACTION_DOWN) {
//            int x = (int)event.getX();
//            int y = (int)event.getY();
//            shape left = lvl.playr.getLayer().get(1);
//            shape right = lvl.playr.getLayer().get(2);
//            shape up = lvl.playr.getLayer().get(3);
//
//
//            player = (player) lvl.playr.getLayer().get(0);
//            if(left.isInside(x,y)) {
//                ismovingleft = true;
//            }
//            if(right.isInside(x,y)) {
//                ismovingright = true;
//            }
//            if(up.isInside(x,y)) {
//                player.dy = -60;
//            }
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP) {
//            ismovingleft = false;
//            ismovingright = false;
//
//        }
//        invalidate();
//        return true;
//
//    }


}
