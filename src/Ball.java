import java.awt.*;
import java.util.Random;

public class Ball {
    Random random = new Random();
    private static final int DIAMETER_SCALE = 15;

    private Point position;
    private Point velocity;
    private final int mass;
    private final int diameter;

    public Ball(int x, int y) {
        this.position = new Point(x, y);
        this.velocity = new Point(0, 0); // Initial velocity
        this.mass = random.nextInt(8) + 1;
        this.diameter = mass * DIAMETER_SCALE;
    }

    public void update(int panelHeight, int panelWidth) {
        position.x += (velocity.x);
        position.y += (velocity.y);

        if (position.x < 0 || position.x + diameter > panelWidth) {
            velocity.x = -velocity.x;
        }
        if (position.y < 0 || position.y + diameter > panelHeight) {
            velocity.y = -velocity.y;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(position.x, position.y, diameter, diameter);

        g.setColor(Color.RED);
        int center = diameter / 4;
        int centerX = position.x + diameter / 2 - center / 2;
        int centerY = position.y + diameter / 2 - center / 2;

        g.fillOval(centerX, centerY, center, center);
    }

    public double getDistance(Ball otherBall) {
        Point pos1 = this.getPosition();
        Point pos2 = otherBall.getPosition();

        // Distance
        return pos1.distance(pos2);
    }

    public void drawDistance(Graphics g, Ball otherBall) {
        // Get positions of both balls
        Point pos1 = this.getPosition();
        Point pos2 = otherBall.getPosition();

        // Calculate the center of each ball
        int centerX1 = pos1.x + diameter / 2;
        int centerY1 = pos1.y + diameter / 2;

        int centerX2 = pos2.x + otherBall.diameter / 2;
        int centerY2 = pos2.y + otherBall.diameter / 2;

        // Calculate the distances between the two centers
        int deltaX = centerX2 - centerX1;
        int deltaY = centerY2 - centerY1;

        // Draw the horizontal line (X distance) in green
        g.setColor(Color.GREEN);
        g.drawLine(centerX1, centerY1, centerX1 + deltaX, centerY1);

        // Draw the vertical line (Y distance) in blue
        g.setColor(Color.BLUE);
        g.drawLine(centerX1 + deltaX, centerY1, centerX1 + deltaX, centerY1 + deltaY);

        // Draw the hypotenuse (actual distance) in red
        g.setColor(Color.RED);
        g.drawLine(centerX1, centerY1, centerX2, centerY2);
    }

    public void setVelocity(int dx, int dy) {
        velocity.setLocation(dx, dy);
    }

    public Point getPosition() {
        return position;
    }

    public Point getVelocity() {
        return velocity;
    }
}