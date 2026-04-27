package com.example.velocity4;

import android.os.Handler;
import android.os.Message;

public class clockThread extends Thread {
    int timer;

    Handler handler;
    private boolean pause;
    public clockThread(Handler handler)
    {
        this.handler=handler;
        this.timer = 0;
    }
    @Override
    public void run()
    {
        super.run();

        while(true)
        {
            if(!pause)
            {   Message msg = new Message();
                msg.arg1 = timer;
                timer++;
                handler.sendMessage(msg);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

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
}
