import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class SettingsPanel extends JPanel implements Themed {

    private final RoundedPanel card;
    private final JLabel title;
    private final JLabel subtitle;
    private final JLabel modeLabel;
    private final JLabel schemeLabel;
    private final JComboBox<AppTheme.AppearanceMode> modeBox;
    private final JComboBox<AppTheme.ColorScheme> schemeBox;
    private final StyledButton backButton;
    private final StyledButton saveButton;
    private final JLabel statusLabel;

    public SettingsPanel(Preferences prefs, Runnable onBack) {
        setOpaque(false);
        setLayout(new GridBagLayout());

        card = new RoundedPanel(20);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 34, 30, 34));
        card.setMaximumSize(new Dimension(520, 440));
        card.setPreferredSize(new Dimension(520, 440));

        title = new JLabel("Settings");
        subtitle = new JLabel("Personalise how Weather Display looks");
        modeLabel = new JLabel("Appearance mode");
        schemeLabel = new JLabel("Colour theme");
        modeBox = new JComboBox<>(AppTheme.AppearanceMode.values());
        schemeBox = new JComboBox<>(AppTheme.ColorScheme.values());
        backButton = new StyledButton("← Back");
        saveButton = new StyledButton("Save");
        statusLabel = new JLabel(" ");

        modeBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value == AppTheme.AppearanceMode.LIGHT ? "Light mode" : "Dark mode");
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }
            return label;
        });

        schemeBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getLabel());
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }
            return label;
        });

        modeBox.setSelectedItem(ThemeManager.getAppearanceMode());
        schemeBox.setSelectedItem(ThemeManager.getColorScheme());

        modeBox.addActionListener(e -> {
            AppTheme.AppearanceMode mode = (AppTheme.AppearanceMode) modeBox.getSelectedItem();
            ThemeManager.setAppearanceMode(mode);
            statusLabel.setText("Unsaved changes");
        });
        schemeBox.addActionListener(e -> {
            AppTheme.ColorScheme scheme = (AppTheme.ColorScheme) schemeBox.getSelectedItem();
            ThemeManager.setColorScheme(scheme);
            statusLabel.setText("Unsaved changes");
        });
        backButton.addActionListener(e -> onBack.run());
        saveButton.addActionListener(e -> {
            prefs.setAppearanceMode((AppTheme.AppearanceMode) modeBox.getSelectedItem());
            prefs.setColorScheme((AppTheme.ColorScheme) schemeBox.getSelectedItem());
            prefs.save();
            statusLabel.setText("Settings saved ✓");
        });

        Dimension boxSize = new Dimension(Integer.MAX_VALUE, 40);
        modeBox.setMaximumSize(boxSize);
        schemeBox.setMaximumSize(boxSize);
        backButton.setPreferredSize(new Dimension(120, 42));
        saveButton.setPreferredSize(new Dimension(120, 42));

        JPanel buttonRow = new JPanel();
        buttonRow.setOpaque(false);
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        buttonRow.add(saveButton);
        buttonRow.add(Box.createHorizontalStrut(12));
        buttonRow.add(backButton);
        buttonRow.add(Box.createHorizontalGlue());

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        modeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        modeBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        schemeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        schemeBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(24));
        card.add(modeLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(modeBox);
        card.add(Box.createVerticalStrut(20));
        card.add(schemeLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(schemeBox);
        card.add(Box.createVerticalStrut(28));
        card.add(buttonRow);
        card.add(Box.createVerticalStrut(12));
        card.add(statusLabel);

        add(card);
    }

    @Override
    public void applyTheme(AppTheme theme) {
        card.setGradient(theme.cardStart, theme.cardEnd);
        card.applyBorder(theme);

        title.setForeground(theme.primaryText);
        title.setFont(theme.headingFont.deriveFont(26f));

        subtitle.setForeground(theme.secondaryText);
        subtitle.setFont(theme.bodyFont);

        modeLabel.setForeground(theme.secondaryText);
        modeLabel.setFont(theme.bodyFont.deriveFont(java.awt.Font.BOLD));

        schemeLabel.setForeground(theme.secondaryText);
        schemeLabel.setFont(theme.bodyFont.deriveFont(java.awt.Font.BOLD));

        modeBox.setBackground(theme.inputBackground);
        modeBox.setForeground(theme.primaryText);
        modeBox.setFont(theme.bodyFont);

        schemeBox.setBackground(theme.inputBackground);
        schemeBox.setForeground(theme.primaryText);
        schemeBox.setFont(theme.bodyFont);

        statusLabel.setForeground(theme.secondaryText);
        statusLabel.setFont(theme.smallFont);

        saveButton.applyTheme(theme);
        backButton.applyTheme(theme);
    }
}
