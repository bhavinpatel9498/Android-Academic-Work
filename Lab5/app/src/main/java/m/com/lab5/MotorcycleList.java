package m.com.lab5;


public class MotorcycleList
{

    private String motorcycleNameList[] = new String[]{
            "Sports buster",
            "Indian Motorcycles",
            "Triumph Thunder",
            "Triumph Storm",
            "Honda Rebel",
            "Energica Ego",
            "Harley Davidson",
            "Suzuki",
            "BMW Hybrid",
            "Yamaha"};

    private Integer motorcycleImageList[] = new Integer[]{
            R.drawable.bike1,
            R.drawable.bike2,
            R.drawable.bike3,
            R.drawable.bike4,
            R.drawable.bike5,
            R.drawable.bike6,
            R.drawable.bike7,
            R.drawable.bike8,
            R.drawable.bike9,
            R.drawable.bike10};

    public Integer[] getImageList()
    {
        return motorcycleImageList;
    }

    public String[] getNameList()
    {
        return motorcycleNameList;
    }

}