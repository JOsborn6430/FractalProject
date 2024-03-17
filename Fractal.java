import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.commons.numbers.fraction.BigFraction;

public class Fractal {

    public static void fractalUI(DrawingPanel panel, Graphics g, Scanner scanner) {
        System.out.println("Welcome to Fractal Generator");
        System.out.println("Warning: max iteration values greater than 20 are not recommended \nAll units are in pixels");
        String fractals = "commands: square, diffraction (diff), tree, clear, back";

        int cx = panel.getWidth()/2;
        int cy = panel.getHeight()/2;

        boolean run = true;
        System.out.println(fractals);
        while (run) {
            String input = scanner.next();
            if (input.equals("diffraction")) input = "diff";
            switch (input) {
                case "square":
                    System.out.println("Enter an integer for length, max iterations, and delay:");
                    try {
                        int length = scanner.nextInt();
                        int max = scanner.nextInt();
                        int delay = scanner.nextInt();
                        scanner.nextLine();

                        squareFractal(cx, cy, length, max, delay, g, panel);
                    } catch (InputMismatchException IME) {
                        System.out.println("Invalid Input");
                        scanner.nextLine();
                    }
                    break;
                case "diff":
                    System.out.println("Enter an integer for length, max iterations, and delay:");
                    try {
                        int length = scanner.nextInt();
                        int max = scanner.nextInt();
                        int delay = scanner.nextInt();
                        scanner.nextLine();

                        diffraction(cx, cy, length, max, delay, g, panel);
                    } catch (InputMismatchException IME) {
                        System.out.println("Invalid Input");
                        scanner.nextLine();
                    }
                    break;
                case "tree":
                    System.out.println("Enter an integer for radius, angle (deg), max iterations, and delay");
                    try {
                        int r = scanner.nextInt();
                        int theta = scanner.nextInt();
                        int max = scanner.nextInt();
                        int delay = scanner.nextInt();
                        scanner.nextLine();

                        treeFractal(cx, panel.getHeight()-10, r, theta, max, g, panel, delay);
                        g.setColor(Color.BLACK);
                    } catch (InputMismatchException IME) {
                        System.out.println("Invalid Input");
                        scanner.nextLine();
                    }
                    break;
                case "clear":
                    panel.clear();
                    scanner.nextLine();
                    break;
                case "back":
                    run = false;
                    scanner.nextLine();
                    break;
                default:
                    scanner.nextLine();
            }
            System.out.println(fractals);

        }
    }



    public static void treeFractal(int x, int y, int r, int theta, int steps, Graphics g, DrawingPanel panel, int pause) {
        if (pause != 0) panel.sleep(pause);
        double thetaRad = degToRad(theta);
        if (steps > 0) {
            drawTree(x,y,theta,r,g);
            int[] endPoint0 = {
                    (int)((r*Math.cos(thetaRad)) + x - r)
                    ,
                    (int)(y - (r*Math.sin(thetaRad)))
            };
            int[] endPoint1 = {
                    (int)(x + r - 1*(r*Math.cos(thetaRad)))
                    ,
                    (int)(y - (r*Math.sin(thetaRad)))
            };
            treeFractal(endPoint0[0],endPoint0[1], r/2, theta, steps-1,g,panel,pause);
            treeFractal(endPoint1[0],endPoint1[1], r/2, theta, steps-1,g,panel,pause);

        }


    }

    public static double degToRad(double deg) {
        return (Math.PI/180.0) * deg;
    }

    public static void drawTree(int x, int y, int arcDeg, int r, Graphics g) {
        g.setColor(Color.RED);
        g.drawArc(x-2*r, y-r, 2*r, 2*r, 0, arcDeg);
        g.setColor(Color.BLUE);
        g.drawArc(x, y-r, 2*r, 2*r, 180-arcDeg, arcDeg);
    }



    public static BigFraction frac(int a, int b) {
        return BigFraction.of(a,b);
    }

    // Originally a failed square fractal.
    public static void diffraction(int x, int y, int l, int max, int sleep, Graphics g, DrawingPanel panel) {
        if (sleep != 0) panel.sleep(sleep);
        drawSquare(x,y,l,g);
        if (max > 0) {
            max--;
            diffraction(x, y-45,l/2,max,sleep,g,panel);
            diffraction(x, y+45,l/2,max,sleep,g,panel);
            diffraction(x+45, y,l/2,max,sleep,g,panel);
            diffraction(x-45, y,l/2,max,sleep,g,panel);
        }

    }


    public static void squareFractal(int x, int y, int l, int max, int sleep, Graphics g, DrawingPanel panel) {
        drawSquare(x,y,l,g);
        if (sleep != 0) panel.sleep(sleep);
        if (max > 0) {
            max--;
            squareFractal(x, y-((3*l)/4),l/2,max,sleep,g,panel);
            squareFractal(x, y+((3*l)/4),l/2,max,sleep,g,panel);
            squareFractal(x+((3*l)/4), y,l/2,max,sleep,g,panel);
            squareFractal(x-((3*l)/4), y,l/2,max,sleep,g,panel);
        }

    }


    public static void drawSquare(int x, int y, int l, Graphics g) {
        g.fillRect(x - l/2, y - l/2, l, l);
    }

}
