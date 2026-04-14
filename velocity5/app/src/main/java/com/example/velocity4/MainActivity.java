package com.example.velocity4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


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
        if(v == credits) {
            Intent intent = new Intent(this, levelsActivity.class);
            startActivity(intent);

        }
        if(v == btn) {
            DatabaseReference myRef = test.getReference("message");
            myRef.setValue("hello");
            myRef.setValue("hello")
                    .addOnSuccessListener(aVoid -> {
                        // Write was successful!
                        Log.d("Firebase", "Successfully wrote 'hello' to database.");
                    })
                    .addOnFailureListener(e -> {
                        // Write failed!
                        Log.e("Firebase", "Error writing to database: " + e.getMessage(), e);
                    });
//            Intent intent = new Intent(this, creditsActivity.class);
//            startActivity(intent);
        }
    }
}