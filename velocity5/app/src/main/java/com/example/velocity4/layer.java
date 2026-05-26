package com.example.velocity4;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

public class layer {
    ArrayList<shape> layer;

    /**
     * creates a new layer
     * @param layer the arrayList of the shapes
     */
    public layer(ArrayList<shape> layer) {
        this.layer = layer;

    }
    /**
     * empty constructor for firebase
     */
    public layer() {}

    public ArrayList<shape> getLayer() {
        return layer;
    }

    public void setLayer(ArrayList<shape> layer) {
        this.layer = layer;
    }
    /**
     * draws the layer of the shapes
     */
    public void drawLayer(Canvas canvas) {
        for (int i = 0; i<layer.toArray().length;i++) {
            layer.get(i).drawShape(canvas);
        }
    }
}
