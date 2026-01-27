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
        JSONObject data = root.getJSONObject("data");

        // Get location string from API response
        JSONArray requestArr = data.getJSONArray("request");
        String location = requestArr.getJSONObject(0).getString("query"); // e.g., "Springfield, United States"

        // Split location into city and country
        String[] parts = location.split(",\\s*"); // Split by comma + optional space
        String city = parts[0];                   // First part is city
        String countryName = parts.length > 1 ? parts[1] : "Unknown"; // Second part is country (if present)

        // Store into CountryOBJ
        country.setCity(city);
        country.setCountry(countryName);

        // Get forecast array
        JSONArray weatherArray = data.getJSONArray("weather");

        for (int i = 0; i < weatherArray.length(); i++) {

            JSONObject dayObj = weatherArray.getJSONObject(i);

            String[] dateParts = dayObj.getString("date").split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            double temp = dayObj.getDouble("avgtempC");

            WeatherDateOBJ weather = new WeatherDateOBJ(year, month, day, temp);
            weatherList.add(weather);
        }

        return weatherList;
    }

    public CountryOBJ getCountry() {
        return country;
    }
}