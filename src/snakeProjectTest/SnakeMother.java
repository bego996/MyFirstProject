package snakeProjectTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeMother {
    private Point bodyStart;
    private Point bodyEnd;

    SnakeMother(Point xy1,Point xy2){
        this.bodyStart = xy1;
        this.bodyEnd = xy2;
    }
     public static Point[] compileSnakeParts(SnakeMother snakeMother, int preferedLength, int preferedPartDecrement){
        Point[] head = new Point[2];
        head[0] = snakeMother.bodyStart;
        head[1] = snakeMother.bodyEnd;
        return HilfsMethoden.schlangenStueckeBerechnenBasic(preferedPartDecrement,head[0],head[1],new Point[preferedLength]);
    }
    public static Point[] compileSnakeParts(SnakeMother snakeMother,Point[] pointToMove){
        Point[] buffer = HilfsMethoden.moveArrayPositionOnce(pointToMove);
        buffer[0] = snakeMother.bodyStart;
        buffer[1] = snakeMother.bodyEnd;
        return buffer;
    }
    public static int[] convertSnakePartsToPossibleCollisionPartsX(Point[] parts){
        int [] buffer = new int[parts.length-2];
        for (int i = 0,j = 2; j < parts.length ; i++,j++) {
            buffer[i] = parts[j].x;
        }
        return buffer;
    }
    public static int[] convertSnakePartsToPossibleCollisionPartsY(Point[] parts){
        int [] buffer = new int[parts.length-2];
        for (int i = 0,j = 2; j < parts.length ; i++,j++) {
            buffer[i] = parts[j].y;
        }
        return buffer;
    }
    public Point getBodyStart() {
        return bodyStart;
    }
    public void setBodyStart(Point bodyStart) {
        this.bodyStart = bodyStart;
    }
    public Point getBodyEnd() {return bodyEnd;}
    public void setBodyEnd(Point bodyEnd) {
        this.bodyEnd = bodyEnd;
    }
}

