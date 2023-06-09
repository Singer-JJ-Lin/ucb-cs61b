public class Planet {
    public double xxPos;

    public double yyPos;

    public double xxVel;

    public double yyVel;

    public double mass;

    public String imgFileName;

    private static final double G = 6.67e-11;

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

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(xxPos - p.xxPos, 2) + Math.pow(yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        double radius = calcDistance(p);
        return G * mass * p.mass / Math.pow(radius, 2);
    }

    public double calcForceExertedByX(Planet p) {
        double radius = calcDistance(p);
        double dx = p.xxPos - xxPos;
        return calcForceExertedBy(p) * dx / radius;
    }

    public double calcForceExertedByY(Planet p) {
        double radius = calcDistance(p);
        double dy = p.yyPos - yyPos;
        return calcForceExertedBy(p) * dy / radius;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0;
        for (Planet planet : planets) {
            if(this.equals(planet)) {
                continue;
            }
            result += calcForceExertedByX(planet);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0;
        for (Planet planet : planets) {
            if(this.equals(planet)) {
                continue;
            }
            result += calcForceExertedByY(planet);
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        xxVel += fX / mass * dt;
        yyVel += fY / mass * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

    public double getMass() {
        return mass;
    }

    public String getImgFileName() {
        return imgFileName;
    }
}
