public class NBody {

    private static String bg = "images/starfield.jpg";

    public static void main(String[] args) {
       double T = Double.parseDouble(args[0]);
       double dt = Double.parseDouble(args[1]);
       String filename = args[2];
       double radius = readRadius(filename);
       Planet[] allPlanet = readPlanets(filename);

       int N = allPlanet.length;
       StdDraw.setScale(0, radius);
       StdDraw.clear();
       StdDraw.picture(0, 0, bg);
       for(Planet p : allPlanet) {
           p.draw();
       }

       StdDraw.enableDoubleBuffering();
       StdDraw.show();
       for(double time = 0; time <= T; time += dt) {
           double[] xForce = new double[N];
           double[] yForce = new double[N];
           for(int j = 0; j < N; j++) {
               xForce[j] = allPlanet[j].calcNetForceExertedByX(allPlanet);
               yForce[j] = allPlanet[j].calcNetForceExertedByY(allPlanet);
           }
           for (int z = 0; z < N; z++) {
               allPlanet[z].update(time, xForce[z], yForce[z]);
           }
           StdDraw.clear();
           StdDraw.picture(0, 0, bg, radius, radius);
           for(Planet p: allPlanet) {
               p.draw();
           }
           StdDraw.show();
           StdDraw.pause(10);
       }

        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanet[i].xxPos, allPlanet[i].yyPos, allPlanet[i].xxVel,
                    allPlanet[i].yyVel, allPlanet[i].mass, allPlanet[i].imgFileName);
        }
    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        double amount = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int planetAmount = in.readInt();
        double radius = in.readDouble();
        Planet[] planetResult = new Planet[planetAmount];
        for (int i = 0; i < planetAmount; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String file = in.readString();
            planetResult[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, file);
        }
        return planetResult;
    }
}
