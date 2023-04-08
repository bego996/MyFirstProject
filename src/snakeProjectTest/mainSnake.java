package snakeProjectTest;

import javax.swing.*;

public class mainSnake {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        DrawingPanel pane = new DrawingPanel();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        pane.setFocusable(true);
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }
}
