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
 * This class input a draw of multiple bouncing balls animation with the
 * user command argument input that affect the balls count and sizes.
 */
public final class MultipleBouncingBallsAnimation {

    private final Ball[] ballsArray;

    /**
     * The constructor that takes the user command line arguments and initialize
     * every ball in the array of balls we created with size, random center and
     * random color.
     * @param args THe user strings that representing each ball size.
     */
    private MultipleBouncingBallsAnimation(String[] args) {
        int ballsNum = args.length;
        this.ballsArray = new Ball[ballsNum];
        Random rand = new Random();

        for (int i = 0; i < ballsNum; i++) {
            int radius = Integer.parseInt(args[i]);
            // generate random center location on the screen.
            int x = rand.nextInt((int) Ball.getWidth()) + 1;
            int y = rand.nextInt((int) Ball.getHeight()) + 1;
            // generate random color.
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color randomColor = new Color(r, g, b);
            this.ballsArray[i] = new Ball(x, y, radius, randomColor);
        }
    }

    /**
     * THis method initiate each ball in balls array speed.
     * the speed is depended on 1 / size of the ball, the angle of the starting
     * speed is random.
     */
    private void initiateSpeed() {
        Random rand = new Random();
        for (Ball tempBall : this.ballsArray) {
            // biggest size, from there the speed is identical.
            int biggestSize = 50;
            int speed;
            if (tempBall.getSize() < biggestSize) {
                speed = biggestSize / tempBall.getSize();
            } else {
                speed = 1;
            }
            int randAngle = rand.nextInt(360);
            tempBall.setVelocity(Velocity.fromAngleAndSpeed(randAngle, speed));
        }
    }

    /**
     * This method is drawing all the balls animation. in each step its moving
     * all balls in the array of balls and draw them.
     */
    private void drawAnimation() {
        GUI gui = new GUI("title", (int) Ball.getWidth(),
                            (int) Ball.getHeight());
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball tempBall : this.ballsArray) {
                tempBall.moveOneStep();
                tempBall.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * This main method is taking the command line arguments that is the sizes
     * of the balls, initialize all of them to the balls array,
     * and give it to the method that draw all balls in moving frame.
     * @param args each String from command line argument is the size of
     * one ball, The number of balls in screen will be args.length.
     */
    public static void main(String[] args) {
        // set screen height and width.
        Ball.setWidthHeight(200, 200);

        MultipleBouncingBallsAnimation bouncingBalls
                = new MultipleBouncingBallsAnimation(args);
        bouncingBalls.initiateSpeed();
        bouncingBalls.drawAnimation();
    }
}
