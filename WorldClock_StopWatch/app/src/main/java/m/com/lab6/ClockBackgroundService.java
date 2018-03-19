package m.com.lab6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockBackgroundService extends Service
{
    private boolean isRunning = false;
    private Handler threadHandler = new Handler();
    private Runnable runnable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        isRunning = true;
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(new Runnable()
        {
            int count = 0;
            @Override
            public void run()
            {
                 sendMessageToActivity(""+count);

                 runnable = this;

                 if(isRunning)
                 {
                     //threadHandler.postDelayed(this, 1000);
                     threadHandler.post(this);
                 }
            }
        }).start();

        return START_STICKY;
    }

    private String[] calculateTime()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("hh:mm:ss a");

        df.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        String date1 = df.format(date);

        df.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String date2 = df.format(date);

        df.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String date3 = df.format(date);

        df.setTimeZone(TimeZone.getTimeZone("Japan"));
        String date4 = df.format(date);

        String[] timeArr = new String[]{date1, date2, date3, date4 };
        return timeArr;
    }

    private void sendMessageToActivity(String newData)
    {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("ClockServiceReceiver");
        broadcastIntent.putExtra("TimeList", calculateTime());
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onDestroy()
    {
        isRunning = false;
        threadHandler.removeCallbacks(runnable);
        stopSelf();
    }

}
