import javax.swing.*;

public class GUI_Helper {

    private JTextField inputField;

    public JScrollPane createWeatherTable(String[] days, double[] temps, CountryOBJ country) {
        int n = days.length;

        String[][] data = new String[1][n + 1];
        String[] cols = new String[n + 1];

        cols[0] = "Date";
        data[0][0] = "Temp";

        for (int i = 0; i < n; i++) {
            cols[i + 1] = days[i];
            data[0][i + 1] = temps[i] + "°C";
        }

        JTable table = new JTable(data, cols);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(country.getCity() + ", " + country.getCountry()));

        return scrollPane;
    }

    public JPanel createInputBar() {
        JPanel panel = new JPanel();

        inputField = new JTextField(20);
        JButton submitButton = new JButton("Enter");

        panel.add(new JLabel("City:"));
        panel.add(inputField);
        panel.add(submitButton);

        return panel;
    }

    // NEW METHOD: get text directly from JTextField
    public String getUserInput() {
        if (inputField == null) return "";
        return inputField.getText().trim();
    }

    // OPTIONAL: allow external access to the submit button
    public JButton getSubmitButton() {
        if (inputField == null) return null;
        return (JButton) ((JPanel) inputField.getParent()).getComponent(2);
    }
}
