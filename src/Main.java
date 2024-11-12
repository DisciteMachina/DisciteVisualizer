import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String[]args){
        // JFrame for the window
        JFrame frame = new JFrame("Visualizer");
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Create the ball
        Ball ballPanel = new Ball();
        ballPanel.setBackground(Color.DARK_GRAY);

        // JPanel for the components
        JPanel controlPanel = createControlPanel(ballPanel);

        frame.setLayout(new BorderLayout());
        frame.add(ballPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static JPanel createControlPanel(Ball ballPanel) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        // Create and add sliders and labels
        JSlider sliderX = createSlider("Speed (Horizontal)", 0, 20, 3);
        JSlider sliderY = createSlider("Speed (Vertical)", 0, 20, 3);

        // Labels for sliders
        JLabel labelX = new JLabel("Speed (Horizontal): " + sliderX.getValue());
        JLabel labelY = new JLabel("Speed (Vertical): " + sliderY.getValue());

        // Update Ball's speed when sliders are changed
        sliderX.addChangeListener(e -> {
            labelX.setText("Speed (Horizontal): " + sliderX.getValue());
            ballPanel.setSpeedX(sliderX.getValue());
        });

        sliderY.addChangeListener(e -> {
            labelY.setText("Speed (Vertical): " + sliderY.getValue());
            ballPanel.setSpeedY(sliderY.getValue());
        });

        // Add sliders and labels to the control panel
        controlPanel.add(sliderX);
        controlPanel.add(labelX);
        controlPanel.add(sliderY);
        controlPanel.add(labelY);

        return controlPanel;
    }

    // Helper method for creating the slider
    private static JSlider createSlider(String labelText, int min, int max, int initialValue) {
        JSlider slider = new JSlider(min, max, initialValue);
        slider.setMajorTickSpacing(4);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

}