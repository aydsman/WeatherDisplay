import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Preferences {

    private static final Path FILE =
            Paths.get(System.getProperty("user.home"), ".weatherdisplay", "preferences.json");

    private AppTheme.AppearanceMode appearanceMode = AppTheme.AppearanceMode.LIGHT;
    private AppTheme.ColorScheme colorScheme = AppTheme.ColorScheme.NORMAL;
    private final List<String> cities = new ArrayList<>();

    public static Preferences load() {
        Preferences prefs = new Preferences();
        try {
            if (Files.exists(FILE)) {
                JSONObject json = new JSONObject(Files.readString(FILE));
                prefs.appearanceMode = parseMode(json.optString("appearanceMode"));
                prefs.colorScheme = parseScheme(json.optString("colorScheme"));

                JSONArray savedCities = json.optJSONArray("cities");
                if (savedCities != null) {
                    for (int i = 0; i < savedCities.length(); i++) {
                        String city = savedCities.optString(i, "").trim();
                        if (!city.isEmpty()) {
                            prefs.cities.add(city);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
            // Corrupt or unreadable file: fall back to defaults.
        }
        return prefs;
    }

    public void save() {
        try {
            JSONObject json = new JSONObject();
            json.put("appearanceMode", appearanceMode.name());
            json.put("colorScheme", colorScheme.name());
            json.put("cities", new JSONArray(cities));

            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, json.toString(2));
        } catch (IOException ignored) {
            // Saving preferences is best-effort; ignore write failures.
        }
    }

    private static AppTheme.AppearanceMode parseMode(String value) {
        try {
            return AppTheme.AppearanceMode.valueOf(value);
        } catch (IllegalArgumentException e) {
            return AppTheme.AppearanceMode.LIGHT;
        }
    }

    private static AppTheme.ColorScheme parseScheme(String value) {
        try {
            return AppTheme.ColorScheme.valueOf(value);
        } catch (IllegalArgumentException e) {
            return AppTheme.ColorScheme.NORMAL;
        }
    }

    public AppTheme.AppearanceMode getAppearanceMode() {
        return appearanceMode;
    }

    public void setAppearanceMode(AppTheme.AppearanceMode appearanceMode) {
        this.appearanceMode = appearanceMode;
    }

    public AppTheme.ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(AppTheme.ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public List<String> getCities() {
        return new ArrayList<>(cities);
    }

    public boolean hasCity(String city) {
        for (String existing : cities) {
            if (existing.equalsIgnoreCase(city.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean addCity(String city) {
        String trimmed = city == null ? "" : city.trim();
        if (trimmed.isEmpty() || hasCity(trimmed)) {
            return false;
        }
        cities.add(trimmed);
        return true;
    }

    public void removeCity(String city) {
        cities.removeIf(existing -> existing.equalsIgnoreCase(city.trim()));
    }
}
