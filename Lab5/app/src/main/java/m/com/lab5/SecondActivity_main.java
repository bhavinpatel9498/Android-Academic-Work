package m.com.lab5;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity_main extends AppCompatActivity
{
    private String playType = "";
    private int playTime = 0;
    private String[] nameList = null;
    private Integer[] imageList = null;
    private Handler threadHandler = new Handler();
    private int index = 0;

    private ImageView img;
    private TextView textView;
    private TextView picNumber;

    private Runnable myRunnable = new Runnable() {
        public void run()
        {
            img.setImageResource(imageList[index]);
            textView.setText(nameList[index]);

            picNumber.setText("Pic: "+(index+1));

            if(index == (nameList.length-1))
            {
                index = 0;
            }
            else
            {
                index++;
            }

            threadHandler.postDelayed(myRunnable, playTime*1000);
        }
    };

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Bundle dataBundle =  getIntent().getExtras();

        playType = dataBundle.getString("type");
        playTime =  dataBundle.getInt("playtime");

        if("Cars".equalsIgnoreCase(playType))
        {
            CarsList c = new CarsList();
            nameList = c.getNameList();
            imageList = c.getImageList();
        }
        else if("Motorcycles".equalsIgnoreCase(playType))
        {
            MotorcycleList c = new MotorcycleList();
            nameList = c.getNameList();
            imageList = c.getImageList();
        }
        else if("Trucks".equalsIgnoreCase(playType))
        {
            TrucksList c = new TrucksList();
            nameList = c.getNameList();
            imageList = c.getImageList();
        }

        img = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);
        picNumber = findViewById(R.id.textView1);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        threadHandler.removeCallbacks(myRunnable);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        myRunnable.run();
    }

}
