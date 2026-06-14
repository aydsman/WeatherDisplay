import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListButton extends JButton implements Themed {

    private AppTheme theme;
    private boolean hover;
    private final boolean accentStyle;
    private final int radius = 12;

    public ListButton(String text, boolean accentStyle) {
        super(text);
        this.accentStyle = accentStyle;
        setHorizontalAlignment(SwingConstants.LEFT);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (theme != null) {
            Color fill = hover ? theme.accentSoft : theme.inputBackground;
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            if (accentStyle) {
                g2.setColor(theme.accent);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, radius, radius);
            }
        }
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void applyTheme(AppTheme theme) {
        this.theme = theme;
        setForeground(accentStyle ? theme.accent : theme.primaryText);
        setFont(theme.bodyFont.deriveFont(accentStyle ? Font.BOLD : Font.PLAIN, 14f));
        repaint();
    }
}
