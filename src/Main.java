import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Store URL in String
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=43.65&lon=-79.38&exclude=hourly%2Cminutely&units=metric&appid=5616f52a91714d59b35213048262301";
        try {
            // API Logic
            API_Client API = new API_Client(); // Create API object
            String APIjson = API.fetchData(url); // Store raw JSON data
            ArrayList<WeatherDateOBJ> weatherListAPI = API.parseWeather(APIjson);

            // Copy country object from API class
            CountryOBJ country = API.getCountry();

            Display display = new Display(weatherListAPI, country);
            display.print();

        } catch (Exception e) {
            System.out.println("Error fetching or parsing weather data: " + e.getMessage());
            e.printStackTrace();
        }

    }
}