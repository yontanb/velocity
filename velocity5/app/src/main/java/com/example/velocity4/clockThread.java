package com.example.velocity4;

import android.os.Handler;
import android.os.Message;

public class clockThread extends Thread {
    long timepassed;
    long timepaused;
    long whenPaused;
    Handler handler;
    private boolean pause;
    boolean running;
    public clockThread(Handler handler)
    {
        running = true;
        this.handler=handler;
        this.timepassed = 0;
        this.timepaused = 0;
    }
    @Override
    public void run()
    {
        long timeStart = System.currentTimeMillis();
        super.run();

        while(running)
        {
            if(!pause)
            {
                if(whenPaused != 0 )
                {
                    timepaused += System.currentTimeMillis() - whenPaused;
                    whenPaused = 0;
                }

                timepassed = System.currentTimeMillis() - timeStart - timepaused;

            } else
            {
                whenPaused = System.currentTimeMillis();
            }
            Message msg = handler.obtainMessage();
            msg.obj = timepassed;
            handler.sendMessage(msg);
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    public void setPause(boolean pause)
    {
        this.pause=pause;
    }
    public boolean getPause()
    {
        return pause;
    }
    public long getTimepassed() {
        return timepassed;
    }
    public void stoptimer() {
        running = false;
        interrupt();
    }
}
