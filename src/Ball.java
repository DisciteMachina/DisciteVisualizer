import java.awt.*;

class Ball  {
    private static final int DIAMETER_SCALE_FACTOR = 20;

    private double x, y, dx, dy;
    private final double mass;
    private final int diameter;

    public Ball() {
        this.mass = 1 + Math.random() * 6; // Mass in kg
        this.diameter = (int) (DIAMETER_SCALE_FACTOR * mass);
        this.dx = 5 / mass;
        this.dy = 5 / mass;
        this.x = Math.random() * (300 - diameter); // Position of ball is
        this.y = Math.random() * (500 - diameter); // randomized in the frame
    }

    // Update ball position
    public void updatePosition(int panelWidth, int panelHeight) {
        // edges
        if (x < 0 || x > panelWidth - diameter) {
            dx = -dx;
        }
        if (y < 0 || y > panelHeight - diameter) {
            dy = -dy;
        }

        x += dx;
        y += dy;
    }

    // Draw the ball
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, diameter, diameter);

        g.setColor(Color.RED);
        g.drawString("Diameter: " + diameter, (int) x, (int) y + diameter + 10);

    }
}