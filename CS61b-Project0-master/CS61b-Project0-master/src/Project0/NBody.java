package Project0;

/**
 * NBody is a class that will actually run your simulation
 *
 */

public class NBody {
    /**
     * Your first method is readRadius. Given a file name as a String, it should return
     * a double corresponding to the radius of the universe in that file,
     * e.g. readRadius("./data/planets.txt") should return 2.50e+11.
     *
     */
    /**
     *
     * @param filename
     * @return Radius
     */
    public static double readRadius(String filename){
        In in =new In(filename);
        in.readInt();
        double Radius=in.readDouble();
        return Radius;
    }

    /**
     *
     *
     * object return to body
     */

    public static Body[] readBodies(String filename){
        In in=new In(filename);
        int num = in.readInt();
        Body[] body=new Body[num];

        in.readDouble();
        for(int i=0;i<num;i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            body[i] = new Body(xP, yP, xV, yV, m, img);

        }
        return body;
    }

    /**
     * Draw the initial universe state
     */
    public static void main(String[] args) {
        /**
         * Get data --finished
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double uniRadius = NBody.readRadius(fileName);
        Body[] Planets = NBody.readBodies(fileName);

        /**
         * Draw the background
         */
        StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**
         * Draw planets 63 filename给到实例Planets
         */
        for (Body var : Planets) {
            var.draw();
        }

        /**
         * Animation 双缓冲
         */
        StdDraw.enableDoubleBuffering();
        /**
         * Set up a loop to loop until time variable reaches T
         * 最后一个部分了Creating an Animation
         *
         * For each time through the loop, do the following:
         * 1.Create an xForces array and yForces array.
         *
         * 2.Calculate the net x and y forces for each Body, storing these in the xForces and yForces arrays respectively.
         *
         * 3.After calculating the net forces for every Body, call update on each of the Bodys. This will update each body’s position,
         * velocity, and acceleration.
         *
         * 4.Draw the background image.
         *
         * 5.Draw all of the Bodys.
         *
         * 6.Show the offscreen buffer (see the show method of StdDraw).
         *
         * 7.Pause the animation for 10 milliseconds (see the pause method of StdDraw). You may need to tweak this on your computer.
         *
         * 8.Increase your time variable by dt.
         *
         *
         */
        for(double t=0;t<=T;t+=dt){
            //创造xForce和YForce数组
            double[] xForce=new double[Planets.length];
            double[] yForce=new double[Planets.length];
            //计算netForce
            for (int i=0;i<Planets.length;i++){
                xForce[i]=Planets[i].calcNetForceExertedByX(Planets);
                yForce[i]=Planets[i].calcNetForceExertedByY(Planets);
            }
            //更新每个星体 update
            for (int i=0;i<Planets.length;i++){
                Planets[i].update(dt,xForce[i],yForce[i]);
            }
            //绘制背景图
            StdDraw.picture(0,0,"images/starfield.jpg");
            //绘制每个星体的图片
            for (int i=0;i<Planets.length;i++){
                Planets[i].draw();
            }
            //显示屏幕外的缓冲区（双缓冲时动画在屏幕外）
            StdDraw.show();
            //暂停10毫秒
            StdDraw.pause(10);
        }

















        /**
         * Print out the final state of the universe when time reaches T
         */
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);

        }

    }

}


