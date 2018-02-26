package m.com.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] drpDownItems = new String[]{
            "Select",
            "Cars",
            "Motorcycles",
            "Trucks"};

    private SeekBar seekbar;
    private Spinner drpDown;
    private Button startBtn;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drpDown = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdpt = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, drpDownItems);
        drpDown.setAdapter(spinnerAdpt);

        seekbar = findViewById(R.id.seekBar);
        startBtn = findViewById(R.id.button);

        seekBarMethods(seekbar);
        startButtonMethods(startBtn);


    }

    private void seekBarMethods(SeekBar sb)
    {

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int trackProgress = 0;

            /* Track seek bar progress with this method */

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                trackProgress = progress;
            }

            /* Track start of seek bar progress with this method */

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                /* Implement abstract method  */
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                time = trackProgress;
                Toast.makeText(MainActivity.this, "Time : "+time+" Seconds", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startButtonMethods(Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
               String drpDownVal = drpDown.getSelectedItem().toString();

               if(time == 0 || "Select".equalsIgnoreCase(drpDownVal))
               {
                   Toast.makeText(MainActivity.this, "Select Slideshow and Play time", Toast.LENGTH_LONG).show();
               }
               else
               {
                   Intent intent = new Intent(getApplicationContext(), SecondActivity_main.class);

                   Bundle dataBundle = new Bundle();
                   dataBundle.putString("type", drpDownVal);
                   dataBundle.putInt("playtime", time);

                   intent.putExtras(dataBundle);
                   startActivity(intent);
               }
            }
        });
    }
}
