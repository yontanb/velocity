package com.example.velocity4;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class gameActivity extends levelholder {
    level lvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layer base = new layer(new ArrayList<>());
        layer obst = new layer(new ArrayList<>());
        base.getLayer().add(new shape(0,900,2000,500, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        base.getLayer().add(new shape(100,500,200,200, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        obst.getLayer().add(new shape(1000,800,200,100, BitmapFactory.decodeResource(getResources(),R.drawable.spike)));
        lvl = new level(base,obst,this);
        setContentView(new gameView(this,lvl));
    }
}