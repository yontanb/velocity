package com.example.velocity4;

import android.content.Context;


public class shapeData {
int x,y,width,height;
String texture;
int damage;
boolean winner;
shapeType type;
    /**
     * creates a newshapeData with a normal shape
     * @param x the X coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param texture the texture name
     */
    public shapeData(int x, int y, int width, int height, String texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        type = shapeType.shape;
    }
    /**
     * creates a newshapeData with a obstacle
     * @param x the X coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param texture the texture name
     * @param damage the damage of the obstacle
     */
    public shapeData(int x, int y, int width, int height, String texture, int damage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.damage = damage;
        type = shapeType.obstacle;
    }
    /**
     * creates a newshapeData with checkpoint
     * @param x the X coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param texture the texture name
     * @param winner the winner status of the checkpoint
     */
    public shapeData(int x, int y, int width, int height, String texture, boolean winner) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.winner = winner;
        type = shapeType.checkpoint;
    }
    /**
     * creates a newshapeData with a given shape
     * @param s the shape the data comes from
     */
    public shapeData(shape s) {
        this.x = s.x;
        this.y = s.y;
        this.width = s.width;
        this.height = s.height;
        this.texture = s.texture;
        type = shapeType.shape;
        if(s instanceof obstacle) {
            type = shapeType.obstacle;
            this.damage = ((obstacle) s).damage;
        }
        if(s instanceof checkpoint) {
            type = shapeType.checkpoint;
            this.winner = ((checkpoint) s).winner;
        }
    }
    /**
     * an empty constructor for Firebase
     */
    public shapeData() {}

    public shapeType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTexture() {
        return texture;
    }

    public int getDamage() {
        return damage;
    }


    public boolean isWinner() {
        return winner;
    }
    /**
     * turns the current data into a usable shape
     * @param context the context for the shape
     * @return the constructed shape from the data
     */
    public shape dataToShape(Context context) {
        if (type == shapeType.shape) {
            return  new shape(x,y,width,height,texture,context);
        }
        if(type == shapeType.obstacle) {
            return new obstacle(x,y,width,height,texture,context,damage);
        }
        if(type == shapeType.checkpoint) {
            return new checkpoint(x,y,width,height,texture,context,winner);
        }
        return  null;
    }
}
