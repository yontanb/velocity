package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

public class level {
    layer base;
    layer obst;
    layer checkpoints;
    player player;
    Context context;
    private ArrayList<shape> parts;
    public level(layer base, layer obst,layer checkpoints, Context context) {
        this.context = context;
        this.base = base;
        this.obst = obst;
        this.checkpoints = checkpoints;
        player = new player(300, 300, 100, 150, BitmapFactory.decodeResource(context.getResources(), R.drawable.ph2));
        parts = new ArrayList<>(base.getLayer());
        parts.addAll(obst.getLayer());

    }

    public layer getBase() {
        return base;
    }

    public void setBase(layer base) {
        this.base = base;
    }

    public layer getObst() {
        return obst;
    }

    public void setObst(layer obst) {
        this.obst = obst;
    }


    public player getgameplayer() {
        return player;
    }


    public void drawLevel(Canvas canvas) {
        if (base != null)
            base.drawLayer(canvas);
        if (obst != null)
            obst.drawLayer(canvas);
        if(checkpoints != null) {
            checkpoints.drawLayer(canvas);
        }
        if (player != null) {
            player.drawShape(canvas);
        }


    }
    public player getGamePlayer() {
        return player;
    }
    public boolean didWin() {
        for(shape s : checkpoints.getLayer()) {
            if(s.collision(player) && ((checkpoint) s).winner) {
                return true;
            }
        }
        return false;
    }
    boolean damaged = false;
    public void playerdeathcheck() {
        for(shape s : obst.getLayer()) {
            if(s.collision(player)) {
                if(!damaged)
                    s.damagePlayer(player);

                damaged = true;
            } else {
                damaged = false;
            }
        }
    }
    public void playerCollide() {
        player.canLeft = player.canUp = player.canRight = player.canDown = true;

        for (shape s : parts) {
            if (player.collision(s)) {
                direction dir = player.dirToOtherShape(s);
                if (dir == direction.down) {
                    player.canDown = false;
                }
                if (dir == direction.up) {
                    player.canUp = false;
                }
                if (dir == direction.left) {
                    player.canLeft = false;
                }
                if (dir == direction.right) {
                    player.canRight = false;
                }
            }
        }
    }


}
