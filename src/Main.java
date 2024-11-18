import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {
    public static void main (String[]args) {
        // Frame for the window
        JFrame frame =  new JFrame("Visualizer");
        frame.setSize(500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to handle the balls
        BallPanel ballPanel = new BallPanel();

        JButton reloadButton = new JButton("Reload Scene");
        reloadButton.setPreferredSize(new Dimension(150, 40));

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballPanel.resetBall(); // reload the scene
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reloadButton);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4,1));

        JLabel horizontalSpeedBall1 = new JLabel("Horizontal Speed (Ball1): " );
        JLabel verticalSpeedBall1 = new JLabel("Vertical Speed (Ball1): " );
        JLabel horizontalSpeedBall2 = new JLabel("Horizontal Speed (Ball2): " );
        JLabel verticalSpeedBall2 = new JLabel("Vertical Speed (Ball2): " );

        infoPanel.add(horizontalSpeedBall1);
        infoPanel.add(verticalSpeedBall1);
        infoPanel.add(horizontalSpeedBall2);
        infoPanel.add(verticalSpeedBall2);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(ballPanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Timer to update the speed labels
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball1 = ballPanel.getBall1();
                Ball ball2 = ballPanel.getBall2();

                // Update labels with new speed
                horizontalSpeedBall1.setText(String.format("Horizontal Speed (Ball 1): %.2f", ball1.getHorizontalSpeed()));
                verticalSpeedBall1.setText(String.format("Vertical Speed (Ball 1): %.2f", ball1.getVerticalSpeed()));

                horizontalSpeedBall2.setText(String.format("Horizontal Speed (Ball 2): %.2f", ball2.getHorizontalSpeed()));
                verticalSpeedBall2.setText(String.format("Vertical Speed (Ball 2): %.2f", ball2.getVerticalSpeed()));
            }
        });
        timer.start();

        frame.setVisible(true);
    }
}