package snakeProjectTest;

import java.awt.*;
import java.util.Random;

public class Appel{
    private int appelCounter = 1;
    private Point appelLocation;

    Appel(){
        Random r = new Random();
        this.appelLocation = new Point(r.nextInt(0,400),r.nextInt(0,400));
    }










    public boolean appelLocationInConfliftWithSnake(Point[] tryLocation,int widht,int height){
        for (int i = 0, j = 0; i < tryLocation.length ; i++) {
            if ((!tryLocation[i].equals(appelLocation) && j < tryLocation.length) && ((appelLocation.x >= 30 && appelLocation.x <= widht-30) && (appelLocation.y >= 30 && appelLocation.y <= height-30) )) {
                j++;
            }
            else if (tryLocation[i].equals(appelLocation)) {
                this.appelLocation = new Point(new Appel().appelLocation);
                appelLocationInConfliftWithSnake(tryLocation,widht,height);
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
        return "\uD83C\uDF4E";
    }
}