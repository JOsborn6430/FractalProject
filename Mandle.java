import org.apache.commons.numbers.complex.Complex;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mandle {
    public static void main(String[] args) {

//        Complex Cj = Complex.ofCartesian(.3,.5);
//
//        DrawingPanel panel = new DrawingPanel(500,500);
//        double scale = 0.002;
//        for (int i = 0; i < 500; i++) {
//            for (int j = 0; j < 500; j++) {
//                Complex c = Complex.ofCartesian(i*scale,j*scale);
//                if (MandelSetCheck(MandelFuncItt(c,Cj,100))) {
//                    panel.setPixel(i,j,Color.BLACK);
//                } else panel.setPixel(i,j,Color.BLUE);
//            }
//        }
//
//
//
//        Complex c1 = Complex.ofCartesian(.4,.17);
//        System.out.println((c1.pow(2)).add(c1));
//        System.out.println(MandelFuncItt(c1,c1,3));
//        System.out.println(MandelSetCheck(MandelFuncItt(c1,c1,50)));

//        DrawingPanel panel = new DrawingPanel(501,501);
//        double[] cen1 = {0,0};
//        Plot p1 = new Plot(panel,cen1,0.01);
//        p1.mandlebrotSet();
        mandelbrotSetExplorer();


    }

    public static void mandelbrotSetExplorer() {
        // Setup
        DrawingPanel panel = new DrawingPanel(501,501);
        double[] cen1 = {0,0};
        Plot p1 = new Plot(panel,cen1,0.01);
        p1.mandlebrotSet();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("commands: zoom, home, close");
        while (run && scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("zoom")) {
                System.out.println("Enter panel coords and scale factor: x y s");
                try {
                    double cx = scanner.nextDouble();
                    double cy = scanner.nextDouble();
                    double s = scanner.nextDouble();

                    cx = cx * p1.scale + (p1.center[0] - p1.scale*(p1.w/2));
                    cy = cy * p1.scale + (p1.center[1] - p1.scale*(p1.h/2));


                    p1.setCenter(cx,cy);
                    p1.mandlebrotSet();
                    p1.scale = s;
                    p1.mandlebrotSet();


                } catch (InputMismatchException IME) {
                    System.out.println("Invalid Input");
                    continue;
                }
            } else if (input.equals("home")) {
                p1.center[0] = 0;
                p1.center[1] = 0;
                p1.mandlebrotSet();
                p1.scale = 0.01;
                p1.mandlebrotSet();
                
            } else if (input.equals("close")) {
                run = false;
            } else {
                System.out.println("commands: zoom, home, close");
            }

        }

    }




    public static Complex MandelFuncItt(Complex z, Complex c, int max) {
        Complex next = MF(z,c);
        if (max > 1) {
            return MandelFuncItt(next,c,max-1);
        }
        return next;
    }
    public static boolean MandelSetCheck(Complex z) {
        if (z.abs() < 2) return true;
        else return false;
    }

    public static Complex MF(Complex z, Complex c) {
        return (z.pow(2)).add(c);
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

}
