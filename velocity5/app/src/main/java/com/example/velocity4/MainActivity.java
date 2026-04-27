package com.example.velocity4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends levelholder implements View.OnClickListener {
    Button btn;
    TextView credits;
    FirebaseDatabase test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.startbtn);
        credits = findViewById(R.id.credits);
        btn.setOnClickListener(this);
        credits.setOnClickListener(this);
        test = FirebaseDatabase.getInstance();
    }
    @Override
    public void onClick(View v) {
        if(v == btn) {
            DatabaseReference ref = test.getReference("messege");
            ref.setValue("helo:)");
            Intent intent = new Intent(this, levelsActivity.class);
            startActivity(intent);
        }
        if(v == credits) {
            DatabaseReference ref = test.getReference("messege" + test);
            ref.setValue("yessir");
            Intent intent = new Intent(this, creditsActivity.class);
            startActivity(intent);
        }
    }
}