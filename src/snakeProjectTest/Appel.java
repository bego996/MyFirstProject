package snakeProjectTest;

import java.awt.*;
import java.util.Random;

public class Appel{
    int appelCounter = 1;
    Point appelLocation;

    Appel(){
        Random r = new Random();
        appelLocation = new Point(r.nextInt(0,200),r.nextInt(0,200));
    }

    public void asignNewAppleLocation(Point[] tryLocation, int widht, int height){
        for (int i = 0, j = 0; i < tryLocation.length ; i++) {
            if ((!tryLocation[i].equals(appelLocation) && j < tryLocation.length) && ((appelLocation.x >= 10 && appelLocation.x <= widht-10) && (appelLocation.y >= 10 && appelLocation.y <= height-10) )) {
                j++;
            }
            else{
                appelLocation = new Point(new Appel().appelLocation);
                i = 0;
                j = 0;
            }
        }
    }

    public boolean appelInConflictWitSnakeHead(SnakeMother head){
        return head.getBodyEnd().equals(appelLocation);
    }
    public Point getAppelLocation() {
        return appelLocation;
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