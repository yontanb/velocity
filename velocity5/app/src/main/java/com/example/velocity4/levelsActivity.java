package com.example.velocity4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class levelsActivity extends levelholder implements View.OnClickListener, OnDeleteListener {
    ListView leveldisplay;
    levelAdapter levelAdapter;
    FirebaseDatabase db;
    Button addlvl;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        addlvl = findViewById(R.id.addlvlbtn);
        addlvl.setOnClickListener(this);
        levelAdapter = new levelAdapter(this,0,0,levels);
        levelAdapter.setOnDeleteListener(this);
        leveldisplay = findViewById(R.id.listLvls);
        leveldisplay.setAdapter(levelAdapter);
        db = FirebaseDatabase.getInstance();
        context = this;
        loadingLvls();
    }
    public void loadingLvls() {
        DatabaseReference ref = db.getReference("levels");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                levels.clear();
                levelMap.clear();
                for(DataSnapshot child : snapshot.getChildren()) {
                    saveData data = child.getValue(saveData.class);
                    if(data != null) {
                        level level = data.levelSaved.dataToLevel(context);
                        levels.add(level);
                        levelMap.put(child.getKey(),level);
                        level.id = child.getKey();
                    }
                }
                levelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("database error", error.getMessage(),error.toException());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        leveldisplay = findViewById(R.id.listLvls);
        leveldisplay.setAdapter(levelAdapter);
    }


    @Override
    public void onClick(View v)
    {
        if(v == addlvl) {
            layer base = new layer(new ArrayList<>());
            layer obst = new layer(new ArrayList<>());
            layer chp = new layer(new ArrayList<>());
            level newLvl = new level(base,obst,chp,this,false);
            DatabaseReference ref = db.getReference("levels").push();
            newLvl.id = ref.getKey();
            levels.add(newLvl);
            levelMap.put(newLvl.id,newLvl);
            Intent intent = new Intent(this, editLevelsActivity.class);
            intent.putExtra("level_id",newLvl.id);
            startActivity(intent);
        }
    }

    @Override
    public void OnDel(level L, int position) {
        DatabaseReference ref = db.getReference("levels").child(L.id);
        ref.removeValue();
        levels.remove(position);
        levelMap.remove(L.id);
        levelAdapter.notifyDataSetChanged();
    }
}