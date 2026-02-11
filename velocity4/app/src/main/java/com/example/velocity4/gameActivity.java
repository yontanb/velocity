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

        base.getLayer().add(new shape(0,700,1500,500, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        base.getLayer().add(new shape(200,300,100,500, BitmapFactory.decodeResource(getResources(),R.drawable.base)));
        lvl = new level(base,null,this);
        setContentView(new gameView(this,lvl));
    }
}