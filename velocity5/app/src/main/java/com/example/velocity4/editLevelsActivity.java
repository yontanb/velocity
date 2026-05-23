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
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class editLevelsActivity extends levelholder implements View.OnTouchListener, PopupMenu.OnMenuItemClickListener {
    editView editView;
    Button left,right,up,down, menu,quit;
    FirebaseDatabase lvl_saver;
    level level;
    String id = "";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra("level_id")) {
            id = intent.getStringExtra("level_id");
            level = levelMap.get(id);
            level.playerNeeded = false;
            editView = new editView(this, level);
        }
        lvl_saver = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_edit_levels);
        RelativeLayout ui = findViewById(R.id.editlayout);
        ui.addView(editView, 0);
        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        up = findViewById(R.id.btnUp);
        down = findViewById(R.id.btnDown);
        menu = findViewById(R.id.menuBtn);
        quit = findViewById(R.id.btnquit);
        menu.setOnTouchListener(this);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
        quit.setOnTouchListener(this);
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
        if(v == menu) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                PopupMenu popup = new PopupMenu(this, v);
                popup.getMenuInflater().inflate(R.menu.edit_level_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(this);
                popup.show();
            }
        }
        if(v == quit) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                finish();
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
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        editView.switchReset();
        if(item.getItemId() == R.id.save_level) {
            finishlevel();
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
    public boolean levelReq() {
        for(shape c : level.checkpoints.layer) {
            if(c.isWinner())
                return true;
        }
        Toast.makeText(this,"not valid level, no end",Toast.LENGTH_LONG).show();
        return false;
    }
    public void finishlevel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("enter name");
        EditText et = new EditText(this);
        et.setHint("enter level name");
        builder.setView(et);
        builder.setPositiveButton("save level",(dialog, which) -> {
            if(levelReq()) {
                level.levelName = et.getText().toString();
                saveLvl();
                finish();
            }
        });
        builder.setNegativeButton("cancel",(dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }
    public void saveLvl() {
        DatabaseReference saver = lvl_saver.getReference("levels").child(id);
        saveData savedata = new saveData(new levelData(level),0,0);
        saver.setValue(savedata);
    }
}