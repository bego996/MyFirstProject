package snakeProjectTest;

import java.awt.*;

public class testt {
    public static void main(String[] args) {
        System.out.println("\uF000");
        Appel test = new Appel();
        System.out.println(test.appelLocationInConfliftWithSnake(new Point[]{new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)}));
        System.out.println(test.getAppelSymbol());
    }
}
