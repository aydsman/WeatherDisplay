import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

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

        // City name
        String city = root.getJSONObject("city").getString("name");
        String countryName = root.getJSONObject("city").getString("country");

        country.setCity(city);
        country.setCountry(countryName);

        // 3-hour interval forecasts
        JSONArray list = root.getJSONArray("list");

        // Pick one forecast per day (every 8 entries)
        for (int i = 0; i < list.length(); i += 8) {

            JSONObject obj = list.getJSONObject(i);

            String dateText = obj.getString("dt_txt");
            // Format: 2026-01-29 12:00:00

            String[] dateParts = dateText.split(" ")[0].split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            double temp = obj.getJSONObject("main").getDouble("temp");

            WeatherDateOBJ weather =
                    new WeatherDateOBJ(year, month, day, temp);

            weatherList.add(weather);
        }

        return weatherList;
    }

    public CountryOBJ getCountry() {
        return country;
    }
}