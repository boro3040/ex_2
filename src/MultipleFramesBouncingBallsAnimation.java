/*
Barak Davidovitch
211604350
OOP ex2
 */

import biuoop.Sleeper;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 * This class input a draw of multiple bouncing balls animation with the
 * user command argument input that affect the balls count and sizes.
 */
public final class MultipleFramesBouncingBallsAnimation {

    private final Ball[] ballsArray;
    /*
    The biggest size of the ball, I choose size that will not do problems.
    from there the speed is identical and smaller the sizes to this size
    */
    private final int biggestSize = 20;
    // The 2 rectangles they define us in mission.
    private final Rectangle rectangle1 = new Rectangle(50, 50, 450, 450);
    private final Rectangle rectangle2 = new Rectangle(450, 450, 150, 150);

    /**
     * The constructor that takes the user command line arguments and initialize
     * every ball in the array of balls we created with size, random center and
     * random color.
     * @param args THe user strings that representing each ball size.
     */
    private MultipleFramesBouncingBallsAnimation(String[] args) {
        int ballsNum = args.length;
        this.ballsArray = new Ball[ballsNum];
        Random rand = new Random();

        for (int i = 0; i < ballsNum; i++) {
            int radius = Integer.parseInt(args[i]);
            // can't handle bigger balls.
            radius = Math.min(radius, this.biggestSize);
            // generate random color.
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color randomColor = new Color(r, g, b);
            Ball tempBall;
            // half of the balls inside rectangle1 and outside rectangle2.
            if (i <= (int) (ballsNum / 2)) {
                do {
                    // generate random center location on the screen.
                    int x = rand.nextInt((int) Ball.getWidth()) + 1;
                    int y = rand.nextInt((int) Ball.getHeight()) + 1;
                    tempBall = new Ball(x, y, radius, randomColor);
                } while (!(tempBall.isBallInsideRectangle(this.rectangle1)
                        && tempBall.isBallOutsideRectangle(this.rectangle2)));
                this.ballsArray[i] = tempBall;
                this.ballsArray[i].addInsideRectangle(this.rectangle1);
                this.ballsArray[i].addOutsideRectangle(this.rectangle2);
            // the second half outside rectangle1 and outside rectangle2.
            } else {
                do {
                    // generate random center location on the screen.
                    int x = rand.nextInt((int) Ball.getWidth()) + 1;
                    int y = rand.nextInt((int) Ball.getHeight()) + 1;
                    tempBall = new Ball(x, y, radius, randomColor);
                } while (!(tempBall.isBallOutsideRectangle(this.rectangle1)
                        && tempBall.isBallOutsideRectangle(this.rectangle2)));
                this.ballsArray[i] = tempBall;
                this.ballsArray[i].addOutsideRectangle(this.rectangle1);
                this.ballsArray[i].addOutsideRectangle(this.rectangle2);
            }
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
            int speed;
            if (tempBall.getSize() < this.biggestSize) {
                speed = this.biggestSize / tempBall.getSize();
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
            // Draw the yellow and the grey rectangles.
            d.setColor(Color.GRAY);
            d.fillRectangle((int) this.rectangle1.getMinX(),
                            (int) this.rectangle1.getMinY(),
                            (int) this.rectangle1.getWidth(),
                            (int) this.rectangle1.getHeight());
            d.setColor(Color.YELLOW);
            d.fillRectangle((int) this.rectangle2.getMinX(),
                            (int) this.rectangle2.getMinY(),
                            (int) this.rectangle2.getWidth(),
                            (int) this.rectangle2.getHeight());
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
        Ball.setWidthHeight(800, 600);
        MultipleFramesBouncingBallsAnimation bouncingBalls
                = new MultipleFramesBouncingBallsAnimation(args);
        bouncingBalls.initiateSpeed();
        bouncingBalls.drawAnimation();
    }
}
