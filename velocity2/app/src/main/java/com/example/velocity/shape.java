package com.example.velocity;

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
        return this.rect.intersect(other.rect);
    }

}
