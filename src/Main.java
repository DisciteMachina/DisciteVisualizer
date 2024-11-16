import javax.swing.*;

public class Main {
    public static void main (String[]args) {
        // Frame for the window
        JFrame frame =  new JFrame("Visualizer");
        frame.setSize(500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to handle the balls
        BallPanel ballPanel = new BallPanel();
        frame.add(ballPanel);
        frame.setVisible(true);
    }
}