import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class BallPanel extends JPanel implements ActionListener {
    Random random = new Random();
    private final Timer timer;
    private static ArrayList<Ball> balls;
    private int count = 2;
    private final int INITIAL_BALLS = 2;

    private boolean debugMode = false;

    public BallPanel() {
        balls = new ArrayList<>();
        initialize(count);
        timer = new Timer(10, this);
        timer.start();
    }

    public void initialize(int count) {
        for (int i = 0; i < count; i++) {
            balls.add(createBall());
        }
    }

    public Ball createBall() {
        int randomX = random.nextInt(400);
        int randomY = random.nextInt(400);
        Ball newBall = new Ball(randomX, randomY);

        int dx = random.nextInt(5) + 1;
        int dy = random.nextInt(5) + 1;
        if (random.nextBoolean()) dx = -dx;
        if (random.nextBoolean()) dy = -dy;

        newBall.setVelocity(dx, dy);
        return newBall;
    }

    public void addBall() {
        balls.add(createBall());
        repaint();
    }

    public void resetBall() {
        balls.clear();
        initialize(INITIAL_BALLS);
        repaint();
    }

    public void setSpeed(int speed) {
        timer.setDelay(21 - speed); // Higher slider value = faster
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);

        for (Ball ball : balls) {
            ball.draw(g);
        }

        if (debugMode) {
            g.setColor(Color.RED);
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    drawDebugLines(g, balls.get(i), balls.get(j));
                }
            }
        }
    }

    public void drawDebugLines(Graphics g, Ball ball1, Ball ball2) {
        Point pos1 = ball1.getPosition();
        Point pos2 = ball2.getPosition();

        int centerX1 = (int) (pos1.x + ball1.getRadius());
        int centerY1 = (int) (pos1.y + ball1.getRadius());

        int centerX2 = (int) (pos2.x + ball2.getRadius());
        int centerY2 = (int) (pos2.y + ball2.getRadius());

        // Calculate the distances between the two centers
        int deltaX = centerX2 - centerX1;
        int deltaY = centerY2 - centerY1;

        // Draw the horizontal line
        g.setColor(Color.GREEN);
        g.drawLine(centerX1, centerY1, centerX1 + deltaX, centerY1);

        // Draw the vertical line
        g.setColor(Color.BLUE);
        g.drawLine(centerX1 + deltaX, centerY1, centerX1 + deltaX, centerY1 + deltaY);

        // Draw the hypotenuse
        g.setColor(Color.RED);
        g.drawLine(centerX1, centerY1, centerX2, centerY2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int steps = 2;
        for (int step = 0; step < steps; step++) {
            for (Ball ball : balls) {
                ball.update(getHeight(), getWidth());
            }
            checkCollisions();
        }
        repaint();
    }

    public void checkCollisions() {
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball1 = balls.get(i);
                Ball ball2 = balls.get(j);

                double distance = ball1.getDistance(ball2);
                double sumOfRadi = (ball1.getDiameter() / 2.0) + (ball2.getDiameter() / 2.0);

                if (distance <= sumOfRadi) {
                    handleCollision(ball1, ball2);
                }
            }
        }
    }

    public void handleCollision(Ball ball1, Ball ball2) {
        Point2D velocity1 = ball1.getVelocity();
        Point2D velocity2 = ball2.getVelocity();

        double dx = ball2.getPosition().x - ball1.getPosition().x;
        double dy = ball2.getPosition().y - ball1.getPosition().y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) {
            return;
        }

        double nx = dx / distance;
        double ny = dy / distance;

        double dotProduct = (velocity2.getX() - velocity1.getX()) * nx + (velocity2.getY() - velocity1.getY()) * ny;

        if (dotProduct < 0) {
            double mass1 = ball1.getMass();
            double mass2 = ball2.getMass();

            double impulse = 2 * dotProduct / (mass1 + mass2);

            double overlap = ball1.getRadius() + ball2.getRadius() - distance;
            ball1.getPosition().translate((int)(overlap * nx), (int)(overlap * ny));
            ball2.getPosition().translate((int)(-overlap * nx), (int)(-overlap * ny));

            velocity1.setLocation(velocity1.getX() + impulse * mass2 * nx, velocity1.getY() + impulse * mass2 * ny);
            velocity2.setLocation(velocity2.getX() - impulse * mass1 * nx, velocity2.getY() - impulse * mass1 * ny);
        }
    }
}