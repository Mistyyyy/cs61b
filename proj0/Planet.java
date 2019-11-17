public class Planet {
    public double xxPos; //  it's current x position
    public double yyPos; //  it's current y position
    public double xxVel; //  it's current velocity in the x direction
    public double yyVel; //  it's current velocity in the y direction
    public double mass; //   it's mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet
    private double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * the calcDistance method is used to calc the distance between two planet
     * */
    public double calcDistance(Planet p) {
        double absXPos = p.xxPos - xxPos;
        double absYPos = p.yyPos - yyPos;
        return Math.sqrt(absXPos * absXPos + absYPos * absYPos);
    }

    /**
     * the calcForceExertedBy method is used to calc the force exerted on the current planet
     * */
    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        double masProduct = mass * p.mass;
        return (G * masProduct) / (dis * dis);
    }

    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p);
        double xPosDistance = p.xxPos - xxPos;
        double dis = this.calcDistance(p);
        return (force * xPosDistance) / dis;
    }

    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p);
        double yPosDistance = p.yyPos - yyPos;
        double dis = this.calcDistance(p);
        return (force * yPosDistance) / dis;
    }

    public double calcNetForceExertedByX(Planet[] allPlanet) {
        double res = 0.0;
        for (Planet p: allPlanet) {
            if (!this.equals(p)) {
                res += this.calcForceExertedByX(p);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet) {
        double res = 0.0;
        for (Planet p: allPlanet) {
            if (!this.equals(p)) {
                res += this.calcForceExertedByY(p);
            }
        }
        return res;
    }

    public void update(double time, double xForce, double yForce) {
        double xAccelerate = xForce / mass;
        double yAccelerate = yForce / mass;
        xxVel = xxVel + time * xAccelerate;
        yyVel = yyVel + time * yAccelerate;
        xxPos = xxPos + time * xxVel;
        yyPos = yyPos + time * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
