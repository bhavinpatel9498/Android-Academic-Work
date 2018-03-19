//Simple Android TabHost and TabWidget Example
package m.com.lab6;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    private ServiceToActivity serviceReceiver;
    private  Intent intent;
    ClockListValues objClockList = new ClockListValues();

    private ClockService clockService;
    private boolean serviceBound = false;

    private Handler threadHandler = new Handler();
    private Runnable runnable;

    private Button startService;
    private Button stopService;
    private Button startTimer;
    private Button stopTimer;
    private Button resetTimer;
    private String time;
    private TextView timer;

    private Intent intentClock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.clocklist);
        startService = (Button)findViewById(R.id.button);
        stopService = (Button)findViewById(R.id.button2);
        startTimer = (Button)findViewById(R.id.button4);
        stopTimer = (Button)findViewById(R.id.button3);
        resetTimer = (Button)findViewById(R.id.button5);

        timer = (TextView)findViewById(R.id.textView3);

        setTabs();
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            ClockService.ClockBinder binder = (ClockService.ClockBinder) service;
            clockService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            serviceBound = false;
        }
    };

    private void buttonListeners()
    {
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                intentClock = new Intent(MainActivity.this, ClockService.class);
                bindService(intentClock, mConnection, Context.BIND_AUTO_CREATE);
                serviceBound = true;

                String strVal = String.format("%02d : %02d : %02d . %03d", 0, 0, 0, 0);
                timer.setText(strVal);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(serviceBound)
                {
                   unbindService(mConnection);
                   serviceBound = false;

                   if(runnable != null && threadHandler != null)
                   {
                       threadHandler.removeCallbacks(runnable);
                   }
                }

                timer.setText("");
            }
        });

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(serviceBound)
                {
                    Toast.makeText(MainActivity.this, "Start Timer ", Toast.LENGTH_SHORT).show();

                    clockService.setBeginTime();
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run() {
                            timer.setText(clockService.timerValue());
                            runnable = this;
                            threadHandler.postDelayed(this, 1);
                            //threadHandler.post(this);
                            //threadHandler.postAtFrontOfQueue(this);
                        }
                    });
                }
            }
        });

        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(serviceBound)
                {
                    //Toast.makeText(MainActivity.this, "Stop Timer ", Toast.LENGTH_SHORT).show();
                    clockService.timerStop();
                    threadHandler.removeCallbacks(runnable);
                }
            }
        });

        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(serviceBound)
                {
                    //Toast.makeText(MainActivity.this, "Reset Timer ", Toast.LENGTH_SHORT).show();
                    threadHandler.removeCallbacks(runnable);
                    String strVal = String.format("%02d : %02d : %02d . %03d", 0, 0, 0, 0);
                    timer.setText(strVal);
                }
            }
        });

    }

    @Override
    protected void onStart()
    {
        buttonListeners();

        super.onStart();
        intent = new Intent(getBaseContext(),ClockBackgroundService.class);
        startService(intent);

        serviceReceiver = new ServiceToActivity();
        IntentFilter intentSFilter = new IntentFilter("ClockServiceReceiver");
        registerReceiver(serviceReceiver, intentSFilter);

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        stopService(intent);
        unregisterReceiver(serviceReceiver);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        stopService(intent);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    private void setTabs()
    {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("TabOne");
        spec.setContent(R.id.tab1);
        spec.setIndicator("World Clock");
        host.addTab(spec);

        TabHost.TabSpec spec1 = host.newTabSpec("TabTwo");
        spec1.setContent(R.id.tab2);
        spec1.setIndicator("Stop Watch");
        host.addTab(spec1);
    }

    public class ServiceToActivity extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context ctx, Intent intent)
        {
            Bundle bundle = intent.getExtras();
            objClockList.setTimeList((String[])bundle.get("TimeList"));

            listAdapter = new ClockAdapterList(MainActivity.this, objClockList.getCountryList(), objClockList.getTimeList());
            listView.setAdapter(listAdapter);
       }
    }


}