/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class representing balls.
 * balls have size, color, and center location.
 * they know how to draw themselves.
 */
public class Ball {

    /*
    The limits of the screen that the bouncing ball can get.
    I entered default values.
    */
    private static final Rectangle SCREEN = new Rectangle(0, 0, 200, 200);

    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private final ArrayList<Rectangle> insideRectanglesArray;
    private final ArrayList<Rectangle> outsideRectanglesArray;

    /**
     * The constructor of the ball.
     * @param center The center point object Point.
     * @param r The radius of the ball.
     * @param color The color of the ball from Color class.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.insideRectanglesArray = new ArrayList<Rectangle>();
        this.outsideRectanglesArray = new ArrayList<Rectangle>();
    }

    /**
     * Constructor without giving a point.
     * @param x the x value of the center.
     * @param y the y value of the center.
     * @param r The radius of the ball.
     * @param color The color of the ball from Color class.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.insideRectanglesArray = new ArrayList<Rectangle>();
        this.outsideRectanglesArray = new ArrayList<Rectangle>();
    }

    /**
     * Constructor without giving a point, to double.
     * @param x the x value of the center.
     * @param y the y value of the center.
     * @param r The radius of the ball.
     * @param color The color of the ball from Color class.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.insideRectanglesArray = new ArrayList<Rectangle>();
        this.outsideRectanglesArray = new ArrayList<Rectangle>();
    }

    /**
     * Get the x cord of the ball center.
     * @return x of Point center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y cord of the ball center.
     * @return y of Point center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get the radius of ball, the so-called "size".
     * @return the int radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get the color of the ball.
     * @return The color in Color object.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get the width of the screen limits of the ball.
     * @return the width of screen.
     */
    public static double getWidth() {
        return SCREEN.getWidth();
    }

    /**
     * get the height of the screen limits of the ball.
     * @return the height of screen.
     */
    public static double getHeight() {
        return SCREEN.getHeight();
    }

    /**
     * Get the current velocity of the ball.
     * @return the velocity on Velocity object type.
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface The draw surface we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Set the starting velocity of the ball, with Velocity object.
     * @param v the velocity like Velocity object.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * set the starting velocity of the ball with the components themselves.
     * @param dx the x component of the velocity.
     * @param dy the y component of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Move the ball center one step with the velocity of him.
     */
    public void moveOneStep() {
        // check the ball don't pass rectangles
        stayInsideRectangle(SCREEN);
        for (Rectangle r: this.outsideRectanglesArray) {
            stayOutsideRectangle(r);
        }
        for (Rectangle r: this.insideRectanglesArray) {
            stayInsideRectangle(r);
        }
        // one step forward.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * check if the ball is getting inside the rectangle and change the
     * velocity direction if needed.
     * @param rectangle The Rectangle we want to check about.
     */
    public void stayInsideRectangle(Rectangle rectangle) {
        // change velocity if ball pass boundaries in x-axis.
        if ((Util.isBiggerOrEqual(this.velocity.getDx(), 0)
                    && Util.isBiggerOrEqual(this.center.getX() + this.radius,
                                            rectangle.getMaxX()))
                || (Util.isSmallerOrEqual(this.velocity.getDx(), 0)
                    && Util.isSmallerOrEqual(this.center.getX() - this.radius,
                                            rectangle.getMinX()))) {
            this.velocity = new Velocity(-this.velocity.getDx(),
                                        this.velocity.getDy());
        }
        // change velocity if ball pass boundaries in y-axis.
        if ((Util.isBiggerOrEqual(this.velocity.getDy(), 0)
                    && Util.isBiggerOrEqual(this.center.getY() + this.radius,
                                            rectangle.getMaxY()))
                || (Util.isSmallerOrEqual(this.velocity.getDy(), 0)
                    && Util.isSmallerOrEqual(this.center.getY() - this.radius,
                                            rectangle.getMinY()))) {
            this.velocity = new Velocity(this.velocity.getDx(),
                                        -this.velocity.getDy());
        }
    }

    /**
     * check if the ball is getting outside the rectangle and change the
     * velocity direction if needed.
     * @param rectangle The Rectangle we want to check about.
     */
    public void stayOutsideRectangle(Rectangle rectangle) {
        /*
        change velocity if ball pass boundaries in x-axis.
        start checking if ball is in the range of rectangle in Y-axis.
         */
        if (Util.isBiggerOrEqual(this.center.getY() + this.radius,
                                rectangle.getMinY())
                && Util.isSmallerOrEqual(this.center.getY() - this.radius,
                                rectangle.getMaxY())) {
            // check if ball came inside with x-axis velocity.
            if ((Util.isBiggerOrEqual(this.velocity.getDx(), 0)
                    && Util.isBiggerOrEqual(this.center.getX() + this.radius,
                                            rectangle.getMinX()))
                    || (Util.isSmallerOrEqual(this.velocity.getDx(), 0)
                    && Util.isSmallerOrEqual(this.center.getX() - this.radius,
                                            rectangle.getMaxX()))) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        this.velocity.getDy());
            }
        }
        /*
        change velocity if ball pass boundaries in y-axis.
        start checking if ball is in the range of rectangle in x-axis.
         */
        if (Util.isBiggerOrEqual(this.center.getX() + this.radius,
                                rectangle.getMinX())
                && Util.isSmallerOrEqual(this.center.getX() - this.radius,
                                rectangle.getMaxX())) {
            if ((Util.isBiggerOrEqual(this.velocity.getDy(), 0)
                    && Util.isBiggerOrEqual(this.center.getY() + this.radius,
                                            rectangle.getMinY()))
                    || (Util.isSmallerOrEqual(this.velocity.getDy(), 0)
                    && Util.isSmallerOrEqual(this.center.getY() - this.radius,
                                            rectangle.getMaxY()))) {
                this.velocity = new Velocity(this.velocity.getDx(),
                                            -this.velocity.getDy());
            }
        }
    }

    /**
     * This method checks if ball is really in specific rectangle.
     * @param rectangle The rectangle we want to check about.
     * @return true only when all ball is inside rectangle, otherwise false.
     */
    public boolean isBallInsideRectangle(Rectangle rectangle) {
        return Util.isSmaller(this.center.getX() + this.radius,
                            rectangle.getMaxX())
                    && Util.isBigger(this.center.getX() - this.radius,
                            rectangle.getMinX())
                    && Util.isSmaller(this.center.getY() + this.radius,
                            rectangle.getMaxY())
                    && Util.isBigger(this.center.getY() - this.radius,
                            rectangle.getMinY());
    }

    /**
     * This method checks if ball is really out of specific rectangle.
     * @param rectangle The rectangle we want to check about.
     * @return true only when all ball is outside rectangle, otherwise false.
     */
    public boolean isBallOutsideRectangle(Rectangle rectangle) {
        return Util.isBigger(this.center.getX() - this.radius,
                            rectangle.getMaxX())
                    || Util.isSmaller(this.center.getX() + this.radius,
                            rectangle.getMinX())
                    || Util.isBigger(this.center.getY() - this.radius,
                            rectangle.getMaxY())
                    || Util.isSmaller(this.center.getY() + this.radius,
                            rectangle.getMinY());
    }

    /**
     * Set the static parameters of screen size.
     * @param width the width of screen, ball can't pass this.
     * @param height the height of screen, ball can't pass this.
     */
    public static void setWidthHeight(int width, int height) {
        SCREEN.setBounds(0, 0, width, height);
    }

    /**
     * add new rectangle to the end of inside rectangles list.
     * @param rectangle the wanted rectangle.
     */
    public void addInsideRectangle(Rectangle rectangle) {
        this.insideRectanglesArray.add(rectangle);
    }

    /**
     * add new rectangle to the end of outside rectangles list.
     * @param rectangle the wanted rectangle.
     */
    public void addOutsideRectangle(Rectangle rectangle) {
        this.outsideRectanglesArray.add(rectangle);
    }
}
