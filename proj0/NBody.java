public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int i = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int i = in.readInt();
		double radius = in.readDouble();
		Planet[] bodies = new Planet[i];
		for(int j = 0; j < i; j++){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			bodies[j] = new Planet(xPos, yPos, xVel, yVel, mass, img);
		}
		return bodies;
	}

	public static void main(String[] args){
		/**  piece one */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] bodies = readPlanets(filename);

		/**  piece two */
		StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**  piece three */
        for(int i = 0; i < bodies.length; i++){
        	bodies[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double time = 0.0; time <= T; time = time + dt){
        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];
        
        for(int i = 0; i < bodies.length; i++){
        	xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        	yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
        }

        for(int i = 0; i< bodies.length; i++){
        	bodies[i].update(dt, xForces[i], yForces[i]);
        }
         for(int i = 0; i < bodies.length; i++){
        	bodies[i].draw();
        }

        StdDraw.show();
        StdDraw.pause(10);

    	}
    	
    	StdOut.printf("%d\n", bodies.length);
    	StdOut.printf("%.2e\n", radius);
    	for(int i = 0; i < bodies.length; i++){
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos,
    			bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass,
    			bodies[i].imgFileName);
    	}
	}
}
