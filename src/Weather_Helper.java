import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
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

    public String getCondition(int day) {
        return weatherList.get(day).getCondition();
    }

    public String getDayOfWeek(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        LocalDate date = LocalDate.of(weatherOBJ.year, weatherOBJ.month, weatherOBJ.day);
        return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    public String getTimeLabel(int day) {
        WeatherDateOBJ weatherOBJ = weatherList.get(day);
        LocalTime time = LocalTime.of(weatherOBJ.hour, weatherOBJ.minute);
        return time.format(DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault()));
    }

}
