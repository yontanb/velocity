package com.example.velocity4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.google.firebase.database.Exclude;
public class shape {

    public transient Rect rect;

    public transient Bitmap bitmap;

    public transient Context context;
    public String texture;
    public int x, y, width, height;
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

    public shape() {}
    public void setRect(Rect rect) {
        this.rect = rect;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void drawShape(Canvas canvas) {
        if(bitmap == null) return;
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
        float overlapWidth = Math.min(this.rect.right, other.rect.right)-Math.max(this.rect.left, other.rect.left);
        float overlapHeight = Math.min(this.rect.bottom, other.rect.bottom)-Math.max(this.rect.top, other.rect.top);
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
    public boolean isWinner() {
        return false;
    }
    public void gotCheckpoint(player player) {}
    public void damagePlayer(player player) {}
    @SuppressLint("DiscouragedApi")
    public Bitmap nameToBitmap(Context context, String name) {
         int id = context.getResources().getIdentifier(name,"drawable",context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(),id);
    }
}
