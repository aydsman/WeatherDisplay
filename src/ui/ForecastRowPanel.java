import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ForecastRowPanel extends JPanel implements Themed {

    private final List<ForecastCardPanel> cards = new ArrayList<>();

    public ForecastRowPanel() {
        setLayout(new GridLayout(1, 5, 16, 0));
        setOpaque(false);

        for (int i = 0; i < 5; i++) {
            ForecastCardPanel card = new ForecastCardPanel();
            cards.add(card);
            add(card);
        }
    }

    public void showForecast(ArrayList<WeatherDateOBJ> weatherList, Weather_Helper helper) {
        int daysToShow = Math.min(5, weatherList.size());

        for (int i = 0; i < cards.size(); i++) {
            if (i < daysToShow) {
                String day = helper.getDayOfWeek(i);
                String date = helper.getMonthName(i).substring(0, 3) + " " + helper.getDay(i)
                        + " · " + helper.getTimeLabel(i);
                cards.get(i).setForecast(
                        day,
                        date,
                        helper.getCondition(i),
                        helper.getTemp(i),
                        i == 0
                );
                cards.get(i).setVisible(true);
            } else {
                cards.get(i).setVisible(false);
            }
        }
    }

    public void clear() {
        for (ForecastCardPanel card : cards) {
            card.setVisible(false);
        }
    }

    @Override
    public void applyTheme(AppTheme theme) {
        setBackground(theme.background);
        for (ForecastCardPanel card : cards) {
            card.applyTheme(theme);
        }
    }
}
