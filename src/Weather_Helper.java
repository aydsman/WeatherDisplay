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
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        return weatherOBJ.day;
    }

    public int getMonthNum(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        return weatherOBJ.month;
    }

    public String getMonthName(int day) {
        int monthNum = getMonthNum(day);
        return monthList.get(monthNum - 1);
    }

    public int getYear(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        return weatherOBJ.year;
    }

    public double getTemp(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        return weatherOBJ.temp;
    }

}
