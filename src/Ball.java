import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Ball extends JPanel implements ActionListener {
    Random random = new Random();
    int diameter = 50;

    // Initial position for ball is randomized within frame
    int x = random.nextInt(500 - diameter);
    int y = random.nextInt(500 - diameter);

    /*
    TODO: add slider so that the speed can be adjusted in frame
     */
    int dx = 7; // Horizontal speed x
    int dy = 15; // Horizontal speed y

    // Timer to update the ball's position
    Timer timer;

    public Ball() {
        // Initialize the timer
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*
        TODO: some way to change the color of the ball in frame (maybe)
         */
        g.setColor(Color.WHITE);
        g.fillOval (x, y, diameter, diameter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx; // Update x
        y += dy; // Update y

        // Checks if the ball has reached the edge of the frame
        if (x < 0 || x > getWidth() - diameter) {
            dx = -dx; // If so, reverses either the x or y
        }
        if (y < 0 || y > getHeight() - diameter) {
            dy = -dy;
        }
        repaint(); // Repaint the panel to update the ball's position
    }

}
