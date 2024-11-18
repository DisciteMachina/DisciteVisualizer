import java.awt.*;

class Ball  {
    private static final int DIAMETER_SCALE_FACTOR = 15;

    private double x, y, dx, dy;
    private final double mass;
    private final int diameter, radius;

    private final Color defaultColor;
    private Color currentColor;
    private boolean isHit;

    public Ball() {
        this.mass = 1 + Math.random() * 6; // Mass in kg
        this.diameter = (int) (DIAMETER_SCALE_FACTOR * mass);
        this.radius = diameter / 2;
        this.dx = 8 / mass;
        this.dy = 8 / mass;
        this.x = Math.random() * (300 - diameter); // Position of ball is
        this.y = Math.random() * (500 - diameter); // randomized in the frame

        this.defaultColor = Color.WHITE;
        this.currentColor = defaultColor;
        this.isHit = false;
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

    public boolean isColliding(Ball otherBall) {
        double distance = Math.sqrt(Math.pow((this.x + this.radius) - (otherBall.x + otherBall.radius), 2) +
                Math.pow((this.y + this.radius) - (otherBall.y + otherBall.radius), 2));

        return distance < (this.radius + otherBall.radius);
    }

    public void handleCollision(Ball otherBall) {
        double tempDx = this.dx;
        double tempDy = this.dy;
        this.dx = otherBall.dx;
        this.dy = otherBall.dy;
        otherBall.dx = tempDx;
        otherBall.dy = tempDy;

        this.changeColorOnHit();
        otherBall.changeColorOnHit();
    }

    private void changeColorOnHit() {
        if (!isHit) {
            isHit = true;
            currentColor = Color.RED;
            new javax.swing.Timer(100, e -> {
                currentColor = defaultColor;
                isHit = false;
            }).start();
        }
    }

    // Draw the ball
    public void draw(Graphics g) {
        g.setColor(currentColor);
        g.fillOval((int) x, (int) y, diameter, diameter);
    }

    public double getHorizontalSpeed() {
        return dx;
    }

    public double getVerticalSpeed() {
        return dy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }
}