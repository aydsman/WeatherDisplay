import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class API_Client {

    private CountryOBJ country;

    public API_Client() {
        this.country = new CountryOBJ();
    }

    public String fetchData(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public ArrayList<WeatherDateOBJ> parseWeather(String rawJsonString) {
        ArrayList<WeatherDateOBJ> weatherList = new ArrayList<>();

        JSONObject root = new JSONObject(rawJsonString);

        String city = root.getJSONObject("city").getString("name");
        String countryName = root.getJSONObject("city").getString("country");

        country.setCity(city);
        country.setCountry(countryName);

        int timezoneSeconds = root.getJSONObject("city").getInt("timezone");
        ZoneOffset cityOffset = ZoneOffset.ofTotalSeconds(timezoneSeconds);
        ZonedDateTime cityNow = ZonedDateTime.now(cityOffset);
        int targetMinutes = cityNow.getHour() * 60 + cityNow.getMinute();

        JSONArray list = root.getJSONArray("list");
        Map<LocalDate, List<ForecastEntry>> forecastsByDate = new LinkedHashMap<>();

        for (int i = 0; i < list.length(); i++) {
            JSONObject obj = list.getJSONObject(i);
            ZonedDateTime localTime = Instant.ofEpochSecond(obj.getLong("dt")).atZone(cityOffset);
            LocalDate date = localTime.toLocalDate();

            double temp = obj.getJSONObject("main").getDouble("temp");
            String condition = obj.getJSONArray("weather").getJSONObject(0).getString("main");

            forecastsByDate
                    .computeIfAbsent(date, ignored -> new ArrayList<>())
                    .add(new ForecastEntry(localTime, temp, condition));
        }

        int daysAdded = 0;
        for (Map.Entry<LocalDate, List<ForecastEntry>> entry : forecastsByDate.entrySet()) {
            if (daysAdded >= 5) {
                break;
            }

            ForecastEntry bestMatch = entry.getValue().stream()
                    .min(Comparator.comparingInt(
                            forecast -> Math.abs(forecast.minutesOfDay() - targetMinutes)
                    ))
                    .orElseThrow();

            ZonedDateTime forecastTime = bestMatch.localTime();
            weatherList.add(new WeatherDateOBJ(
                    forecastTime.getYear(),
                    forecastTime.getMonthValue(),
                    forecastTime.getDayOfMonth(),
                    forecastTime.getHour(),
                    forecastTime.getMinute(),
                    bestMatch.temp(),
                    bestMatch.condition()
            ));
            daysAdded++;
        }

        return weatherList;
    }

    public CountryOBJ getCountry() {
        return country;
    }

    private record ForecastEntry(ZonedDateTime localTime, double temp, String condition) {
        int minutesOfDay() {
            return localTime.getHour() * 60 + localTime.getMinute();
        }
    }
}
