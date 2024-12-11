import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Ball {
    Random random = new Random();
    private static final int DIAMETER_SCALE = 15;

    private final Point position;
    private final Point2D velocity;
    private final int mass;
    private final int diameter;
    private final Color color;

    public Ball(int x, int y) {
        this.position = new Point(x, y);
        this.velocity = new Point2D.Double(0, 0); // Initial velocity
        this.mass = random.nextInt(5) + 5;
        this.diameter = mass * DIAMETER_SCALE;
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
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
        // Ball
        g.setColor(color);
        g.fillOval(position.x, position.y, diameter, diameter);
    }

    public double getDistance(Ball otherBall) {
        Point pos1 = this.getPosition();
        Point pos2 = otherBall.getPosition();

        // Distance
        return pos1.distance(pos2);
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