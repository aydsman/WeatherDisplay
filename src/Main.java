import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // User input for city
        System.out.print("Type in a city --> ");
        Scanner s = new Scanner(System.in);
        String userCity = s.nextLine();

        // Store URL in String
        String url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?q="
                + userCity.replace(" ", "%20")
                + "&format=json&num_of_days=7&key=e28cc0110e394f6889014943262701";
        try {
            // API Logic
            API_Client API = new API_Client(); // Create API object
            String APIjson = API.fetchData(url); // Store raw JSON data
            ArrayList<WeatherDateOBJ> weatherListAPI = API.parseWeather(APIjson);

            // Copy country object from API class
            CountryOBJ country = API.getCountry();
            country.setCity(userCity);

            Display display = new Display(weatherListAPI, country);
            display.print();

        } catch (Exception e) {
            System.out.println("Error fetching or parsing weather data: " + e.getMessage());
            e.printStackTrace();
        }

    }
}