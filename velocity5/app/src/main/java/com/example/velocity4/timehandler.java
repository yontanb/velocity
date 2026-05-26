package com.example.velocity4;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class timehandler  extends Handler {
    WeakReference<gameActivity> gameRef;
    /**
     * creates a new time handler
     * @param gameActivity the activity the handler sends to
     */
    public timehandler(gameActivity gameActivity) {
        super(Looper.getMainLooper());
        this.gameRef = new WeakReference<>(gameActivity);
    }
    /**
     * sends a message to the activity
     */
    @Override
    public void handleMessage(@NonNull Message msg) {
        gameActivity gameActivity = gameRef.get();
        if(gameActivity != null) {
            long time = (long) msg.obj;
            gameActivity.updateTime(time);
        }
    }
}
