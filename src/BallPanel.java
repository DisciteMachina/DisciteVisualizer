import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class BallPanel extends JPanel implements ActionListener {
    Random random = new Random();
    private static ArrayList<Ball> balls;
    private final int numOfBalls = 2;

    private static final int PANEL_WIDTH = 700;
    private static final int PANEL_HEIGHT = 800;

    public BallPanel() {
        balls = new ArrayList<>();
        initialize(numOfBalls);
        Timer timer = new Timer(10, this);
        timer.start();
    }

    public void initialize(int numOfBalls) {
        int randomX;
        int randomY;
        int randomdx;
        int randomdy;

        for (int i = 0; i < numOfBalls; i++) {
            randomX = random.nextInt(PANEL_WIDTH - 400);
            randomY = random.nextInt(PANEL_HEIGHT - 400);

            Ball ball = new Ball(randomX, randomY);
            balls.add(ball);

            randomdx = random.nextInt(5) + 1;
            randomdy = random.nextInt(5) + 1;

            // Randomly set the direction for velocity
            if (random.nextBoolean()) {
                randomdx = -randomdx;
            }
            if (random.nextBoolean()) {
                randomdy = -randomdy;
            }

            ball.setVelocity(randomdx, randomdy);

            System.out.println("------------");
            System.out.println("Ball Added: ");
            System.out.println("X Cord: " + randomX);
            System.out.println("Y Cord: " + randomY);
            System.out.println("Velocity: " + randomdx + ", " + randomdy);
            System.out.println("------------");
            System.out.println();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);

        for (Ball ball : balls) {
            ball.draw(g);
        }

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball1 = balls.get(i);
                Ball ball2 = balls.get(j);

                double distance = ball1.getDistance(ball2);
                ball1.drawDistance(g, ball2);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        for (Ball ball : balls) {
            ball.update(panelHeight, panelWidth);
        }
        checkCollisions();
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

        // Avoid division by zero in case balls are at the exact same location (unlikely but possible)
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

    public void resetBall() {
        balls.clear();
        initialize(numOfBalls);
        repaint();
    }
}