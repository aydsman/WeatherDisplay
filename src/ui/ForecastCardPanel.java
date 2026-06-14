import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForecastCardPanel extends RoundedPanel implements Themed {

    private final JLabel dayLabel;
    private final JLabel dateLabel;
    private final JLabel iconLabel;
    private final JLabel tempLabel;
    private boolean highlightToday;
    private boolean hovered;
    private AppTheme theme;

    public ForecastCardPanel() {
        super(18);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        dayLabel = new JLabel("Mon");
        dateLabel = new JLabel("Jan 1");
        iconLabel = new JLabel("☀");
        tempLabel = new JLabel("20°");

        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(dayLabel);
        add(Box.createVerticalStrut(2));
        add(dateLabel);
        add(Box.createVerticalGlue());
        add(iconLabel);
        add(Box.createVerticalGlue());
        add(tempLabel);
        add(Box.createVerticalStrut(20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                refreshStyle();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                refreshStyle();
            }
        });
    }

    public void setForecast(String day, String date, String condition, double temp, boolean highlightToday) {
        this.highlightToday = highlightToday;
        dayLabel.setText(day);
        dateLabel.setText(date);
        iconLabel.setText(WeatherIcons.forCondition(condition));
        tempLabel.setText(String.format("%.0f°", temp));
        refreshStyle();
    }

    private void refreshStyle() {
        if (theme == null) {
            return;
        }

        if (hovered) {
            setGradient(theme.accentSoft, theme.cardEnd);
        } else {
            setGradient(theme.cardStart, theme.cardEnd);
        }

        int thickness = (highlightToday || hovered) ? 2 : 1;
        java.awt.Color borderColor = (highlightToday || hovered) ? theme.accent : theme.border;
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, thickness, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        repaint();
    }

    @Override
    public void applyTheme(AppTheme theme) {
        this.theme = theme;

        dayLabel.setForeground(theme.primaryText);
        dayLabel.setFont(theme.bodyFont.deriveFont(java.awt.Font.BOLD, 15f));

        dateLabel.setForeground(theme.secondaryText);
        dateLabel.setFont(theme.smallFont);

        iconLabel.setFont(theme.cardIconFont);

        tempLabel.setForeground(theme.accent);
        tempLabel.setFont(theme.headingFont.deriveFont(24f));

        refreshStyle();
    }
}
