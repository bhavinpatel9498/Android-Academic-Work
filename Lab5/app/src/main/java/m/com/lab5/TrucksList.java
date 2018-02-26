package m.com.lab5;


public class TrucksList
{

    private String trucksNameList[] = new String[]{
            "Western Star",
            "Nissan",
            "Chevrolet",
            "Ford",
            "GMC",
            "Volvo Heavy",
            "Volvo",
            "Tata",
            "Ashok Leyland",
            "MAN Trucks"};

    private Integer trucksImageList[] = new Integer[]{
            R.drawable.truck1,
            R.drawable.truck2,
            R.drawable.truck3,
            R.drawable.truck4,
            R.drawable.truck5,
            R.drawable.truck6,
            R.drawable.truck7,
            R.drawable.truck8,
            R.drawable.truck9,
            R.drawable.truck10};

    public Integer[] getImageList()
    {
        return trucksImageList;
    }

    public String[] getNameList()
    {
        return trucksNameList;
    }

}