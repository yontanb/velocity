package com.example.velocity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

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
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ph2);
        playr.getLayer().add(new player(500,400,200,300,bitmap));
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.button);
        playr.getLayer().add(new shape(200,800,200,200,bitmap1));
        bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.button2);
        playr.getLayer().add(new shape(500,800,200,200,bitmap1));
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
        if(base != null)
            base.drawLayer(canvas);
        if(obst != null)
            obst.drawLayer(canvas);
        if(playr != null)
            playr.drawLayer(canvas);
    }
    


}
