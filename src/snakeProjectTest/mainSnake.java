package snakeProjectTest;

import javax.swing.*;

public class mainSnake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawingPanel pane = new DrawingPanel();
        pane.setFocusable(true);
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);

    }
}
