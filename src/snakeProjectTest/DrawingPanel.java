package snakeProjectTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
    SnakeMother head ;
    Appel actualAppelLocation = new Appel();
    int maxAppels = 10;
    private boolean stopSnake = false;
    private boolean kolisionDetect = false;
    private boolean movesDown = false;
    private boolean movesUp = false;
    private boolean movesRight = false;
    private boolean movesLeft = false;
    private boolean headKolisionDetect = false;
    int[] possibleColissionPartsX;
    int[] possibleCollisionPartsY;
    private final int[] startEndWidth = new int[2];
    private final int[] startEndHeight = new int[2];
    private Point[] parts;

    DrawingPanel() {
        setBackground(Color.green);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setPreferredSize(new Dimension(400,400));

    }

    @Override
    protected void paintComponent(Graphics g) {

        startEndWidth[0] = 0;startEndWidth[1] = getWidth();
        startEndHeight[0]= 0;startEndHeight[1]= getHeight();
        int width = getWidth();
        int height = getHeight();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2));
        g2d.setFont(new Font("",Font.PLAIN,10));

        if (parts == null) {
            head = new SnakeMother(new Point(width / 2, height / 2),new Point(width / 2 + 1, height / 2));
            parts = SnakeMother.compileSnakeParts(head, 30, 1);
        }
        possibleColissionPartsX = SnakeMother.convertSnakePartsToPossibleCollisionPartsX(parts);
        possibleCollisionPartsY = SnakeMother.convertSnakePartsToPossibleCollisionPartsY(parts);



        if ((!movesUp && !movesLeft && !movesRight && !movesDown) || !stopSnake || !actualAppelLocation.appelLocationInConfliftWithSnake(parts,width,height) ) {
            for (int i = 0; i < parts.length - 1; i++) {
                g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
            }
            g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
        }


        if (stopSnake && !HilfsMethoden.isHeadConflict(head,startEndHeight,startEndWidth) &&  !kolisionDetect && !HilfsMethoden.isHeadConflictXY(head,possibleColissionPartsX,possibleCollisionPartsY)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (movesRight) {
                head.setBodyStart(new Point(head.getBodyStart().x + 1, head.getBodyStart().y));
                head.setBodyEnd(new Point(head.getBodyEnd().x + 1, head.getBodyEnd().y));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++)
                    g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesDown) {
                head.setBodyStart(new Point(head.getBodyStart().x, head.getBodyStart().y+1));
                head.setBodyEnd(new Point(head.getBodyEnd().x, head.getBodyEnd().y+1));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++)
                    g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesLeft) {
                head.setBodyStart(new Point(head.getBodyStart().x - 1, head.getBodyStart().y));
                head.setBodyEnd(new Point(head.getBodyEnd().x -1, head.getBodyEnd().y));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++)
                    g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            } else if (movesUp) {
                head.setBodyStart(new Point(head.getBodyStart().x, head.getBodyStart().y - 1));
                head.setBodyEnd(new Point(head.getBodyEnd().x, head.getBodyEnd().y - 1));
                parts = SnakeMother.compileSnakeParts(head, parts);
                for (int i = 0; i < parts.length - 1; i++) {
                    g.drawLine(parts[i].x, parts[i].y, parts[i + 1].x, parts[i + 1].y);
                }
                g2d.drawString(actualAppelLocation.getAppelSymbol(),actualAppelLocation.getAppelLocation().x,actualAppelLocation.getAppelLocation().y);
                repaint();
            }
        }else if (HilfsMethoden.isHeadConflict(head,startEndHeight,startEndWidth) || HilfsMethoden.isHeadConflictXY(head,possibleColissionPartsX,possibleCollisionPartsY) ){
            JOptionPane.showMessageDialog(null, "Grenze erreicht!");
            g.setColor(Color.red);
            g2d.fillRect(0,0,width,height);
            g.setColor(Color.green);
            String gr = "Verloren :(";
            g2d.setFont(new Font("",Font.PLAIN,50));
            g2d.drawString(gr,width/4,height/2);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
