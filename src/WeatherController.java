import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherController {

    private ArrayList<WeatherDateOBJ> listGlobal;
    private CountryOBJ countryGlobal;
    private String errorMessage;

    public WeatherController(String userIn) {
        String user = userIn;

        String url =
                "https://api.openweathermap.org/data/2.5/forecast?q="
                        + user.replace(" ", "%20")
                        + "&units=metric&appid=b8b84f7e7f71b55dd008cf1b3aee2a0b";

        try {
            API_Client API = new API_Client();
            String APIjson = API.fetchData(url);

            JSONObject root = new JSONObject(APIjson);
            String code = String.valueOf(root.opt("cod"));
            if (!"200".equals(code)) {
                errorMessage = "\"" + userIn.trim() + "\" was not found. Check the spelling and try again.";
                return;
            }

            listGlobal = API.parseWeather(APIjson);
            countryGlobal = API.getCountry();

        } catch (Exception e) {
            errorMessage = "Could not load weather. Check your internet connection and try again.";
        }
    }

    public boolean isSuccess() {
        return listGlobal != null && !listGlobal.isEmpty() && countryGlobal != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public CountryOBJ getCountryGlobal() {
        return countryGlobal;
    }

    public ArrayList<WeatherDateOBJ> getListGlobal() {
        return listGlobal;
    }
}
