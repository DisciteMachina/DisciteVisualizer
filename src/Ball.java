import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Ball extends JPanel implements ActionListener {
    Random random = new Random();
    int diameter = 50;
    int x = random.nextInt(500 - diameter);
    int y = random.nextInt(500 - diameter);

    int dx = 7;
    int dy = 15;

    Timer timer;

    public Ball() {
        // Timer to move the ball
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillOval (x, y, diameter, diameter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        if (x < 0 || x > getWidth() - diameter) {
            dx = -dx;
        }
        if (y < 0 || y > getHeight() - diameter) {
            dy = -dy;
        }
        repaint();
    }

}
