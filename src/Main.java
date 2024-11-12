import javax.swing.*;
import java.awt.*;

public static void main(String[] args) {
    // Initialize swing frame
    JFrame frame = new JFrame("Ball Moving around");
    frame.setSize(500, 500);
    frame.getContentPane().setBackground(Color.DARK_GRAY);

    Ball ballPanel = new Ball();
    ballPanel.setBackground(Color.DARK_GRAY);
    frame.add(ballPanel);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}