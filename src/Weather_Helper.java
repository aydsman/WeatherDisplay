import java.util.ArrayList;

public class Weather_Helper {
    ArrayList<WeatherDateOBJ> weatherList;
    ArrayList<String> monthList = new ArrayList<>(
            java.util.Arrays.asList(
                    "January", "February", "March",
                    "April", "May", "June", "July",
                    "August", "September", "October",
                    "November", "December"
            )
    );

    public Weather_Helper(ArrayList<WeatherDateOBJ> weatherList) {
        this.weatherList = weatherList;
    }

    public int getDay(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day - 1);
        return weatherOBJ.day;
    }

    public int getMonthNum(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day - 1);
        return weatherOBJ.month;
    }

    public String getMonthName() {
        WeatherDateOBJ weatherOBJ = weatherList.get(0);
        int month = weatherOBJ.month;
        return monthList.get(month + 1);
    }

    public int getYear(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day - 1);
        return weatherOBJ.year;
    }

    public double getTemp(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day - 1);
        return weatherOBJ.temp;
    }

}
