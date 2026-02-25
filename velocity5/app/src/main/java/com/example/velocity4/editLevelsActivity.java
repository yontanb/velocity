package com.example.velocity4;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class editLevelsActivity extends AppCompatActivity implements View.OnTouchListener {
    editView editView;
    Button left,right,up,down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra("level")) {
            level level = (level) Objects.requireNonNull(intent.getExtras()).get("level");
            editView = new editView(this,level);
        } else {
            editView = new editView(this);
        }
        setContentView(R.layout.activity_edit_levels);
        RelativeLayout ui = findViewById(R.id.editlayout);
        ui.addView(editView, 0);
        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        up = findViewById(R.id.btnUp);
        down = findViewById(R.id.btnDown);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
    }
    public boolean onTouch(View v, MotionEvent event) {
        if(v == left) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                editView.cameraMovement[2] = true;
            }
        }
        if(v == right) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                editView.cameraMovement[3] = true;
            }
        }
        if(v == up) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                editView.cameraMovement[0] = true;
            }
        }
        if(v == down) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                editView.cameraMovement[1] = true;
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            editView.cameraMovement[0] = false;
            editView.cameraMovement[1] = false;
            editView.cameraMovement[2] = false;
            editView.cameraMovement[3] = false;
        }
        return true;
    }
}