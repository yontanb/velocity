package com.example.velocity4;

import android.content.Context;

import java.util.ArrayList;

public class levelData {
    ArrayList<shapeData> base;
    ArrayList<shapeData> obst;
    ArrayList<shapeData> checkpoints;
    int startX ,startY;
    String levelName;

    /**
     * creates a new levelData from a existing level
     * @param L the level you wanna turn into data
     */
    public levelData(level L) {
        base = new ArrayList<>();
        obst = new ArrayList<>();
        checkpoints = new ArrayList<>();
        for(shape s : L.getBase().layer) {
            base.add(new shapeData(s));
        }
        for(shape s : L.getObst().layer) {
            obst.add(new shapeData(s));
        }
        for(shape s : L.getCheckpoints().layer) {
            checkpoints.add(new shapeData(s));
        }
        this.startX = L.player.startX;
        this.startY = L.player.startY;
        this.levelName = L.levelName;
    }

    /**
     * a empty constructor for firebase
     */
    public levelData() {}

    public ArrayList<shapeData> getBase() {
        return base;
    }

    public ArrayList<shapeData> getObst() {
        return obst;
    }

    public ArrayList<shapeData> getCheckpoints() {
        return checkpoints;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public String getLevelName() {
        return levelName;
    }

    /**
     * turns the data into a usable level
     * @param context the level context
     * @return a ready to use level from the data
     */
    public level dataToLevel(Context context) {
        layer base1 = new layer(new ArrayList<>());
        layer obst1 = new layer(new ArrayList<>());
        layer checkpoints1 = new layer(new ArrayList<>());
        if(base != null) {
            for(shapeData D : base) {
                base1.layer.add(D.dataToShape(context));
            }
        }
        if(obst != null) {
            for(shapeData D : obst) {
                obst1.layer.add(D.dataToShape(context));
            }
        }
        if(checkpoints != null) {
            for(shapeData D : checkpoints) {
                checkpoints1.layer.add(D.dataToShape(context));
            }
        }
        level level = new level(base1,obst1,checkpoints1,context,true);
        level.player.startX = this.startX;
        level.player.startY = this.startY;
        level.levelName = this.levelName;
        return level;
    }
}
