import java.awt.*;

import org.apache.commons.numbers.complex.Complex;
import org.apache.commons.numbers.fraction.BigFraction;

public class Fractal {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(500,500);
        Graphics g = panel.getGraphics();

        panel.setAntiAlias(false);
        squareFractal2(250,250,120,6,1,g,panel);
        g.setColor(Color.BLUE);
        squareFractal3(250,250,120,6,1,g,panel);
        g.setColor(Color.ORANGE);
        squareFractal3(450,450,12,3,1,g,panel);



        for (int i = 10; i < 100; i +=2) {
            panel.setPixel(i,100,Color.BLACK);
        }


    }

    public static void MSuperPrecise(ZFrac z, ZFrac c, int max) {
        ZFrac next = (z.sqr()).add(c);
        System.out.println(max);
        if (max > 1) {
            MSuperPrecise(next, c, max-1);
        } else {
            System.out.println(next);
            System.out.println(next.toDouble());
        }
    }

    public static void MandelSetItt(Complex z, Complex c, int max) {
        Complex next = MF(z,c);
        if (max > 1 && next.abs() < 2) {
            MandelSetItt(next,c,max-1);
        } else System.out.println(next);
    }

    public static Complex MF(Complex z, Complex c) {
        return (z.pow(2)).add(c);
    }

    public static void treeFractal(int x, int y, int r, int theta, int steps, Graphics g, DrawingPanel panel, int pause) {
        panel.sleep(pause);
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

    public static void squareFractal(int x, int y, int l, int max, int sleep, Graphics g, DrawingPanel panel) {
        drawSquare(x,y,l,g);
        if (max > 0) {
            max--;
            squareFractal(x, y-45,l/2,max,sleep,g,panel);
            squareFractal(x, y+45,l/2,max,sleep,g,panel);
            squareFractal(x+45, y,l/2,max,sleep,g,panel);
            squareFractal(x-45, y,l/2,max,sleep,g,panel);
        } else System.out.println("Done");

    }

    public static void squareFractal2(int x, int y, int l, int max, int sleep, Graphics g, DrawingPanel panel) {
        drawSquare(x,y,l,g);
        panel.sleep(sleep);
        if (max > 0) {
            max--;
            squareFractal2(x, y-(l/2 + l/4),l/2,max,sleep,g,panel);
            squareFractal2(x, y+(l/2 + l/4),l/2,max,sleep,g,panel);
            squareFractal2(x+(l/2 + l/4), y,l/2,max,sleep,g,panel);
            squareFractal2(x-(l/2 + l/4), y,l/2,max,sleep,g,panel);
        }

    }

    public static void squareFractal3(int x, int y, int l, int max, int sleep, Graphics g, DrawingPanel panel) {
        drawSquare(x,y,l,g);
        panel.sleep(sleep);
        if (max > 0) {
            max--;
            squareFractal3(x, y-((3*l)/4),l/2,max,sleep,g,panel);
            squareFractal3(x, y+((3*l)/4),l/2,max,sleep,g,panel);
            squareFractal3(x+((3*l)/4), y,l/2,max,sleep,g,panel);
            squareFractal3(x-((3*l)/4), y,l/2,max,sleep,g,panel);
        }

    }


    public static void drawSquare(int x, int y, int l, Graphics g) {
        g.fillRect(x - l/2, y - l/2, l, l);
    }



}
