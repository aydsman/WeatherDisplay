import java.util.ArrayList;
import java.util.List;

public final class ThemeManager {

    public interface ThemeListener {
        void onThemeChanged(AppTheme theme);
    }

    private static AppTheme currentTheme = AppTheme.resolve(
            AppTheme.AppearanceMode.LIGHT,
            AppTheme.ColorScheme.NORMAL
    );
    private static AppTheme.AppearanceMode appearanceMode = AppTheme.AppearanceMode.LIGHT;
    private static AppTheme.ColorScheme colorScheme = AppTheme.ColorScheme.NORMAL;
    private static final List<ThemeListener> listeners = new ArrayList<>();

    private ThemeManager() {
    }

    public static AppTheme getTheme() {
        return currentTheme;
    }

    public static AppTheme.AppearanceMode getAppearanceMode() {
        return appearanceMode;
    }

    public static AppTheme.ColorScheme getColorScheme() {
        return colorScheme;
    }

    public static void setAppearanceMode(AppTheme.AppearanceMode mode) {
        appearanceMode = mode;
        refreshTheme();
    }

    public static void setColorScheme(AppTheme.ColorScheme scheme) {
        colorScheme = scheme;
        refreshTheme();
    }

    public static void addListener(ThemeListener listener) {
        listeners.add(listener);
        listener.onThemeChanged(currentTheme);
    }

    public static void removeListener(ThemeListener listener) {
        listeners.remove(listener);
    }

    private static void refreshTheme() {
        currentTheme = AppTheme.resolve(appearanceMode, colorScheme);
        for (ThemeListener listener : listeners) {
            listener.onThemeChanged(currentTheme);
        }
    }
}
