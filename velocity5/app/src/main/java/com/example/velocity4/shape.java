package com.example.velocity4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.google.firebase.database.Exclude;
public class shape {

    public  Rect rect;

    public Bitmap bitmap;

    public  Context context;
    public String texture;
    public int x, y, width, height;

    /**
     * creates a new shape
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width of the shape
     * @param height the height of the shape
     * @param texture the texture name
     * @param context the context of the shape
     */
    public shape(int x, int y, int width, int height, String texture, Context context) {
        this.rect = new Rect(x,y,x+width,y+height);
        this.bitmap = nameToBitmap(context, texture);
        this.texture = texture;
        this.context = context;
        this.x = x;
        this.y = y;
        this.width =width;
        this.height = height;
    }

    public String getTexture() {
        return texture;
    }

    /**
     * empty constructor for firebase
     */
    public shape() {}
    public void setRect(Rect rect) {
        this.rect = rect;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * draws the shape on a canvas
     * @param canvas the canvas to draw on
     */
    public void drawShape(Canvas canvas) {
        if(bitmap == null) return;
        canvas.drawBitmap(bitmap,null,rect,null);
    }

    /**
     *  checks if the point is inside the shape
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return true if point inside shape false if else
     */
    public boolean isInside(int x,int y)
    {
       return rect.contains(x,y);
    }

    /**
     * checks if another shape overlaps with the current shape
     * @param other the other shape
     * @return true if there is overlap, false if else
     */
    public boolean collision(shape other) {
        return this.rect.intersects(other.rect.left,other.rect.top,other.rect.right,other.rect.bottom);
    }

    /**
     * checks the direction of another shape relative to the current shape
     * @param other the other shape
     * @return the direction to the other shape
     */
    public direction dirToOtherShape( shape other ) {
        if(other == null)
            return  direction.none;
        //האורך של ה
        float overlapWidth = Math.min(this.rect.right, other.rect.right) - Math.max(this.rect.left, other.rect.left);
        float overlapHeight = Math.min(this.rect.bottom, other.rect.bottom) - Math.max(this.rect.top, other.rect.top);
        if (overlapWidth < overlapHeight) {
            if (this.rect.centerX() > other.rect.centerX())
                return direction.left;
            else
                return direction.right;
        } else {
            if (this.rect.centerY() > other.rect.centerY())

                return direction.up;
            else {
                rect.offsetTo(this.rect.left, other.rect.top - this.rect.height());
                return direction.down;
            }
        }
    }

    /**
     * for override
     * @return just for override
     */
    @Exclude
    public boolean isWinner() {
        return false;
    }

    /**
     * for override
     * @param player for override
     */
    public void gotCheckpoint(player player) {}

    /**
     * for override
     * @param player for override
     */
    public void damagePlayer(player player) {}

    /**
     * takes the texure name and makes a bitmap from the drawable
     * @param context the context to use the drawable
     * @param name the texture name
     * @return a bitmap with the texture
     */
    @SuppressLint("DiscouragedApi")
    public Bitmap nameToBitmap(Context context, String name) {
         int id = context.getResources().getIdentifier(name,"drawable",context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(),id);
    }
}
