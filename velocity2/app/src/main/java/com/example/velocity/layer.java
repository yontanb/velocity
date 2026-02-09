package com.example.velocity;

import android.graphics.Canvas;

import java.util.ArrayList;

public class layer {
    ArrayList<shape> layer;


    public layer(ArrayList<shape> layer) {
        this.layer = layer;

    }

    public ArrayList<shape> getLayer() {
        return layer;
    }

    public void setLayer(ArrayList<shape> layer) {
        this.layer = layer;
    }

    public void drawLayer(Canvas canvas) {
        for (int i = 0; i<layer.toArray().length;i++) {
            layer.get(i).drawShape(canvas);
        }
    }
}
