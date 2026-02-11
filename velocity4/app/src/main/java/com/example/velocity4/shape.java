package com.example.velocity4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public  class shape {
    Rect rect;
    Bitmap bitmap;

    public shape(int x, int y, int width, int height, Bitmap bitmap) {
        this.rect = new Rect(x,y,x+width,y+height);
        this.bitmap = bitmap;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void drawShape(Canvas canvas) {
        canvas.drawBitmap(bitmap,null,rect,null);
    }
    public boolean isInside(int x,int y)
    {
       return rect.contains(x,y);
    }
    public boolean collision(shape other) {
        return this.rect.intersects(other.rect.left,other.rect.top,other.rect.right,other.rect.bottom);
    }
    public direction dirToOtherShape( shape other ) {
        if(other == null)
            return  direction.none;
        if(this.rect.top >= other.rect.bottom) {
            return direction.up;
        } else
        if(this.rect.bottom >= other.rect.top) {
            return direction.down;
        } else
        if( this.rect.left >= other.rect.right) {
            return  direction.left;
        } else
        if( this.rect.right <= other.rect.left) {
            return  direction.right;
        }
        return  direction.none;
    }

}
