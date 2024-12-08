import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Ball {
    Random random = new Random();
    private static final int DIAMETER_SCALE = 15;

    private Point position;
    private Point2D velocity;
    private final int mass;
    private final int diameter;

    public Ball(int x, int y) {
        this.position = new Point(x, y);
        this.velocity = new Point2D.Double(0, 0); // Initial velocity
        this.mass = random.nextInt(8) + 1;
        this.diameter = mass * DIAMETER_SCALE;
    }

    public void update(int panelHeight, int panelWidth) {
        position.x += ((int)velocity.getX());
        position.y += ((int)velocity.getY());

        if (position.x < 0 || position.x + diameter > panelWidth) {
            velocity.setLocation(-velocity.getX(), velocity.getY());
        }
        if (position.y < 0 || position.y + diameter > panelHeight) {
            velocity.setLocation(velocity.getX(), -velocity.getY());
        }
    }

    public void draw(Graphics g) {
        // Not actually center
        int centerX = position.x;
        int centerY = position.y;

        // Ball
        g.setColor(Color.WHITE);
        g.fillOval(centerX, centerY, diameter, diameter);

        // Center
        g.setColor(Color.RED);
        int center = diameter / 8;
        int centerX2 = position.x + diameter / 2 - center / 2;
        int centerY2 = position.y + diameter / 2 - center / 2;
        g.fillOval(centerX2, centerY2, center, center);

        // Square
        g.setColor(Color.YELLOW);
        int boxX = position.x;
        int boxY = position.y;
        g.drawRect(boxX, boxY, diameter, diameter);
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

    public Point2D getVelocity() {
        return velocity;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getRadius() {
        return (double) diameter / 2;
    }

    public double getMass() {
        return mass;
    }
}