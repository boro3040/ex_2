/*
Barak Davidovitch
211604350
OOP ex2
 */

import java.awt.geom.Line2D;

public class LineTest {
    public static void main(String[] args) {
        int counter = 0;
        int from = -2, to = 5;
        for (int x1 = from; x1 < to; x1++) {
            for (int x2 = from; x2 < to; x2++) {
                for (int x3 = from; x3 < to; x3++) {
                    for (int x4 = from; x4 < to; x4++) {
                        for (int y1 = from; y1 < to; y1++) {
                            for (int y2 = from; y2 < to; y2++) {
                                for (int y3 = from; y3 < to; y3++) {
                                    for (int y4 = from; y4 < to; y4++) {
                                        Line2D.Double l1d = new Line2D.Double(x1, y1, x2, y2);
                                        Line2D.Double l2d = new Line2D.Double(x3, y3, x4, y4);
                                        Line l1 = new Line(x1, y1, x2, y2);
                                        Line l2 = new Line(x3, y3, x4, y4);
                                        if (l1.start().equals(l1.end()) || l2.start().equals(l2.end())) {
                                            continue;
                                        }
                                        if (l1.isIntersecting(l2) != l1d.intersectsLine(l2d)) {
                                            System.out.println("problem" + counter);
                                        }
                                        counter++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("end");
    }
}
