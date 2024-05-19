/**
 * This Point class implement point and their methods for doing GUI
 * that connect between lines and points.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * The constructor.
     * @param x the first value of point (x-axis)
     * @param y the second value of point (y-axis)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the method give the distance of this point and the other point.
     * @param other is the other point.
     * @return the distance between them.
     */
    public double distance(Point other) {
        // dist = sqrt((x1-x2)^2+(y1-y2)^2)
        return Math.sqrt((other.getX() - this.x) * (other.getX() - this.x)
                + (other.getY() - this.y) * (other.getY() - this.y));
    }

    /**
     * The method checks if 2 points are the same.
     * Since computers representation of number is finite we used threshold
     * that if 2 points are close enough this is like it's the same point.
     * @param other The other point (not our object).
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Util.isEqual(this.x, other.getX())
                && Util.isEqual(this.y, other.getY());
    }

    /**
     * This method get the x of the point.
     * @return x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method get the y of the point.
     * @return y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * multiply the vector that ends in this Point with scalar.
     * @param scalar The scalar we multiply by.
     * @return scalar * this = (scalar * x, scalar * y)
     */
    public Point scalarMultiply(double scalar) {
        return new Point(scalar * this.getX(), scalar * this.getY());
    }

    /**
     * calculate the addition between this and other vectors.
     * @param other the second vector
     * @return (this + other)
     */
    public Point plus(Point other) {
        return new Point(this.getX() + other.getX(),
                        this.getY() + other.getY());
    }

    /**
     * calculate the difference between this and other vectors.
     * @param other the second vector
     * @return (this - other)
     */
    public Point minus(Point other) {
        return new Point(this.getX() - other.getX(),
                        this.getY() - other.getY());
    }

    /**
     * Scalar product of 2 2-D vectors that ends in Points.
     * @param other The second point
     * @return (this * other)
     */
    public double dot(Point other) {
        return (this.getX() * other.getX() + this.getY() * other.getY());
    }

    /**
     * The cross product of 2 2-D vectors that ends in Points.
     * @param other The second point
     * @return (this X other)
     */
    public double cross(Point other) {
        return (this.getX() * other.getY() - this.getY() * other.getX());
    }
}
