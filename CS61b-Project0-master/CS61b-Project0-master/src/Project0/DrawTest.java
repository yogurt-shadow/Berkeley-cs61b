package Project0;

import static Project0.StdDraw.*;

public class DrawTest {
    public static void main(String[] args) {
        StdDraw.setScale(-2, +2);
        StdDraw.enableDoubleBuffering();

        for (double t = 0.0; true; t += 0.02) {
            double x = Math.sin(t);
            double y = Math.cos(t);
            StdDraw.clear();
            StdDraw.filledCircle(x, y, 0.05);
            StdDraw.filledCircle(-x, -y, 0.05);
            StdDraw.show();
            StdDraw.pause(20);
        }
    }

//    for (double t = 0; t <= T; t += dt) {
//        double[] xForces = new double[Planets.length];
//        double[] yForces = new double[Planets.length];
//        /**
//         * Calculate the net forces for every planet
//         */
//        for (int i = 0; i < Planets.length; i++) {
//            xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
//            yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
//        }
//        /**
//         * Update positions and velocities of each planet
//         */
//        for (int i = 0; i < Planets.length; i++) {
//            Planets[i].update(dt, xForces[i], yForces[i]);
//        }
//        /**
//         * Draw the background
//         */
//        StdDraw.picture(0, 0, "images/starfield.jpg");
//        /**
//         * Draw all planets
//         */
//        for (Body planet : Planets) {
//            planet.draw();
//        }
//        StdDraw.show();
//        StdDraw.pause(10);
//
//    }

}
