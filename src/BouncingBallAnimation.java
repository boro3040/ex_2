/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.Sleeper;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * This class take command line argument from user and make a screen with
 * bouncing ball that don't pass screen limits with the specific user
 * variables requests.
 */
public class BouncingBallAnimation {

    /**
     * draw animation of ball movement that don't pass screen limits.
     * @param start the first center of the ball.
     * @param dx the x direction velocity vector.
     * @param dy the y direction velocity vector.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", (int) Ball.getWidth(),
                            (int) Ball.getHeight());
        Sleeper sleeper = new Sleeper();
        int ballRadius = 30;
        Ball ball = new Ball(start.getX(), start.getY(), ballRadius,
                            java.awt.Color.BLACK);
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
     * @param args command line arguments, first is x, second is y, third is
     * dx and fourth is dy.
     */
    public static void main(String[] args) {
        // set screen height and width.
        Ball.setWidthHeight(200, 200);

        int startX = Integer.parseInt(args[0]);
        int startY = Integer.parseInt(args[1]);
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);
        drawAnimation(new Point(startX, startY), dx, dy);
    }
}
