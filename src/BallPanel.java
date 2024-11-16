import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel to manage and display the balls
public class BallPanel extends JPanel implements ActionListener {
    Ball ball1;
    Ball ball2;

    Timer timer;

    public BallPanel() {
        ball1 = new Ball();
        ball2  = new Ball();

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);

        ball1.draw(g);
        ball2.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball1.updatePosition(getWidth(), getHeight());
        ball2.updatePosition(getWidth(), getHeight());

        repaint(); // Repaint panel
    }
}
