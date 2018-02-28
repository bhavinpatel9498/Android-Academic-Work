package m.com.lab3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView farText;
    private TextView celText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setting listeners for seek bar widget*/
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        farText = (TextView)findViewById(R.id.textView4);
        celText = (TextView)findViewById(R.id.textView5);

        seekbarListener();
        seekbar.setProgress(0);
        calculateCelValue(0);
        showImageAndText("winter");
    }

    /* Function to calculate fahrenheit and celsius values  */
    private void calculateCelValue(int farValue)
    {
        long celsiusValue = Math.round((5.0/9.0)*(farValue-32));
        farText.setText(""+farValue);
        celText.setText(""+celsiusValue);
    }

    /* Function show image and text dynamically*/

    private void showImageAndText(String type)
    {
        ImageView img = (ImageView)findViewById(R.id.imageView);
        TextView disText = (TextView)findViewById(R.id.textView);

        if("winter".equalsIgnoreCase(type))
        {
            img.setImageResource(R.drawable.winter);
            disText.setText("Winter is Coming!");
            disText.setBackgroundColor(Color.parseColor("#31D3F0"));
        }
        else if("summer".equalsIgnoreCase(type))
        {
            img.setImageResource(R.drawable.summer);
            disText.setText("Summer is coming!");
            disText.setBackgroundColor(Color.parseColor("#FFC300"));
        }
        else if("hottest".equalsIgnoreCase(type))
        {
            img.setImageResource(R.drawable.hottest);
            disText.setText("Hot days are coming!");
            disText.setBackgroundColor(Color.parseColor("#B3000A"));
        }
    }

    /* Listener for seek bar */

    private void seekbarListener()
    {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int farValue = 0;

            /* Track seek bar progress with this method */

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                farValue = progress;

                calculateCelValue(farValue);

                if(farValue <= 40)
                {
                    showImageAndText("winter");
                }
                else if(farValue > 40 && farValue < 90)
                {
                    showImageAndText("summer");
                }
                else if(farValue >= 90 && farValue <= 200)
                {
                    showImageAndText("hottest");
                }

            }

            /* Track start of seek bar progress with this method */

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                /* Implement abstract method  */
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                /* Implement abstract method  */
                //Toast.makeText(MainActivity.this, ""+farValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
