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

        JSONObject json = new JSONObject(rawJsonString);

        country.setCountry(json.getString("country"));
        country.setCity(json.getString("city"));

        JSONArray forecastArray = json.getJSONArray("forecast");

        for (int i = 0; i < forecastArray.length(); i++) {

            JSONObject dayObj = forecastArray.getJSONObject(i);

            String[] parts = dayObj.getString("date").split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            double temp = dayObj.getDouble("temp");

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