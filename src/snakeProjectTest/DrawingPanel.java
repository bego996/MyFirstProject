package snakeProjectTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class DrawingPanel extends JPanel implements KeyListener{
    SnakeMother head ;
    Appel actualAppelLocation = new Appel();
    Appel secondAppelLocation = new Appel();
    private int startBodyParts = 200;
    private int followingBodyPartsAfterApple;
    private boolean stopSnake = false;
    private boolean kolisionDetect = false;
    private boolean movesDown = false;
    private boolean movesUp = false;
    private boolean movesRight = false;
    private boolean movesLeft = false;
    private boolean headKolisionDetect = false;
    boolean appleConsumed = false;
    int maxAppels = 5;
    int[] possibleColissionPartsX;
    int[] possibleCollisionPartsY;
    private final int[] startEndWidth = new int[2];
    private final int[] startEndHeight = new int[2];
    private Point[] parts;
    Point[] partsAfterAppleComsome;
    List<Point> bothstacked = new ArrayList<>();

    DrawingPanel() {
        setBackground(Color.green);
        addKeyListener(this);
        setPreferredSize(new Dimension(500,500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        startEndWidth[0]= 0;
        startEndWidth[1]= getWidth();
        startEndHeight[0]= 0;
        startEndHeight[1]= getHeight();
        int width = getWidth();
        int height = getHeight();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.red);
        g2d.setStroke(new BasicStroke(1));
        g2d.setFont(new Font("",Font.PLAIN,10));

        if (parts == null) {
            head = new SnakeMother(new Point(width / 2, height / 2),new Point(width / 2 + 1, height / 2));
            parts = SnakeMother.compileSnakeParts(head, startBodyParts, 1);
            actualAppelLocation.asignNewAppleLocation(parts,width,height);
        } else if (appleConsumed) {
            partsAfterAppleComsome = SnakeMother.compileSnakeParts(new SnakeMother(parts[parts.length-2],parts[parts.length-1]), followingBodyPartsAfterApple, 1);
            bothstacked.addAll(List.of(parts));
            bothstacked.addAll(List.of(partsAfterAppleComsome));
            parts = bothstacked.toArray(new Point[0]);
            appleConsumed = false;
            bothstacked = new ArrayList<>();
        }
        possibleColissionPartsX = SnakeMother.convertSnakePartsToPossibleCollisionPartsX(parts);
        possibleCollisionPartsY = SnakeMother.convertSnakePartsToPossibleCollisionPartsY(parts);



        if (((!movesUp && !movesLeft && !movesRight && !movesDown) || !stopSnake )) {
            for (int i = 0; i < parts.length - 1; i++) {
                g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
            }
            g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
        }


        if (stopSnake && !HilfsMethoden.isHeadConflict(head,startEndHeight,startEndWidth) &&  !kolisionDetect && !HilfsMethoden.isHeadConflictXY(head,possibleColissionPartsX,possibleCollisionPartsY) && !actualAppelLocation.appelInConflictWitSnakeHead(head) && !secondAppelLocation.appelInConflictWitSnakeHead(head)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (movesRight) {
                head.setBodyStart(new Point(head.getBodyStart().x + 1, head.getBodyStart().y));
                head.setBodyEnd(new Point(head.getBodyEnd().x + 1, head.getBodyEnd().y));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++) {
                    g2d.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                }
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesDown) {
                head.setBodyStart(new Point(head.getBodyStart().x, head.getBodyStart().y+1));
                head.setBodyEnd(new Point(head.getBodyEnd().x, head.getBodyEnd().y+1));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++) {
                    g2d.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                }
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesLeft) {
                head.setBodyStart(new Point(head.getBodyStart().x - 1, head.getBodyStart().y));
                head.setBodyEnd(new Point(head.getBodyEnd().x -1, head.getBodyEnd().y));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++) {
                    g2d.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                }
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesUp) {
                head.setBodyStart(new Point(head.getBodyStart().x, head.getBodyStart().y - 1));
                head.setBodyEnd(new Point(head.getBodyEnd().x, head.getBodyEnd().y - 1));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++) {
                    g2d.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                }
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            }
        }
        else{
            if ((actualAppelLocation.appelInConflictWitSnakeHead(head) || secondAppelLocation.appelInConflictWitSnakeHead(head)) && actualAppelLocation.getAppelCounter() < maxAppels && !appleConsumed  ) {
                actualAppelLocation.appelLocation = new Appel().appelLocation;
                actualAppelLocation.setAppelCounter(actualAppelLocation.getAppelCounter() + 1);
                followingBodyPartsAfterApple += 50;
                appleConsumed = !appleConsumed;
                repaint();
            } else if (actualAppelLocation.getAppelCounter() == maxAppels) {
                g.setColor(Color.red);
                g2d.fillRect(0,0,width,height);
                g.setColor(Color.green);
                String gr = "Gratulation Gewonnen!:) Drücke R für Neustart";
                g2d.setFont(new Font("",Font.PLAIN,20));
                g2d.drawString(gr,width/2,height/2);
                actualAppelLocation.setAppelCounter(0);
                startBodyParts = 200;
                kolisionDetect = !kolisionDetect;
                headKolisionDetect = !headKolisionDetect;
                stopSnake = !stopSnake;
                movesDown = false;
                movesRight = false;
                movesLeft = false;
                movesUp = false;
                parts = null;
            }else if (HilfsMethoden.isHeadConflict(head,startEndHeight,startEndWidth) || HilfsMethoden.isHeadConflictXY(head,possibleColissionPartsX,possibleCollisionPartsY)){
                g.setColor(Color.red);
                g2d.fillRect(0,0,width,height);
                g.setColor(Color.green);
                String gr = "Verloren :( Drücke R für Neustart";
                g2d.setFont(new Font("",Font.PLAIN,10));
                g2d.drawString(gr,width/2,height/2);
                actualAppelLocation.setAppelCounter(1);
                startBodyParts = 200;
                kolisionDetect = !kolisionDetect;
                headKolisionDetect = !headKolisionDetect;
                stopSnake = !stopSnake;
                movesDown = false;
                movesRight = false;
                movesLeft = false;
                movesUp = false;
                parts = null;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == VK_RIGHT && !movesRight && !movesLeft && stopSnake){
                movesRight = true;
                movesUp = false;
                movesDown = false;
            } else if (e.getKeyCode() == VK_DOWN && !movesDown && !movesUp && stopSnake) {
                movesDown = true;
                movesRight = false;
                movesLeft = false;
            } else if (e.getKeyCode() == VK_LEFT && !movesLeft && !movesRight && stopSnake) {
                movesLeft = true;
                movesUp = false;
                movesDown = false;
            } else if (e.getKeyCode() == VK_UP && !movesUp && !movesDown && stopSnake) {
                movesLeft = false;
                movesUp = true;
                movesRight = false;
            } else if (e.getKeyChar() == 115 && !kolisionDetect && !headKolisionDetect) {
                stopSnake = !stopSnake;
            } else if ((e.getKeyChar() == 114 && kolisionDetect) || (e.getKeyChar() == 114 && headKolisionDetect)  ) {
                kolisionDetect = false;
                headKolisionDetect = false;
            }
            repaint();
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
