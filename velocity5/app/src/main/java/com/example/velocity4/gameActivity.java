package com.example.velocity4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class gameActivity extends levelholder implements View.OnTouchListener {
    level lvl;
    Button goLeft, goRight,jump;
    TextView timer;
    private gameView gameView;
    clockThread clockThread;
    Handler handler;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        if(intent.hasExtra("level")) {
            lvl = levels.get((int)intent.getExtras().get("level"));
            lvl.playerNeeded = true;
        }
        assert lvl != null;
        gameView = new gameView(this, lvl);
        RelativeLayout relativeLayout = findViewById(R.id.gamelayout);
        relativeLayout.addView(gameView,0);
        goLeft = findViewById(R.id.goLeft);
        goRight = findViewById(R.id.goRight);
        jump = findViewById(R.id.jump);
        timer = findViewById(R.id.time);
        goRight.setOnTouchListener(this);
        jump.setOnTouchListener(this);
        goLeft.setOnTouchListener(this);
        //note to self, this is the death function but it just offsets to startX,startY, so don't worry
        lvl.player.death();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                timer.setText("time: " + msg.arg1);
                return true;
            }
        });
        clockThread = new clockThread(handler);
        clockThread.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == goLeft) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingleft = true;
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                gameView.ismovingleft = false;
            }
        }
        if(v == goRight) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingright = true;
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                gameView.ismovingright = false;
            }
        }
        if(v == jump) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                lvl.getGamePlayer().dy = -60;
            }
        }
        return true;
    }
}