package m.com.lab6;

import android.util.Log;

public class ClockListValues
{

    private String countryList[] = new String[]{
            "United States",
            "India",
            "United Kingdom",
            "Japan"};

    private String TimeList[] = new String[]{
            "",
            "",
            "",
            ""};

    public String[] getCountryList()
    {
        return this.countryList;
    }

    public String[] getTimeList()
    {
        return this.TimeList;
    }

    public void setTimeList(String[] time)
    {
        this.TimeList = time;
    }
}
