import java.util.ArrayList;

public class Display {
    ArrayList<WeatherDateOBJ> weatherList;
    CountryOBJ country;
    Weather_Helper weatherUtil;

    public Display(ArrayList<WeatherDateOBJ> weatherList, CountryOBJ country) {
        this.weatherList = weatherList;
        this.country = country;
        this.weatherUtil = new Weather_Helper(this.weatherList);
    }

    public void print() {
        System.out.printf("Country: " + country.getCountry() + " || Year: " + weatherUtil.getYear(0));
        System.out.println("Region: " + country.getRegion() + " || Month: " + weatherUtil.getMonthName());
        System.out.println("City: " + country.getCity() + " || Today's date: " + weatherUtil.getDay(0));

    }
}
