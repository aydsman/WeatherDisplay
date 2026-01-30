import java.util.ArrayList;

public class WeatherController {

    private GUI_Helper gui;
    private ArrayList<WeatherDateOBJ> listGlobal;
    private CountryOBJ countryGlobal;

    private String userIn;

    public WeatherController(String userIn) {
        String user = userIn; // User input
        this.userIn = userIn;
        System.out.println("USER ENTERED: [" + userIn + "]");


        // Store URL in String
        String url =
                "https://api.openweathermap.org/data/2.5/forecast?q="
                        + user.replace(" ", "%20")
                        + "&units=metric&appid=b8b84f7e7f71b55dd008cf1b3aee2a0b";

        try {
            // API Logic
            API_Client API = new API_Client(); // Create API object
            String APIjson = API.fetchData(url); // Store raw JSON data
            //System.out.println(APIjson); // Printing json data (debugging)
            ArrayList<WeatherDateOBJ> weatherListAPI = API.parseWeather(APIjson);
            listGlobal = weatherListAPI; // Copy this list onto global list (for get method)

            // Copy country object from API class
            CountryOBJ country = API.getCountry();
            countryGlobal = country; // Copy countryOBJ onto global
            country.setCity(user);

        } catch (Exception e) {
            System.out.println("Error fetching or parsing weather data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public CountryOBJ getCountryGlobal() {
        return countryGlobal;
    }

    public ArrayList<WeatherDateOBJ> getListGlobal() {
        return listGlobal;
    }
}
