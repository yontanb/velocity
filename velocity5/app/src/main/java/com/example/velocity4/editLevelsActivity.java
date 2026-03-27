package com.example.velocity4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;



public class editLevelsActivity extends levelholder implements View.OnTouchListener, PopupMenu.OnMenuItemClickListener {
    editView editView;
    Button left,right,up,down,save;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra("level")) {
            level level = levels.get((Integer) intent.getExtras().get("level"));
            editView = new editView(this, level);
        }
        setContentView(R.layout.activity_edit_levels);
        RelativeLayout ui = findViewById(R.id.editlayout);
        ui.addView(editView, 0);
        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        up = findViewById(R.id.btnUp);
        down = findViewById(R.id.btnDown);
        save = findViewById(R.id.saveBtn);
        save.setOnTouchListener(this);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_level_menu, menu);
        return true;
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
        if(v == save) {
            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenuInflater().inflate(R.menu.edit_level_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            editView.cameraMovement[0] = false;
            editView.cameraMovement[1] = false;
            editView.cameraMovement[2] = false;
            editView.cameraMovement[3] = false;
        }
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        editView.switchReset();
        if(item.getItemId() == R.id.save_level) {
            finish();
        }
        if(item.getItemId() == R.id.add_base) {
            resetValueForPartChosen();
            editView.partChosen[0] = true;
            Log.d("Level Editor", "part Chosen: base");
        }
        if(item.getItemId() == R.id.add_obst) {
            resetValueForPartChosen();
            editView.partChosen[1] = true;
            Log.d("Level Editor", "part Chosen: obst");
        }
        if(item.getItemId() == R.id.add_checkpoint) {
            resetValueForPartChosen();
            editView.partChosen[2] = true;
            Log.d("Level Editor", "part Chosen: checkpoint");
        }
        if(item.getItemId() == R.id.add_finish) {
            resetValueForPartChosen();
            editView.partChosen[3] = true;
            Log.d("Level Editor", "part Chosen: finish line");
        }
        if(item.getItemId() == R.id.add_spawn) {
            resetValueForPartChosen();
            editView.partChosen[4] = true;
            Log.d("Level Editor", "part Chosen: spawn");
        }
        if(item.getItemId() == R.id.deleter) {
            resetValueForPartChosen();
            editView.partChosen[5] = true;
            Log.d("Level Editor", "delete mode chosen");
        }
        return true;
    }
    private void resetValueForPartChosen() {
        editView.partChosen[0] = false;
        editView.partChosen[1] = false;
        editView.partChosen[2] = false;
        editView.partChosen[3] = false;
        editView.partChosen[4] = false;
        editView.partChosen[5] = false;
    }
}