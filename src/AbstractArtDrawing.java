/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    private final int width;
    private final int height;
    private final int linesNum;
    private int currentNumOfLines = 0;
    private final Line[] linesArray;

    public AbstractArtDrawing(int width, int height, int linesNum) {
        this.width = width;
        this.height = height;
        this.linesNum = linesNum;
        this.linesArray = new Line[this.linesNum];
    }

    public void drawAll() {
        GUI gui = new GUI("Abstract Art Drawing", this.width, this.height);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < this.linesNum; ++i) {
            Line l = generateRandomLine();
            drawLine(l, d, Color.BLACK);
            drawMiddlePoint(l, d);
        }
        drawIntersectionsAndTriangles(d);
        gui.show(d);
    }

    public Line generateRandomLine() {
        Random rand = new Random();
        int x1 =  rand.nextInt(this.width) + 1;
        int y1 =  rand.nextInt(this.height) + 1;
        int x2 =  rand.nextInt(this.width) + 1;
        int y2 =  rand.nextInt(this.height) + 1;
        Line l = new Line(x1, y1, x2, y2);

        // add the line to the lines array that store all of them
        this.linesArray[this.currentNumOfLines] = l;
        this.currentNumOfLines++;
        return l;
    }

    public void drawLine(Line l, DrawSurface d, Color c) {
        d.setColor(c);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                    (int) l.end().getX(), (int) l.end().getY());
    }

    public void drawMiddlePoint(Line l, DrawSurface d) {
        Point middlePoint = l.middle();
        drawPoint(middlePoint, d, Color.BLUE);
    }

    public void drawIntersectionsAndTriangles(DrawSurface d) {
        for (int i = 0; i < this.currentNumOfLines; i++) {
            for (int j = 0; j < this.currentNumOfLines; j++) {
                for (int k = 0; k < this.currentNumOfLines; k++) {
                    // takes all three lines only once
                    if ((i >= j) || (j >= k)) {
                        continue;
                    }
                    Point point1 = linesArray[i].intersectionWith(linesArray[j]);
                    boolean triangleFlag = drawPoint(point1, d, Color.RED);
                    Point point2 = linesArray[j].intersectionWith(linesArray[k]);
                    triangleFlag &= drawPoint(point2, d, Color.RED);
                    Point point3 = linesArray[i].intersectionWith(linesArray[k]);
                    triangleFlag &= drawPoint(point3, d, Color.RED);
                    if (triangleFlag && (!point1.equals(point2))
                        && (!point2.equals(point3))
                        && (!point1.equals(point3))) {
                        drawLine(new Line(point1, point2), d, Color.GREEN);
                        drawLine(new Line(point2, point3), d, Color.GREEN);
                        drawLine(new Line(point1, point3), d, Color.GREEN);
                    }
                }
            }
        }
    }

    public boolean drawPoint(Point p, DrawSurface d, Color c) {
        // when the point entered does not really exist.
        if (p == null) {
            return false;
        }
        // size of point radius in the drawing.
        int pointRadiusSize = 3;
        d.setColor(c);
        d.fillCircle((int) p.getX(),
                (int) p.getY(), pointRadiusSize);
        return true;
    }

    public static void main(String[] args) {
        // we asked for drawing in 400X300 canvas 10 lines.
        AbstractArtDrawing draw = new AbstractArtDrawing(400, 300, 10);
        draw.drawAll();
    }
}
