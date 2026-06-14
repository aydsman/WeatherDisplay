import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CityListPanel extends RoundedPanel implements Themed {

    private final JLabel header;
    private final JPanel listContainer;
    private final Consumer<String> onSelect;
    private final Runnable onAdd;
    private AppTheme theme;
    private List<String> cities = new ArrayList<>();

    public CityListPanel(Consumer<String> onSelect, Runnable onAdd) {
        super(20);
        this.onSelect = onSelect;
        this.onAdd = onAdd;

        setLayout(new BorderLayout(0, 14));
        setBorder(BorderFactory.createEmptyBorder(20, 18, 20, 18));
        setPreferredSize(new Dimension(236, 0));

        header = new JLabel("Saved Cities");

        listContainer = new JPanel();
        listContainer.setOpaque(false);
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));

        JPanel listHolder = new JPanel(new BorderLayout());
        listHolder.setOpaque(false);
        listHolder.add(listContainer, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(
                listHolder,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        rebuild();
    }

    public void setCities(List<String> cities) {
        this.cities = new ArrayList<>(cities);
        rebuild();
    }

    private void rebuild() {
        listContainer.removeAll();

        for (String city : cities) {
            ListButton button = new ListButton(city, false);
            button.addActionListener(e -> onSelect.accept(city));
            configure(button);
            listContainer.add(button);
            listContainer.add(Box.createVerticalStrut(8));
        }

        ListButton addButton = new ListButton("+   Add a city", true);
        addButton.addActionListener(e -> onAdd.run());
        configure(addButton);
        listContainer.add(addButton);

        if (theme != null) {
            applyTheme(theme);
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    private void configure(ListButton button) {
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
    }

    @Override
    public void applyTheme(AppTheme theme) {
        this.theme = theme;
        setGradient(theme.cardStart, theme.cardEnd);
        applyBorder(theme);

        header.setForeground(theme.secondaryText);
        header.setFont(theme.bodyFont.deriveFont(Font.BOLD, 15f));

        for (Component component : listContainer.getComponents()) {
            if (component instanceof ListButton listButton) {
                listButton.applyTheme(theme);
            }
        }
    }
}
