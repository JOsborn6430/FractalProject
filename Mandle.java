import org.apache.commons.numbers.complex.Complex;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mandle {

    public static void mandelbrotSetExplorer(DrawingPanel panel, Scanner scanner) {
        // Setup
        double[] cen1 = {0,0};
        Plot p1 = new Plot(panel,cen1,0.01);
        p1.mandlebrotSet();
        boolean run = true;
        String validCommands = "commands: zoom, cordZoom, home, julia, mandle, iterations, back";
        System.out.println(validCommands);
        while (run) {
            String input = scanner.next();

            // I wrote this with if else statements because I thought that switch statements could only be used
            // with primitive types. Turns out it that's mostly true except for Strings and a few others.

            if (input.equals("zoom")) {
                System.out.println("Enter panel coords and scale factor: x y s");
                try {
                    double cx = scanner.nextDouble();
                    double cy = scanner.nextDouble();
                    double s = scanner.nextDouble();
                    scanner.nextLine();

                    cx = cx * p1.scale + (p1.center[0] - p1.scale*(p1.w/2));
                    cy = cy * p1.scale + (p1.center[1] - p1.scale*(p1.h/2));


                    p1.setCenter(cx,cy);
//                    p1.mandlebrotSet();
                    p1.scale = s;
                    p1.mandlebrotSet();


                } catch (InputMismatchException IME) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            } else if (input.equals("home")) {
                p1.center[0] = 0;
                p1.center[1] = 0;
                p1.scale = 0.01;
                p1.mandlebrotSet();
                continue;
            } else if (input.equals("julia")) {
                System.out.println("enter complex number: a b");
                try {
                    Complex jc = Complex.ofCartesian(scanner.nextDouble(), scanner.nextDouble());
                    scanner.nextLine();
                    p1.JC = jc;
                    p1.juliaMode = true;
                    p1.mandlebrotSet();
                } catch (InputMismatchException IME) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            } else if (input.equals("cordZoom")) {
                System.out.println("enter coordinates on complex plane and zoom factor: a b s");
                try {
                    double cx = scanner.nextDouble();
                    double cy = -1.0 * scanner.nextDouble();
                    double s = scanner.nextDouble();
                    scanner.nextLine();
                    p1.setCenter(cx, cy);
                    p1.scale = s;
                    p1.mandlebrotSet();
                } catch (InputMismatchException IME) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            }
            else if (input.equals("mandle")) {
                p1.juliaMode = false;
                p1.mandlebrotSet();
                scanner.nextLine();
            } else if (input.equals("back")) {
                run = false;
                scanner.nextLine();
            }else if (input.equals("iterations")) {
                System.out.println("Enter number of iterations before deciding stability (going higher than 100 is not recommended)");
                try {
                    p1.iterations = scanner.nextInt();
                    p1.mandlebrotSet();
                } catch (InputMismatchException IME) {System.out.println("Invalid input");}
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }
            System.out.println(validCommands);
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

    //This was a version that used complex fractions to improve accuracy, unfortunately,
    // It took several minutes to calculate each frame for only a minuscule improvement in accuracy.
    // The class which this relied on has been removed from the source code, I can send it to you if you would like
//    public static void MSuperPrecise(ZFrac z, ZFrac c, int max) {
//        ZFrac next = (z.sqr()).add(c);
//        System.out.println(max);
//        if (max > 1) {
//            MSuperPrecise(next, c, max-1);
//        } else {
//            System.out.println(next);
//            System.out.println(next.toDouble());
//        }
//    }

}
