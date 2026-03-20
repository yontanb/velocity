package com.example.velocity4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class editView extends View {
    Context context;
    level levelEditing;
    float cameraX = 0, cameraY = 0;
    /*
    camera movement for checking:
    [0] -> up
    [1] -> down
    [2] -> left
    [3] -> right
     */
    boolean[] cameraMovement = {false,false,false,false};
    /*
    parts chosen order for checking:
    [0] -> base part
    [1] -> obstacle
    [2] -> checkpoint
    [3] -> finish line
    [4] -> player spawn
     */
    boolean[] partChosen = {false,false,false,false,false};
    public editView(Context context) {
        super(context);
        this.context = context;
        levelEditing = new level(new layer(new ArrayList<>()),new layer(new ArrayList<>()),new layer(new ArrayList<>()),context,false);
    }
    public editView(Context context, level level) {
        super(context);
        levelEditing = level;
        this.context = context;
    }
    public void moveCamera() {
        if(cameraMovement[0]) {
            cameraY += 10;
        }
        if(cameraMovement[1]) {
            cameraY -= 10;
        }
        if(cameraMovement[2]) {
            cameraX += 10;
        }
        if(cameraMovement[3]) {
            cameraX -= 10;
        }
    }
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        canvas.translate(cameraX,cameraY);
        if(levelEditing != null) {
            levelEditing.drawLevel(canvas);
        }
        moveCamera();
        invalidate();
    }
    int taps = 0;
    float x1 = 0,y1 = 0;
    public void switchReset() {
        taps = 0;
        x1 = 0;
        y1 = 0;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // base part
        if(partChosen[0])
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                float x2,y2;
                if(taps == 0) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if(taps == 1) {
                    x2 = event.getX();
                    y2 = event.getY();
                    float width = Math.abs(x2 - x1);
                    float height = Math.abs(y2 - y1);
                    float X = Math.min(x1,x2);
                    float Y = Math.min(y1,y2);
                    levelEditing.base.getLayer().add(new shape((int) ((int) X - cameraX), (int) ((int) Y - cameraY), (int) width, (int) height, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
                }
                taps = (taps+1)%2;
            }

        }
        //obstacle
        else if(partChosen[1]) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                float x2,y2;
                if(taps == 0) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if(taps == 1) {
                    x2 = event.getX();
                    y2 = event.getY();
                    float width = Math.abs(x2 - x1);
                    float height = Math.abs(y2 - y1);
                    float X = Math.min(x1,x2);
                    float Y = Math.min(y1,y2);
                    levelEditing.obst.getLayer().add(new obstacle((int) ((int)  X - cameraX), (int) ((int) Y - cameraY), (int) width, (int) height, BitmapFactory.decodeResource(getResources(),R.drawable.spike),50));
                }
                taps = (taps+1)%2;
            }
        }
        //checkpoint
        else if(partChosen[2]) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                    float width = 100;
                    float height = 200;
                    float X = x1 - 50;
                    float Y = y1 - 100;
                    levelEditing.checkpoints.getLayer().add(new checkpoint((int) (X - cameraX), (int) (Y-cameraY), (int) width, (int) height,BitmapFactory.decodeResource(getResources(),R.drawable.checkpoint),false));
                }
        //finish line
        } else if(partChosen[3]) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                x1 = event.getX();
                y1 = event.getY();
                float width = 100;
                float height = 200;
                float X = x1 - 50;
                float Y = y1 - 100;
                levelEditing.checkpoints.getLayer().add(new checkpoint((int) (X - cameraX), (int) (Y-cameraY), (int) width, (int) height,BitmapFactory.decodeResource(getResources(),R.drawable.checkpoint),true));
            }
        //spawn point
        } else if(partChosen[4]) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                x1 = event.getX();
                y1 = event.getY();
                levelEditing.player.startX =(int) (x1 - cameraX);
                levelEditing.player.startY = (int) (y1- cameraY);
            }
        }
    return super.onTouchEvent(event);
    }
}
