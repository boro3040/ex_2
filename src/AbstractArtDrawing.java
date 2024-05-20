/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * This class draw 10 random lines in the GUI draw, and then draw also the
 * intersections of all lines, middle point of each line, and draw all the
 * triangles they create.
 */
public class AbstractArtDrawing {

    private final int width;
    private final int height;
    private final int linesNum;
    private int currentNumOfLines = 0;
    private final Line[] linesArray;

    /**
     * The constructor of the draw.
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param linesNum The number of lines to draw.
     */
    public AbstractArtDrawing(int width, int height, int linesNum) {
        this.width = width;
        this.height = height;
        this.linesNum = linesNum;
        this.linesArray = new Line[this.linesNum];
    }

    /**
     * Draw all the lines and points we need.
     */
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

    /**
     * Make new line randomly inside the screen size.
     * @return the line we made.
     */
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

    /**
     * draw a line in the GUI with specific color.
     * @param l the Line object we want to draw.
     * @param d the DrawSurface we want to draw on.
     * @param c the color of wanted line.
     */
    public void drawLine(Line l, DrawSurface d, Color c) {
        d.setColor(c);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                    (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * Draw the middle point of a line like circle.
     * @param l the line we want to draw his middle point.
     * @param d the DrawSurface we want to draw on.
     */
    public void drawMiddlePoint(Line l, DrawSurface d) {
        Point middlePoint = l.middle();
        drawPoint(middlePoint, d, Color.BLUE);
    }

    /**
     * Draw point like circle with specific radius.
     * @param p The Point
     * @param d the DrawSurface we want to draw on.
     * @param c The color we want to give.
     * @return true if we seceded draw point, false if point doesn't exist (null)
     */
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

    /**
     * Draw all intersection points of all lines we have already drawn and all
     * triangles they create.
     * @param d the DrawSurface we want to draw on.
     */
    public void drawIntersectionsAndTriangles(DrawSurface d) {
        // running on all threes of lines in draw
        for (int i = 0; i < this.currentNumOfLines; i++) {
            for (int j = 0; j < this.currentNumOfLines; j++) {
                for (int k = 0; k < this.currentNumOfLines; k++) {
                    // takes all three lines only once
                    if ((i >= j) || (j >= k)) {
                        continue;
                    }
                    // draw intersection points of of each pair out of the three
                    Point point1 = linesArray[i].intersectionWith(linesArray[j]);
                    boolean triangleFlag = drawPoint(point1, d, Color.RED);
                    Point point2 = linesArray[j].intersectionWith(linesArray[k]);
                    triangleFlag &= drawPoint(point2, d, Color.RED);
                    Point point3 = linesArray[i].intersectionWith(linesArray[k]);
                    triangleFlag &= drawPoint(point3, d, Color.RED);
                    /*
                    draw triangle only if all 3 points of
                    intersection are different
                    */
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

    /**
     * The main method make the new draw object and draw all lines, points
     * and triangles.
     * @param args command line arguments - didn't use this time
     */
    public static void main(String[] args) {
        // we asked for drawing in 400X300 canvas 10 lines.
        AbstractArtDrawing draw = new AbstractArtDrawing(400, 300, 10);
        draw.drawAll();
    }
}
