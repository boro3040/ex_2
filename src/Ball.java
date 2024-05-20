/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.Sleeper;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Class representing balls.
 * balls have size, color, and center location.
 * they know how to draw themselves.
 */
public class Ball {

    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;

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
     * Get the current velocity of the ball.
     * @return the velocity on Velocity object type.
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * Move the ball center one step with the velocity of him.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Example of draw animation with the ball.
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * Main method to check the class.
     * @param args command line arguments - not in use.
     */
    public static void main(String[] args) {
        drawAnimation(new Point(100, 100), 1, -1);
    }

}
