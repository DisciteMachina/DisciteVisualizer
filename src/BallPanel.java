import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel to manage and display the balls
public class BallPanel extends JPanel implements ActionListener {
    Ball ball1;
    Ball ball2;
    private Timer timer;

    public BallPanel() {
        initialize();
        timer = new Timer(16, this);
        timer.start();
    }

    public void initialize() {
        do {
            ball1 = new Ball();
            ball2 = new Ball();
        } while (areBallsOverLapping(ball1, ball2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);

        ball1.draw(g);
        ball2.draw(g);
    }

    public void resetBall() {
        initialize();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball1.updatePosition(getWidth(), getHeight());
        ball2.updatePosition(getWidth(), getHeight());

        if (ball1.isColliding(ball2)) {
            ball1.handleCollision(ball2);
        }
        repaint(); // Repaint panel
    }

    private boolean areBallsOverLapping(Ball ball1, Ball ball2) {
        double distance = Math.sqrt(
                Math.pow((ball1.getX() + ball1.getRadius()) - (ball2.getX() + ball2.getRadius()), 2) +
                        Math.pow((ball1.getY() + ball1.getRadius()) - (ball2.getY() + ball2.getRadius()), 2)
        );
        return distance < (ball1.getRadius() + ball2.getRadius());
    }

    public Ball getBall1() {
        return ball1;
    }

    public Ball getBall2() {
        return ball2;
    }
}
