/*
Barak Davidovitch
211604350
OOP ex2
 */

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private final double dx;
    private final double dy;

    /**
     * constructor of the 2 axis velocities.
     * @param dx the change in x direction for one step.
     * @param dy the change in y direction for one step.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * get the x component of the velocity.
     * @return dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get the y component of the velocity.
     * @return dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y)
     * and return a new point with position (x+dx, y+dy).
     * @param p the starting Point.
     * @return (x + dx, y + dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
