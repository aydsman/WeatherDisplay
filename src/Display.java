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
        System.out.println("City: " + country.getCity());
        System.out.println("Country: " + country.getCountry() + "\n-----------------------------------------");
        for (int i = 0; i < 7; i++) {
            System.out.println("Today's date: " + weatherUtil.getMonthName(i) + " " + weatherUtil.getDay(i) + " || Temp: " + weatherUtil.getTemp(i) + "°C");
        }
    }
}
