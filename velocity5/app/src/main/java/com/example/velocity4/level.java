package com.example.velocity4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.TextView;

public class level {
    layer base;
    layer obst;
    layer checkpoints;
    player player;
    transient Context context;
    boolean playerNeeded;
    String id;
    String levelName;
    public level(layer base, layer obst,layer checkpoints, Context context, boolean playerNeeded) {
        this.context = context;
        this.base = base;
        this.obst = obst;
        this.checkpoints = checkpoints;
        this.playerNeeded = playerNeeded;
        player = new player(300, 300, 100, 150, "ph2", context);

    }
    public level() {}
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


    public layer getCheckpoints() {
        return checkpoints;
    }

    public player getPlayer() {
        return player;
    }
    Paint p = new Paint();
    Path path = new Path();
    public void drawLevel(Canvas canvas) {
        if (base != null)
            base.drawLayer(canvas);
        if (obst != null)
            obst.drawLayer(canvas);
        if (checkpoints != null) {
            checkpoints.drawLayer(canvas);
        }
        if (playerNeeded) {
            player.drawShape(canvas);
        } else {
            drawspawn(canvas);
        }
    }
    public void drawspawn(Canvas canvas) {
        int color = 0xff1392D3;
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        path.reset();
        path.moveTo(player.startX - 30, player.startY);
        path.lineTo(player.startX, player.startY - 30);
        path.lineTo(player.startX + 30, player.startY);
        path.lineTo(player.startX, player.startY + 30);
        path.close();
        canvas.drawPath(path, p);
    }

    public void healthDisplay(Context context) {
        TextView tv = ((Activity) context).findViewById(R.id.health);
        tv.setText("health: " + player.health);
    }
    public boolean didWin() {
        for(shape s : checkpoints.getLayer()) {
            if(s.collision(player) && ((checkpoint) s).winner) {
                return true;
            }
        }
        return false;
    }
     transient boolean damaged = false;
    public void playerdeathcheck() {
        for(shape s : obst.getLayer()) {
            if(s.collision(player)) {
                if(!damaged)
                    s.damagePlayer(player);

                damaged = true;
            } else {
                damaged = false;
            }
        }
    }

    public boolean playerCollide() {
        player.canLeft = player.canUp = player.canRight = player.canDown = true;
        for (shape s : base.layer) {
            if (player.collision(s)) {
                direction dir = player.dirToOtherShape(s);
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
        if(!player.canLeft || !player.canRight || !player.canDown || !player.canUp) {
            return  true;
        } else  {
            return false;
        }

    }


}
