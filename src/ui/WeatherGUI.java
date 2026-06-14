import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class WeatherGUI implements ThemeManager.ThemeListener {

    private static final String VIEW_WEATHER = "weather";
    private static final String VIEW_SETTINGS = "settings";

    private final Preferences prefs;

    private final JFrame frame;
    private final GradientPanel root;
    private final SearchBarPanel searchBar;
    private final CityListPanel sidebar;
    private final HeroPanel heroPanel;
    private final ForecastRowPanel forecastRow;
    private final JLabel forecastTitle;
    private final SettingsPanel settingsPanel;
    private final CardLayout cardLayout;
    private final JPanel cards;

    private String currentCity;

    public WeatherGUI() {
        prefs = Preferences.load();
        ThemeManager.setAppearanceMode(prefs.getAppearanceMode());
        ThemeManager.setColorScheme(prefs.getColorScheme());

        frame = new JFrame("Weather Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1180, 680));
        frame.setLayout(new BorderLayout());

        root = new GradientPanel();
        root.setLayout(new BorderLayout());

        searchBar = new SearchBarPanel();
        sidebar = new CityListPanel(this::loadCity, this::addCurrentCity);
        heroPanel = new HeroPanel();
        forecastRow = new ForecastRowPanel();
        forecastTitle = new JLabel("5-Day Forecast");
        settingsPanel = new SettingsPanel(prefs, this::showWeatherView);

        JPanel forecastSection = new JPanel(new BorderLayout(0, 14));
        forecastSection.setOpaque(false);
        forecastSection.add(forecastTitle, BorderLayout.NORTH);
        forecastSection.add(forecastRow, BorderLayout.CENTER);

        JPanel weatherView = new JPanel(new BorderLayout(0, 22));
        weatherView.setOpaque(false);
        weatherView.add(heroPanel, BorderLayout.NORTH);
        weatherView.add(forecastSection, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);
        cards.add(weatherView, VIEW_WEATHER);
        cards.add(settingsPanel, VIEW_SETTINGS);

        JPanel mainArea = new JPanel(new BorderLayout(22, 0));
        mainArea.setOpaque(false);
        mainArea.add(sidebar, BorderLayout.WEST);
        mainArea.add(cards, BorderLayout.CENTER);

        JPanel content = new JPanel(new BorderLayout(0, 22));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(28, 30, 30, 30));
        content.add(searchBar, BorderLayout.NORTH);
        content.add(mainArea, BorderLayout.CENTER);

        root.add(content, BorderLayout.CENTER);
        frame.add(root, BorderLayout.CENTER);

        searchBar.getSearchButton().addActionListener(e -> performSearch());
        searchBar.getInputField().addActionListener(e -> performSearch());
        searchBar.getSettingsButton().addActionListener(e -> toggleSettings());

        ThemeManager.addListener(this);
        heroPanel.showEmpty();
        forecastRow.clear();
        sidebar.setCities(prefs.getCities());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void toggleSettings() {
        if (settingsPanel.isShowing()) {
            showWeatherView();
        } else {
            cardLayout.show(cards, VIEW_SETTINGS);
        }
    }

    private void showWeatherView() {
        cardLayout.show(cards, VIEW_WEATHER);
    }

    private void loadCity(String city) {
        searchBar.setInputText(city);
        performSearch();
    }

    private void addCurrentCity() {
        showWeatherView();
        if (currentCity == null || currentCity.isBlank()) {
            heroPanel.showError("Search for a city first, then press Add a city.");
            return;
        }
        if (prefs.addCity(currentCity)) {
            prefs.save();
            sidebar.setCities(prefs.getCities());
        }
    }

    private void performSearch() {
        String city = searchBar.getUserInput();
        if (city.isBlank()) {
            showWeatherView();
            heroPanel.showError("Please enter a city name.");
            forecastRow.clear();
            return;
        }

        showWeatherView();
        searchBar.setSearchEnabled(false);
        heroPanel.showLoading();
        forecastRow.clear();

        new SwingWorker<WeatherController, Void>() {
            @Override
            protected WeatherController doInBackground() {
                return new WeatherController(city);
            }

            @Override
            protected void done() {
                searchBar.setSearchEnabled(true);
                try {
                    WeatherController controller = get();
                    if (controller.isSuccess()) {
                        showWeather(controller);
                        currentCity = controller.getCountryGlobal().getCity();
                    } else {
                        heroPanel.showError(
                                controller.getErrorMessage() == null
                                        ? "City not found. Try another search."
                                        : controller.getErrorMessage()
                        );
                        forecastRow.clear();
                    }
                } catch (Exception ex) {
                    heroPanel.showError("Could not load weather data.");
                    forecastRow.clear();
                }
            }
        }.execute();
    }

    private void showWeather(WeatherController controller) {
        ArrayList<WeatherDateOBJ> weatherList = controller.getListGlobal();
        CountryOBJ country = controller.getCountryGlobal();
        Weather_Helper helper = new Weather_Helper(weatherList);

        heroPanel.showWeather(country, weatherList.getFirst(), helper.getTimeLabel(0));
        forecastRow.showForecast(weatherList, helper);
    }

    @Override
    public void onThemeChanged(AppTheme theme) {
        root.setGradient(theme.background, theme.backgroundEnd);

        searchBar.applyTheme(theme);
        sidebar.applyTheme(theme);
        heroPanel.applyTheme(theme);
        forecastRow.applyTheme(theme);
        settingsPanel.applyTheme(theme);

        forecastTitle.setForeground(theme.secondaryText);
        forecastTitle.setFont(theme.bodyFont.deriveFont(java.awt.Font.BOLD, 16f));

        frame.revalidate();
        frame.repaint();
    }
}
