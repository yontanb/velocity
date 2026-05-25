package com.example.velocity4;

import android.os.Handler;
import android.os.Message;

public class clockThread extends Thread {
    long timepassed;
    long timepaused;
    long whenPaused;
    Handler handler;
    private boolean pause;
     volatile boolean running;
    public clockThread(Handler handler) {
        running = true;
        this.handler=handler;
        this.timepassed = 0;
        this.timepaused = 0;
        this.whenPaused = 0;
        pause = false;
    }
    @Override
    public void run() {
        long timeStart = System.nanoTime();
        while(running) {
            if(pause) {
                if(whenPaused == 0) {
                    whenPaused = System.nanoTime();
                }
            } else {
                if(whenPaused != 0) {
                    timepaused += System.nanoTime() - whenPaused;
                    whenPaused = 0;
                }
                timepassed = (System.nanoTime() - timeStart - timepaused) /1000000;
                Message msg = handler.obtainMessage();
                msg.obj = timepassed;
                handler.sendMessage(msg);
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    public void togglePause() {
        pause = !pause;
    }
    public void stopTimer() {
        running = false;
        interrupt();
    }
}
