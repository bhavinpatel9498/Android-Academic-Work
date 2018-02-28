package m.com.lab4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/* This is an adapter class for List view */

public class CarsAdapterList extends ArrayAdapter
{

    private final Activity classContext;
    private final Integer[] CarImageList;
    private final String[] carNameList;
    private final String[] carDescList;

    public CarsAdapterList(Activity actCtx, Integer[] carsImage, String[] carsName, String[] carsDesc)
    {
        super(actCtx, R.layout.carlayout, carsName);

        this.classContext = actCtx;
        this.CarImageList = carsImage;
        this.carNameList = carsName;
        this.carDescList = carsDesc;
    }

    public View getView(int pos, View view, ViewGroup parentView)
    {
        LayoutInflater layoutInflater = this.classContext.getLayoutInflater();

        View listViewElement = layoutInflater.inflate(R.layout.carlayout, null, true);


        ImageView carImgView = (ImageView) listViewElement.findViewById(R.id.imageView);
        TextView carName = (TextView) listViewElement.findViewById(R.id.textView);
        TextView carDesc = (TextView) listViewElement.findViewById(R.id.textView2);


        carImgView.setImageResource(CarImageList[pos]);
        carName.setText(carNameList[pos]);
        carDesc.setText(carDescList[pos]);

        return listViewElement;

    }

}
