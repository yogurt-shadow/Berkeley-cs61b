public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xp, double yp, double xV, double yV, double m, String img){
		xxPos = xp;
		yyPos = yp;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	
	public double calcDistance(Planet b){
		double xdis = this.xxPos - b.xxPos;
		double ydis = this.yyPos - b.yyPos;
		double distance = Math.sqrt(xdis * xdis + ydis * ydis);
		return distance;
	}

	public double calcForceExertedBy(Planet b){
		final double G = 6.67e-11;
		double force = G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
		return force;
	}

	public double calcForceExertedByX(Planet b){
		double dx = b.xxPos - this.xxPos;
		double force_x = this.calcForceExertedBy(b) * dx / this.calcDistance(b);
		return force_x;
	}

	public double calcForceExertedByY(Planet b){
		double dy = b.yyPos - this.yyPos;
		double force_y = this.calcForceExertedBy(b) * dy / this.calcDistance(b);
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] b){
		double sum_x = 0;
		for(int i = 0; i < b.length; i++){
			if(this.equals(b[i])){
				continue;
			}
			else{
				sum_x = sum_x + this.calcForceExertedByX(b[i]);
			}
		}
		return sum_x;
	}

	public double calcNetForceExertedByY(Planet[] b){
		double sum_y = 0;
		for(int i = 0; i < b.length; i++){
			if(this.equals(b[i])){
				continue;
			}
			else{
				sum_y = sum_y + this.calcForceExertedByY(b[i]);
			}
		}
		return sum_y;
	}

	public void update(double dt, double fx, double fy){
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}

