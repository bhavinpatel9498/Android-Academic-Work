package m.com.lab5;


public class CarsList
{

    private String carsNameList[] = new String[]{
            "Dodge Challenger",
            "Ford Mustang",
            "Chevrolet Camaro",
            "BMW i5",
            "Lamborghini",
            "RollsRoyce",
            "Audi R8",
            "BMW i8",
            "Mercedes Benz",
            "Dodge Charger"};

    private Integer carsImageList[] = new Integer[]{
            R.drawable.dodge,
            R.drawable.mustang,
            R.drawable.camaro,
            R.drawable.bmwi5,
            R.drawable.lamborghini,
            R.drawable.rollsroyce,
            R.drawable.audir8,
            R.drawable.bmwi8,
            R.drawable.mercedes,
            R.drawable.charger};

    public Integer[] getImageList()
    {
        return carsImageList;
    }

    public String[] getNameList()
    {
        return carsNameList;
    }

}