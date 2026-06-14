public class WeatherDateOBJ {
    int year;
    int month;
    int day;
    int hour;
    int minute;
    double temp;
    boolean celsius;
    String condition;

    public WeatherDateOBJ(int year, int month, int day, int hour, int minute, double temp, String condition) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.temp = temp;
        this.celsius = true;
        this.condition = condition == null ? "Clear" : condition;
    }

    public String getCondition() {
        return condition;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
