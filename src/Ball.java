import java.awt.*;
import java.util.Random;

class Ball  {
    Random random = new Random();
    int diameter = 70;

    // Initial position for ball is randomized within frame
    double x = random.nextInt(300 - diameter);
    double y = random.nextInt(500 - diameter);

    double dx = 5; // Horizontal speed x
    double dy = 5; // Horizontal speed y

    // Update ball position
    public void updatePosition(int panelWidth, int panelHeight) {
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
    }
}
