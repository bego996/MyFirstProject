package snakeProjectTest;

import java.awt.*;
import java.util.Random;

public class Appel {
    int appelCounter = 1;
    Point appelLocation;

    Appel() {
        Random r = new Random();
        appelLocation = new Point(r.nextInt(0, 500), r.nextInt(0, 500));
    }

    public void asignNewAppleLocation(Point[] tryLocation, int widht, int height) {
        for (int i = 0, j = 0; i < tryLocation.length; i++) {
            if ((!tryLocation[i].equals(appelLocation) && j < tryLocation.length) && ((appelLocation.x >= 50 && appelLocation.x <= widht - 50) && (appelLocation.y >= 50 && appelLocation.y <= height - 50))) {
                j++;
            } else {
                appelLocation = new Point(new Appel().appelLocation);
                i = 0;
                j = 0;
            }
        }
    }

    public boolean appelInConflictWitSnakeHead(SnakeMother head) {
        return (head.getBodyEnd().x >= appelLocation.x - 3 && head.getBodyEnd().x <= appelLocation.x + 7) && (head.getBodyEnd().y >= appelLocation.y - 10 && head.getBodyEnd().y <= appelLocation.y);
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