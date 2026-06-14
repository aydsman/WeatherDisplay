import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class SearchBarPanel extends JPanel implements Themed {

    private final JLabel titleLabel;
    private final RoundedPanel searchPanel;
    private final JLabel cityLabel;
    private final JTextField inputField;
    private final StyledButton searchButton;
    private final StyledButton settingsButton;

    public SearchBarPanel() {
        setLayout(new BorderLayout(16, 0));
        setOpaque(false);

        titleLabel = new JLabel("Weather Display");
        searchPanel = new RoundedPanel(16);
        cityLabel = new JLabel("City");
        inputField = new JTextField();
        searchButton = new StyledButton("Search");
        settingsButton = new StyledButton("Settings");

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        left.add(titleLabel);

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setMaximumSize(new Dimension(560, 46));
        searchPanel.setPreferredSize(new Dimension(560, 46));

        cityLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        inputField.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 8));
        inputField.setOpaque(false);
        searchButton.setPreferredSize(new Dimension(96, 40));
        settingsButton.setPreferredSize(new Dimension(108, 40));

        searchPanel.add(cityLabel);
        searchPanel.add(Box.createHorizontalGlue());
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        searchPanel.add(inputField);
        searchPanel.add(Box.createHorizontalStrut(8));
        searchPanel.add(searchButton);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        right.setOpaque(false);
        right.add(settingsButton);

        add(left, BorderLayout.WEST);
        add(searchPanel, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }

    public String getUserInput() {
        return inputField.getText().trim();
    }

    public void clearInput() {
        inputField.setText("");
    }

    public void setInputText(String text) {
        inputField.setText(text);
    }

    public StyledButton getSearchButton() {
        return searchButton;
    }

    public StyledButton getSettingsButton() {
        return settingsButton;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void setSearchEnabled(boolean enabled) {
        searchButton.setEnabled(enabled);
        inputField.setEnabled(enabled);
    }

    @Override
    public void applyTheme(AppTheme theme) {
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        titleLabel.setForeground(theme.primaryText);
        titleLabel.setFont(theme.titleFont);

        searchPanel.setBackground(theme.inputBackground);
        searchPanel.applyBorder(theme);

        cityLabel.setForeground(theme.secondaryText);
        cityLabel.setFont(theme.bodyFont);

        inputField.setBackground(theme.inputBackground);
        inputField.setForeground(theme.primaryText);
        inputField.setFont(theme.bodyFont);
        inputField.setCaretColor(theme.primaryText);

        searchButton.applyTheme(theme);
        settingsButton.applyTheme(theme);
    }
}
