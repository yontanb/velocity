package com.example.velocity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class level {
    layer base;
    layer obst;
    layer playr = new layer(new ArrayList<>());
    Context context;

    public level(layer base, layer obst, Context context) {
        this.context = context;
        this.base = base;
        this.obst = obst;
        setPlayrLayer();
    }

    public void setPlayrLayer() {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ph2);
        playr.getLayer().add(new player(300, 400, 100, 200, bitmap));
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.button);
        playr.getLayer().add(new shape(200, 900, 150, 150, bitmap1));
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.button2);
        playr.getLayer().add(new shape(500, 900, 150, 150, bitmap1));
        playr.getLayer().add(new shape(1500, 900, 150, 150, bitmap1));
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

    public layer getPlayer() {
        return playr;
    }

    public void setPlayer(layer player) {
        this.playr = player;
    }

    public void drawLevel(Canvas canvas) {
        if (base != null)
            base.drawLayer(canvas);
        if (obst != null)
            obst.drawLayer(canvas);
        if (playr != null)
            playr.drawLayer(canvas);
    }
    public void  playerdeathcheck() {
        player player = (player) playr.getLayer().get(0);
        for( shape s : obst.getLayer()) {
            if(player.collision(s)) {
                player.death();
            }
        }
    }
    public void playerCollide() {
        player player = (player) playr.getLayer().get(0);
        player.canLeft = player.canUp = player.canRight = player.canDown = true;
        for (shape s : base.getLayer()) {
            if (player.collision(s)) {
                direction dir = player.dirToOtherShape(s);
                Log.d("testing collider", dir.toString());
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
