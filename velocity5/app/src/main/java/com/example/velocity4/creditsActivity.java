package com.example.velocity4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class creditsActivity extends AppCompatActivity implements View.OnClickListener {
    Button back;
    /**
     * initializes the variables and sets the screen layout
     * gets called on screen creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }
    /**
     * detects click to exit activity
     */
    @Override
    public void onClick(View v) {
        finish();
    }
}