import javax.swing.*;

public class GUI_Helper {

    public JTable createWeatherTable(String[] days, double[] temps) {
        int n = days.length;

        // 2 rows: one for "Day", one for "Temp"
        String[][] data = new String[1][n + 1]; // +1 for label column
        String[] cols = new String[n + 1];

        cols[0] = "Date";           // top-left corner label
        data[0][0] = "Temp";    // bottom-left corner label

        // Fill in the rest of the table
        for (int i = 0; i < n; i++) {
            cols[i + 1] = days[i];            // column header
            data[0][i + 1] = temps[i] + "°C"; // temp row
        }

        return new JTable(data, cols);
    }
}
