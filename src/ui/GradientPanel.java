import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

public class GradientPanel extends JPanel {

    private Color start;
    private Color end;

    public GradientPanel() {
        setOpaque(true);
    }

    public void setGradient(Color start, Color end) {
        this.start = start;
        this.end = end;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (start == null || end == null) {
            super.paintComponent(g);
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, start, 0, getHeight(), end));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
