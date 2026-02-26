package com.example.velocity4;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class levelsActivity extends levelholder implements AdapterView.OnItemClickListener, View.OnClickListener {
    ListView leveldisplay;
    levelAdapter levelAdapter;
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        level levelplayed = (level) leveldisplay.getAdapter().getItem(position);
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("level", (Parcelable) levelplayed);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        layer base = new layer(new ArrayList<>());
        layer obst = new layer(new ArrayList<>());
        layer chp = new layer(new ArrayList<>());
        base.getLayer().add(new shape(-100,900,5000,500, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        base.getLayer().add(new shape(100,500,200,200, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        base.getLayer().add(new shape(1000,300,500,200, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        obst.getLayer().add(new obstacle(10,800,100,100, BitmapFactory.decodeResource(getResources(),R.drawable.spike),50));
        chp.getLayer().add(new checkpoint(2000,700,100,200, BitmapFactory.decodeResource(getResources(),R.drawable.checkpoint),true));
        levels.add(new level(base,obst,chp,this,true));
    }
}