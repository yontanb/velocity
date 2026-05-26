package com.example.velocity4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class levelholder extends AppCompatActivity {
    /**
     * a arrayList of the current levels for display
     */
    static ArrayList<level> levels = new ArrayList<>();
    /**
     * a map of id -> level to look them up for use in the engines
     */
    static Map<String,level> levelMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelholder);

    }
}