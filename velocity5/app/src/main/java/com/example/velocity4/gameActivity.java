package com.example.velocity4;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class gameActivity extends levelholder implements View.OnTouchListener, OnWinListener {
    level lvl;
    Button goLeft, goRight, jump;
    TextView timer;
    private gameView gameView;
    clockThread clockThread;
    Handler handler;
    String id;
    int spawnX, spawnY;
    String timeFormat;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        if (intent.hasExtra("level_id")) {
            id = intent.getStringExtra("level_id");
            lvl = levelMap.get(id);
            lvl.playerNeeded = true;
            lvl.context = this;
            spawnX = lvl.player.startX;
            spawnY = lvl.player.startY;
        }
        assert lvl != null;
        gameView = new gameView(this, lvl);
        RelativeLayout relativeLayout = findViewById(R.id.gamelayout);
        relativeLayout.addView(gameView, 0);
        goLeft = findViewById(R.id.goLeft);
        goRight = findViewById(R.id.goRight);
        jump = findViewById(R.id.jump);
        timer = findViewById(R.id.time);
        goRight.setOnTouchListener(this);
        jump.setOnTouchListener(this);
        goLeft.setOnTouchListener(this);
        gameView.setOnWinListener(this);
        //note to self, this is the death function but it just offsets to startX,startY, so don't worry
        lvl.player.death();
        handler = new Handler(msg -> {
            long time =(long) msg.obj;
            long timeMinutes = time/60000;
            long timeSecond = time/1000;
            long timemills = time % 1000;
            timeFormat =  String.format("%d:%02d:%03d",timeMinutes,timeSecond,timemills);
            timer.setText("time: " + timeFormat);
            return true;
        });
        clockThread = new clockThread(handler);
        clockThread.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == goLeft) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingleft = true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                gameView.ismovingleft = false;
            }
        }
        if (v == goRight) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                gameView.ismovingright = true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                gameView.ismovingright = false;
            }
        }
        if (v == jump) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                lvl.player.dy = -60;
            }
        }
        return true;
    }

    @Override
    public void OnWin() {
        clockThread.stoptimer();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("you won the level!");
        builder.setMessage("try again?");
        builder.setCancelable(false);
        builder.setPositiveButton("restart", (dialog, which) -> {
            gameView.notWon = true;
            lvl.player.setSpawn(spawnX,spawnY);
            lvl.player.death();
            clockThread = new clockThread(handler);
            clockThread.start();
        } );
        builder.setNegativeButton("go to levels screen", (dialog, which) -> {
            finish();
        });
        builder.show();
        String str = String.format("you beat this level in %s" , timeFormat);
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
}

