import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class HeroPanel extends RoundedPanel implements Themed {

    private final JLabel iconLabel;
    private final JLabel cityLabel;
    private final JLabel conditionLabel;
    private final JLabel detailLabel;
    private final JLabel tempLabel;

    public HeroPanel() {
        super(22);
        setLayout(new BorderLayout(24, 0));
        setBorder(BorderFactory.createEmptyBorder(28, 34, 28, 34));
        setPreferredSize(new Dimension(0, 190));

        iconLabel = new JLabel("🔍", JLabel.CENTER);
        cityLabel = new JLabel("Search for a city");
        conditionLabel = new JLabel("Enter a city name to begin");
        detailLabel = new JLabel(" ");
        tempLabel = new JLabel("--°");

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        cityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        conditionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(cityLabel);
        center.add(Box.createVerticalStrut(10));
        center.add(conditionLabel);
        center.add(Box.createVerticalStrut(4));
        center.add(detailLabel);
        center.add(Box.createVerticalGlue());

        tempLabel.setHorizontalAlignment(JLabel.RIGHT);
        tempLabel.setVerticalAlignment(JLabel.CENTER);

        add(iconLabel, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(tempLabel, BorderLayout.EAST);
    }

    public void showWeather(CountryOBJ country, WeatherDateOBJ today, String timeLabel) {
        iconLabel.setText(WeatherIcons.forCondition(today.getCondition()));
        cityLabel.setText(country.getCity() + ", " + country.getCountry());
        conditionLabel.setText(today.getCondition());
        detailLabel.setText("As of " + timeLabel + " local time");
        tempLabel.setText(String.format("%.0f°", today.temp));
    }

    public void showEmpty() {
        iconLabel.setText("🔍");
        cityLabel.setText("Search for a city");
        conditionLabel.setText("Enter a city name to begin");
        detailLabel.setText(" ");
        tempLabel.setText("--°");
    }

    public void showLoading() {
        iconLabel.setText("⏳");
        cityLabel.setText("Loading...");
        conditionLabel.setText("Fetching the latest weather");
        detailLabel.setText(" ");
        tempLabel.setText(" ");
    }

    public void showError(String message) {
        iconLabel.setText("⚠");
        cityLabel.setText("Something went wrong");
        conditionLabel.setText(message);
        detailLabel.setText(" ");
        tempLabel.setText(" ");
    }

    @Override
    public void applyTheme(AppTheme theme) {
        setGradient(theme.heroStart, theme.heroEnd);
        setBorder(BorderFactory.createEmptyBorder(28, 34, 28, 34));

        iconLabel.setFont(theme.heroIconFont);
        iconLabel.setForeground(theme.heroText);

        cityLabel.setForeground(theme.heroText);
        cityLabel.setFont(theme.heroCityFont);

        conditionLabel.setForeground(theme.heroSubText);
        conditionLabel.setFont(theme.headingFont);

        detailLabel.setForeground(theme.heroSubText);
        detailLabel.setFont(theme.smallFont);

        tempLabel.setForeground(theme.heroText);
        tempLabel.setFont(theme.heroTempFont);
    }
}
