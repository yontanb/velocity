package com.example.velocity4;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class gameActivity extends levelholder implements View.OnTouchListener {
    level lvl;
    Button goLeft, goRight,jump;
    private gameView gameView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        layer base = new layer(new ArrayList<>());
        layer obst = new layer(new ArrayList<>());
        base.getLayer().add(new shape(0,900,2000,500, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        base.getLayer().add(new shape(100,500,200,200, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        obst.getLayer().add(new shape(1000,800,200,100, BitmapFactory.decodeResource(getResources(),R.drawable.spike)));
        lvl = new level(base,obst,this);
        gameView = new gameView(this, lvl);
        RelativeLayout relativeLayout = findViewById(R.id.gamelayout);
        relativeLayout.addView(gameView,0);
        goLeft = findViewById(R.id.goLeft);
        goRight = findViewById(R.id.goRight);
        jump = findViewById(R.id.jump);
        goRight.setOnTouchListener(this);
        jump.setOnTouchListener(this);
        goLeft.setOnTouchListener(this);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == goLeft) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingleft = true;
            }
        }
        if(v == goRight) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingright = true;
            }
        }
        if(v == jump) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                lvl.getGamePlayer().dy = -60;
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            gameView.ismovingright = false;
            gameView.ismovingleft = false;
        }
        return true;
    }
}