package com.example.velocity4;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class timehandler  extends Handler {
    WeakReference<gameActivity> gameRef;
    public timehandler(gameActivity gameActivity) {
        super(Looper.getMainLooper());
        this.gameRef = new WeakReference<>(gameActivity);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        gameActivity gameActivity = gameRef.get();
        if(gameActivity != null) {
            long time = (long) msg.obj;
            gameActivity.updateTime(time);
        }
    }
}
