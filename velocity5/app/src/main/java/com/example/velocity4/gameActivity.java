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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class gameActivity extends levelholder implements View.OnTouchListener, OnWinListener {
    level lvl;
    gameView gameView;
    int spawnX, spawnY;

    Button goLeft, goRight, jump, pause;
    TextView timer;

    clockThread clockThread;
    timehandler timehandler;
    String timeFormat;
    long time;

    FirebaseDatabase db;
    String id;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    /**
     * the screen constructor it initializes variables, loads the level and starts the game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        db = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        if (intent.hasExtra("level_id")) {
            id = intent.getStringExtra("level_id");
            lvl = levelMap.get(id);
            if (lvl != null) {
                lvl.playerNeeded = true;
                lvl.context = this;
                spawnX = lvl.player.startX;
                spawnY = lvl.player.startY;
            }
        }
        assert lvl != null;
        gameView = new gameView(this, lvl);
        RelativeLayout relativeLayout = findViewById(R.id.gamelayout);
        relativeLayout.addView(gameView, 0);

        goLeft = findViewById(R.id.goLeft);
        goRight = findViewById(R.id.goRight);
        jump = findViewById(R.id.jump);
        pause = findViewById(R.id.pauseBtn);
        timer = findViewById(R.id.time);

        goLeft.setOnTouchListener(this);
        goRight.setOnTouchListener(this);
        jump.setOnTouchListener(this);
        pause.setOnTouchListener(this);
        gameView.setOnWinListener(this);
        //note to self: this just resets the player location for the level start
        lvl.player.death();
        timehandler = new timehandler(this);
        clockThread = new clockThread(timehandler);
        clockThread.start();
    }
    /**
     * handles the action button clicks
     */
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
                gameView.jumped = true;
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                gameView.jumped = false;
            }
        }
        if(v == pause) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                clockThread.togglePause();
                gameView.togglePause();
                pauseDialog();
            }
        }
        return false;
    }
    /**
     * creates a dialog and pauses the game
     */
    public void pauseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("level paused");
        builder.setMessage("return to main menu?");
        builder.setCancelable(false);

        builder.setPositiveButton("continue", (dialog, which) -> {
            clockThread.togglePause();
            gameView.togglePause();
        });
        builder.setNegativeButton("go to levels screen", (dialog, which) -> {
            clockThread.stopTimer();
            clockThread = null;
            finish();
        });
        builder.show();
    }
    /**
     * creates a dialog for the win of the game
     */
    @Override
    public void OnWin() {
        clockThread.stopTimer();
        clockThread = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("you won the level!");
        builder.setMessage(String.format("you beat this level in: %s", timeFormat));
        builder.setCancelable(false);

        builder.setPositiveButton("restart", (dialog, which) -> {
            gameView.notWon = true;
            lvl.player.setSpawn(spawnX,spawnY);
            lvl.player.death();
            clockThread = null;
            clockThread = new clockThread(timehandler);
            clockThread.start();
        });
        builder.setNegativeButton("go to levels screen", (dialog, which) -> {
            clockThread = null;
            finish();
        });
        builder.show();
        updateDataBaseTimes();
    }
    /**
     * updates the times in the database when you win
     */
    public void updateDataBaseTimes() {
        DatabaseReference updateTimes = db.getReference("levels").child(id);
        updateTimes.child("lastTime").setValue(time);

        updateTimes.child("bestTime").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long currBest = snapshot.getValue(Long.class);
                if(currBest == null) return;
                if (currBest == 0 || time < currBest) {
                    updateTimes.child("bestTime").setValue(time);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /**
     * updates the text timer on the screen
     */
    public void updateTime(long time) {
        if(gameView.notWon) {
            long minutes = time / 60000;
            long seconds = (time / 1000) % 60;
            long millis = time % 1000;
            this.time = time;
            timeFormat = String.format("%d:%02d:%03d", minutes, seconds, millis);
            timer.setText("time: " + timeFormat);
        }
    }
    /**
     * gets trigger when exiting the activity, destroys the thread and connections
     */
    public void onDestroy() {
        super.onDestroy();

        if (timehandler != null) {
            timehandler.removeCallbacksAndMessages(null);
        }

        if (clockThread != null) {
            clockThread.stopTimer();
            clockThread.interrupt();
            clockThread = null;
        }
        if (lvl != null) {
            lvl.context = null;
        }
    }
}

