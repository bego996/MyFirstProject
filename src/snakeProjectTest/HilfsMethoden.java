package snakeProjectTest;

import java.awt.*;


public class HilfsMethoden {
    public static Point[] schlangenStueckeBerechnenBasic(int stueckLaenge, Point kopfAnfang, Point kopfEnde, boolean firstRun, Point... stueckeAnfangEnde) {
        Point[] stueckBuffer = new Point[(firstRun ? 2 : 0) + (stueckeAnfangEnde != null ? stueckeAnfangEnde.length : 0) * 2];
        stueckBuffer[0] = kopfAnfang;
        stueckBuffer[1] = kopfEnde;
        if (firstRun) {
            for (int i = 2; i < stueckBuffer.length; i++) {
                stueckBuffer[i] = new Point(0, 0);
            }
            for (int i = 0, j = 1, k = 2, l = 3; l < stueckBuffer.length; i++, j++, k++, l++) {
                stueckBuffer[k].x = stueckBuffer[i].x - stueckLaenge;
                stueckBuffer[k].y = stueckBuffer[i].y;
                stueckBuffer[l].x = stueckBuffer[j].x - stueckLaenge;
                stueckBuffer[l].y = stueckBuffer[j].y;
            }
        } else {
            for (int i = 2; i < stueckBuffer.length; i++) {
                stueckBuffer[i] = new Point(0, 0);
            }
            for (int i = 0, j = 1, k = 2, l = 3; l < stueckBuffer.length; i++, j++, k++, l++) {
                if (kopfAnfang.x < kopfEnde.x) {
                    stueckBuffer[k].x = stueckBuffer[i].x + stueckLaenge;
                    stueckBuffer[k].y = stueckBuffer[i].y;
                    stueckBuffer[l].x = stueckBuffer[j].x + stueckLaenge;
                    stueckBuffer[l].y = stueckBuffer[j].y;
                } else if (kopfAnfang.x > kopfEnde.x) {
                    stueckBuffer[k].x = stueckBuffer[i].x + stueckLaenge;
                    stueckBuffer[k].y = stueckBuffer[i].y;
                    stueckBuffer[l].x = stueckBuffer[j].x + stueckLaenge;
                    stueckBuffer[l].y = stueckBuffer[j].y;
                } else if (kopfEnde.y > kopfAnfang.y) {
                    stueckBuffer[k].x = stueckBuffer[i].x;
                    stueckBuffer[k].y = stueckBuffer[i].y + stueckLaenge;
                    stueckBuffer[l].x = stueckBuffer[j].x;
                    stueckBuffer[l].y = stueckBuffer[j].y + stueckLaenge;
                } else if (kopfEnde.y < kopfAnfang.y) {
                    stueckBuffer[k].x = stueckBuffer[i].x;
                    stueckBuffer[k].y = stueckBuffer[i].y - stueckLaenge;
                    stueckBuffer[l].x = stueckBuffer[j].x;
                    stueckBuffer[l].y = stueckBuffer[j].y - stueckLaenge;
                }
            }
        }
        return stueckBuffer;
    }

    public static Point[] moveArrayPositionOnce(Point[] pointsToMove) {
        for (int i = pointsToMove.length - 1; i > 1; i -= 2) {
            pointsToMove[i] = pointsToMove[i - 2];
            pointsToMove[i - 1] = pointsToMove[i - 3];
        }
        return pointsToMove;
    }

    public static boolean isHeadConflict(SnakeMother xyHead, int[] northSouthBoarder, int[] westEastBoarder) {
        int xHead = xyHead.getBodyEnd().x;
        int yHead = xyHead.getBodyEnd().y;
        if (xHead <= westEastBoarder[0] || xHead >= westEastBoarder[1]) {
            return true;
        } else return yHead <= northSouthBoarder[0] || yHead >= northSouthBoarder[1];
    }

    public static boolean isHeadConflictXY(SnakeMother xyHead, int[] possibleCollisionPartsX, int[] posibbleCollisionPartsY) {
        int xHead = xyHead.getBodyEnd().x;
        int yHead = xyHead.getBodyEnd().y;
        for (int i = 1; i < posibbleCollisionPartsY.length; i++) {
            if (xHead == possibleCollisionPartsX[i] && yHead == posibbleCollisionPartsY[i])
                return true;
        }
        return false;
    }

}
