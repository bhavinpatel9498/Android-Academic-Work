package m.com.lab4;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Cars Name list */
    String carsNameList[] = new String[]{
            "Dodge Challenger",
            "Ford Mustang",
            "Chevrolet Camaro",
            "BMW i5",
            "Lamborghini",
            "RollsRoyce",
            "Audi R8"};


    /* Cars desc list */

    String carsDescList[] = new String[]{
            "Price:$27000 * \nHorsepower: 305 to 808 hp",
            "Price:$25000 * \nHorsepower: 310 to 460 hp",
            "Price:$25500 * \nHorsepower: 275 to 650 hp",
            "Price:$50000 * \nHorsepower: Top 445 hp",
            "Price:$200000 * \nHorsepower: Top 602 hp",
            "Price:$250000 * \nHorsepower: Top 453 hp",
            "Price:$160000 * \nHorsepower: 540 to 610 hp"};

    /* Cars Image list */

    Integer carsImageList[] = {
            R.drawable.dodge,
            R.drawable.mustang,
            R.drawable.camaro,
            R.drawable.bmwi5,
            R.drawable.lamborghini,
            R.drawable.rollsroyce,
            R.drawable.audir8};

    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView)findViewById(R.id.carslist);
        listAdapter = new CarsAdapterList(MainActivity.this, carsImageList, carsNameList, carsDescList);
        listView.setAdapter(listAdapter);

        /* Implementing lister for item list click. Respective image for clicked list item would pop up on screen */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                Dialog showImgDialog = new Dialog(MainActivity.this);
                showImgDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                showImgDialog.requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
                showImgDialog.requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

                showImgDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(carsImageList[pos]);

                RelativeLayout.LayoutParams relLayout = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                showImgDialog.addContentView(imageView,relLayout);
                showImgDialog.show();
            }
        });
    }
}
