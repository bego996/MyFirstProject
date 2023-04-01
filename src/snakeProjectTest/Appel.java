package snakeProjectTest;

import java.awt.*;
import java.util.Random;

public class Appel{
    private final String appelSymbol = "\uF000";
    private int appelCounter = 1;
    private Point appelLocation;

    Appel(){
        Random r = new Random();
        this.appelLocation = new Point(r.nextInt(0,5),r.nextInt(0,5));
    }










    public boolean appelLocationInConfliftWithSnake(Point[] tryLocation){
        for (int i = 0, j = 0; i < tryLocation.length ; i++) {
            if (!tryLocation[i].equals(this.appelLocation) && j < tryLocation.length) {
                j++;
            }
            else if (tryLocation[i].equals(this.appelLocation)) {
                this.appelLocation = new Point(new Appel().appelLocation);
                appelLocationInConfliftWithSnake(tryLocation);
            }
        }
        return false;
    }
    public Point getAppelLocation() {
        return appelLocation;
    }

    public void setAppelLocation(Point appelLocation) {
        this.appelLocation = appelLocation;
    }
    public int getAppelCounter() {
        return appelCounter;
    }

    public void setAppelCounter(int appelCounter) {
        this.appelCounter = appelCounter;
    }

    public String getAppelSymbol() {
        return appelSymbol;
    }
}