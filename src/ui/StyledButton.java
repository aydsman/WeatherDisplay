import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton implements Themed {

    private AppTheme theme;
    private boolean hover;
    private final int radius = 12;

    public StyledButton(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

        Color top = theme == null ? getBackground() : (hover ? theme.accentHover : theme.accent);
        Color bottom = theme == null ? getBackground() : (hover ? theme.accent : theme.accentHover);

        if (top != null && bottom != null) {
            g2.setPaint(new GradientPaint(0, 0, top, 0, getHeight(), bottom));
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void applyTheme(AppTheme theme) {
        this.theme = theme;
        setForeground(theme.buttonText);
        setFont(theme.bodyFont.deriveFont(java.awt.Font.BOLD));
        repaint();
    }
}
