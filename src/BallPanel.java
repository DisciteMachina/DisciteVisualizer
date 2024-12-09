import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Timer timer = new Timer(16, this);
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
        repaint();
    }

    public void resetBall() {
        balls.clear();
        initialize(numOfBalls);
        repaint();
    }
}