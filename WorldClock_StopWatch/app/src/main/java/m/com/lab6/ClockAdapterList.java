package m.com.lab6;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClockAdapterList extends ArrayAdapter
{
    private final Activity classContext;
    private final String[] countryList;
    private final String[] timeList;


    public ClockAdapterList(Activity actCtx, String[] cList, String[] tList)
    {
        super(actCtx, R.layout.clocklayout, cList);

        this.classContext = actCtx;
        this.countryList = cList;
        this.timeList = tList;
    }

    public View getView(int pos, View view, ViewGroup parentView)
    {
        LayoutInflater layoutInflater = this.classContext.getLayoutInflater();

        View listViewElement = layoutInflater.inflate(R.layout.clocklayout, null, true);

        TextView country = (TextView) listViewElement.findViewById(R.id.textView);
        TextView time = (TextView) listViewElement.findViewById(R.id.textView2);

        country.setText(countryList[pos]);
        time.setText(timeList[pos]);

        return listViewElement;

    }
}
