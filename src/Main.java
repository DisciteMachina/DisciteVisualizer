import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Main {
    public static void main (String[]args){
        // JFrame for the window
        JFrame frame = new JFrame("Ball Moving around");
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        Ball ballPanel = new Ball();
        ballPanel.setBackground(Color.DARK_GRAY);

        // JPanel for the components
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Slider for changing the dx value
        JSlider sliderX = new JSlider(0, 20, 3);
        sliderX.setMajorTickSpacing(4);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);

        // Create a label to display the value of the dx
        JLabel labelX = new JLabel("Speed (Horizontal): " + sliderX.getValue());

        sliderX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelX.setText("Speed (Horizontal): " + sliderX.getValue());
                ballPanel.setSpeedX(sliderX.getValue());
            }
        });
        controlPanel.add(sliderX);
        controlPanel.add(labelX);

        // Slider for changing the dy value
        JSlider sliderY = new JSlider(0, 20, 3);
        sliderY.setMajorTickSpacing(4);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);

        // Create a label to display the value of the dy
        JLabel labelY = new JLabel("Speed (Vertical): " + sliderY.getValue());

        sliderY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelY.setText("Speed (Vertical): " + sliderY.getValue());
                ballPanel.setSpeedY(sliderY.getValue());
            }
        });
        controlPanel.add(sliderY);
        controlPanel.add(labelY);

        frame.add(ballPanel, BorderLayout.CENTER);  // Add Ball controlPanel in the center

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}