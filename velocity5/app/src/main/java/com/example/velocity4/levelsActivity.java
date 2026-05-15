package com.example.velocity4;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class levelsActivity extends levelholder implements AdapterView.OnItemClickListener, View.OnClickListener {
    ListView leveldisplay;
    levelAdapter levelAdapter;
    FirebaseDatabase loader = FirebaseDatabase.getInstance();
    Button addlvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        addlvl = findViewById(R.id.addlvlbtn);
        addlvl.setOnClickListener(this);
        levelAdapter = new levelAdapter(this,0,0,levels);
        leveldisplay = findViewById(R.id.listLvls);
        leveldisplay.setAdapter(levelAdapter);
        leveldisplay.setOnItemClickListener(this);
        loadingLvls();
    }
    public void loadingLvls() {
        DatabaseReference ref = loader.getReference("levelTest");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                levels.clear();
                for(DataSnapshot child : snapshot.getChildren()) {
                    level lvl = child.getValue(level.class);
                    if(lvl != null) {
                        levels.add(lvl);
                    }
                }
                levelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("database error", error.getMessage());
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("level",position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        if(v == addlvl) {
            layer base = new layer(new ArrayList<>());
            layer obst = new layer(new ArrayList<>());
            layer chp = new layer(new ArrayList<>());
            levels.add(new level(base, obst, chp, this, false));
            Intent intent = new Intent(this, editLevelsActivity.class);
            intent.putExtra("level",levels.size()-1);
            this.startActivity(intent);
        }
    }
}