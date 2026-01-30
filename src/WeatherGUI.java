import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class WeatherGUI {
    GUI_Helper gui = new GUI_Helper();
    JFrame frame;
    JPanel mainPanel; // will hold the table
    JScrollPane tablePane;

    public WeatherGUI() {
        frame = new JFrame("Weather Display");
        frame.setSize(1000, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Main panel to hold table
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Input bar at bottom
        JPanel inputBar = gui.createInputBar();
        frame.add(inputBar, BorderLayout.SOUTH);

        // Attach listener to submit button inside input bar
        ((JButton) inputBar.getComponent(2)).addActionListener(e -> {
            String userCity = gui.getUserInput(); // get typed city

            // Fetch new weather data
            WeatherController weather = new WeatherController(userCity);
            ArrayList<WeatherDateOBJ> weatherList = weather.getListGlobal();
            CountryOBJ country = weather.getCountryGlobal();
            Weather_Helper weatherUtil = new Weather_Helper(weatherList);

            // Build day/temp arrays
            int daysToShow = Math.min(5, weatherList.size());
            String[] days = new String[daysToShow];
            double[] temps = new double[daysToShow];

            for (int i = 0; i < daysToShow; i++) {
                String month = weatherUtil.getMonthName(i);
                days[i] = month + " " + weatherUtil.getDay(i);
                temps[i] = weatherUtil.getTemp(i);
            }

            // Remove old table if exists
            if (tablePane != null) {
                mainPanel.remove(tablePane);
            }

            // Create new table
            tablePane = gui.createWeatherTable(days, temps, country);
            mainPanel.add(tablePane, BorderLayout.CENTER);

            // Refresh GUI
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        frame.setVisible(true);
    }
}
