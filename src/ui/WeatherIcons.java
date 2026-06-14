public final class WeatherIcons {

    private WeatherIcons() {
    }

    public static String forCondition(String condition) {
        if (condition == null) {
            return "🌡";
        }

        return switch (condition.toLowerCase()) {
            case "clear" -> "☀";
            case "clouds" -> "☁";
            case "rain" -> "🌧";
            case "drizzle" -> "🌦";
            case "thunderstorm" -> "⛈";
            case "snow" -> "❄";
            case "mist", "fog", "haze", "smoke" -> "🌫";
            default -> "🌡";
        };
    }
}
