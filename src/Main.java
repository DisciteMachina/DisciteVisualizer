import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // Frame for the window
        JFrame frame = new JFrame("Visualizer");
        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to handle the balls
        BallPanel ballPanel = new BallPanel();

        JButton addBall = new JButton("Add Ball");
        addBall.setPreferredSize(new Dimension(150, 40));

        addBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballPanel.addBall();
            }
        });

        JButton debugButton = new JButton("Debug");
        debugButton.setPreferredSize(new Dimension(150, 40));

        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballPanel.toggleDebugMode();
            }
        });

        JButton reloadButton = new JButton("Reload Scene");
        reloadButton.setPreferredSize(new Dimension(150, 40));

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballPanel.resetBall(); // Reload the scene
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reloadButton);
        buttonPanel.add(debugButton);
        buttonPanel.add(addBall);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(ballPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}