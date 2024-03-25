import org.w3c.dom.ls.LSOutput;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        mainUI();


    }

    public static void mainUI() {
        DrawingPanel panel = new DrawingPanel(1000,701);
        Graphics g = panel.getGraphics();
        Scanner scanner = new Scanner(System.in);
        boolean mainLoop = true;
        String welcome = "Welcome to fractal explorer, choose a mode to start exploring.\n geometric lets you draw basic fractal shapes, complex lets you explore the Mandelbrot/Julia sets";
        String modes = "Commands: geometric, complex, close";
        System.out.println(welcome);
        System.out.println(modes);
        while (mainLoop) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "geometric":
                    Fractal.fractalUI(panel,g,scanner);
                    break;
                case "complex":
                    Mandle.mandelbrotSetExplorer(panel, scanner);
                    break;
                case "close":
                    mainLoop = false;
                    System.out.println("Goodbye (couldn't find a way to close the window from program)");
                    break;
                default:
            }
            if (mainLoop) System.out.println(modes);

        }
    }



}


//    DrawingPanel panel = new DrawingPanel(400,400);
//        panel.onMouseClick(new DrawingPanel.DPMouseEventHandler() {
//@Override
//public void onMouseEvent(int x, int y) {
//        System.out.println(x + " " + y);
//        }
//        });