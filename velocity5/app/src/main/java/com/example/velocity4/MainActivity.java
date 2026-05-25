package com.example.velocity4;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;


public class MainActivity extends levelholder implements View.OnClickListener {
    Button btn,credits,quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.startbtn);
        credits = findViewById(R.id.credits);
        quit = findViewById(R.id.quit);
        btn.setOnClickListener(this);
        credits.setOnClickListener(this);
        quit.setOnClickListener(this);

        AlarmManager manager = (AlarmManager) getSystemService(this.ALARM_SERVICE);
        Intent intent = new Intent(this, broadcaster.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        manager.cancel(pendingIntent);
        pendingIntent.cancel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
    }
    @Override
    public void onClick(View v) {
        if(v == btn) {
            Intent intent = new Intent(this, levelsActivity.class);
            startActivity(intent);
        }
        if(v == credits) {
            Intent intent = new Intent(this, creditsActivity.class);
            startActivity(intent);
        }
        if(v == quit) {
            setAlarm();
            finishAffinity();
        }
    }
    public void setAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(this.ALARM_SERVICE);
        Intent intent = new Intent(this, broadcaster.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.HOUR,24);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if(manager != null && manager.canScheduleExactAlarms()) {
                manager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        }
    }

}