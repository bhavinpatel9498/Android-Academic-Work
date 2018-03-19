package m.com.lab6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class ClockService extends Service
{
    private IBinder mBinder = new ClockBinder();
    private Handler threadHandler = new Handler();
    private Runnable runnable;
    private boolean isRunning = false;
    private String time="";

    private int miliSec = 00;
    private int seconds = 00;
    private int minutes = 00;
    private int hours = 00;
    private long startTime;

    public class ClockBinder extends Binder {
        ClockService getService() {
            return ClockService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void setBeginTime()
    {
        startTime= System.nanoTime();
        miliSec = 0;
        seconds=0;
        minutes=0;
        hours=0;
    }

    public String timerValue()
    {
        miliSec = 0;
        seconds=0;
        minutes=0;
        hours=0;

        long endTime = System.nanoTime();

        long timediff = ((endTime -startTime)/1000000);

        while(true)
        {
            if(timediff >= (60*60*1000))
            {
                hours = hours+1;
                timediff = timediff - (60*60*1000);
            }
            else
            {
                break;
            }
        }

        while(true)
        {
            if(timediff >= (60*1000) && timediff < (60*60*1000))
            {
                minutes = minutes+1;
                timediff = timediff - (60*1000);
            }
            else
            {
                break;
            }
        }

        while(true)
        {
            if(timediff >= 1000 && timediff < (60*1000))
            {
                seconds = seconds+1;
                timediff = timediff - 1000;
            }
            else
            {
                break;
            }
        }

        miliSec = (int)timediff;



        time =  String.format("%02d : %02d : %02d . %03d", hours, minutes, seconds, miliSec);

        return time;
    }

    public void timerStop()
    {
        isRunning = false;
        threadHandler.removeCallbacks(runnable);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
