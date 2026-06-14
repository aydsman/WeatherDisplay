import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

public class RoundedPanel extends JPanel {

    private final int radius;
    private Color gradientStart;
    private Color gradientEnd;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    public void setGradient(Color start, Color end) {
        this.gradientStart = start;
        this.gradientEnd = end;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (gradientStart != null && gradientEnd != null) {
            g2.setPaint(new GradientPaint(0, 0, gradientStart, 0, getHeight(), gradientEnd));
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }

    public void applyBorder(AppTheme theme) {
        setBorder(BorderFactory.createLineBorder(theme.border, 1));
    }
}
