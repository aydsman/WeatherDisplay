import javax.swing.*;
import java.util.ArrayList;

public class WeatherGUI {
    GUI_Helper gui = new GUI_Helper();

    ArrayList<WeatherDateOBJ> weatherList;
    CountryOBJ country;
    Weather_Helper weatherUtil;

    public WeatherGUI(ArrayList<WeatherDateOBJ> weatherList, CountryOBJ country) {
        this.weatherList = weatherList;
        this.country = country;
        this.weatherUtil = new Weather_Helper(this.weatherList);

        String[] days = new String[7];
        double[] temps = new double[7];
        for (int i = 0; i < 7; i++) {
            String month = weatherUtil.getMonthName(i);
            days[i] = month + " " + String.valueOf(weatherUtil.getDay(i));
            temps[i] = weatherUtil.getTemp(i);
        }

        JFrame frame = new JFrame("Weather Display");

        frame.setSize(1000, 200);                 // Width, Height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);       // Center window

        // Example data


        // Call the helper method to create the table
        JTable table = gui.createWeatherTable(days, temps);

        // Add table to scroll pane and frame
        frame.add(new JScrollPane(table));

        frame.setVisible(true);                  // Show window
    }
}