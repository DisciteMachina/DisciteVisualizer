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
        ball1 = new Ball();
        ball2  = new Ball();

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);

        ball1.draw(g);
        ball2.draw(g);
    }

    public void resetBall() {
        ball1 = new Ball();
        ball2 = new Ball();
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

    public Ball getBall1() {
        return ball1;
    }

    public Ball getBall2() {
        return ball2;
    }
}
