import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Frame for the window
        JFrame frame = new JFrame("Visualizer");
        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to handle the balls
        BallPanel ballPanel = new BallPanel();

        // Buttons
        JButton addBall = new JButton("Add Ball");
        addBall.setPreferredSize(new Dimension(150, 40));
        addBall.addActionListener(e -> ballPanel.addBall());

        JButton debugButton = new JButton("Debug");
        debugButton.setPreferredSize(new Dimension(150, 40));
        debugButton.addActionListener(e -> ballPanel.toggleDebugMode());

        JButton reloadButton = new JButton("Reload Scene");
        reloadButton.setPreferredSize(new Dimension(150, 40));
        reloadButton.addActionListener(e -> ballPanel.resetBall());

        // Speed Slider
        JSlider speedSlider = new JSlider(1, 20, 10); // Min speed, Max speed, Default
        speedSlider.setPreferredSize(new Dimension(300, 50));
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.addChangeListener(e -> ballPanel.setSpeed(speedSlider.getValue()));

        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setPreferredSize(new Dimension(50, 40));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sliderPanel.add(speedLabel);
        sliderPanel.add(speedSlider);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reloadButton);
        buttonPanel.add(debugButton);
        buttonPanel.add(addBall);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(ballPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(sliderPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}