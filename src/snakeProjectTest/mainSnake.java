package snakeProjectTest;

import javax.swing.*;

public class mainSnake {
    public mainSnake(){
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        DrawingPanel pane = new DrawingPanel();
        pane.setFocusable(true);
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
       SwingUtilities.invokeLater(mainSnake::new);
    }
}
