import org.apache.commons.numbers.complex.Complex;

import java.awt.*;

public class Plot {

    public DrawingPanel panel;

    public double w;
    public double h;
    public double[] center = {0,0};
    // Plot origin relative to drawing panel origin

    public double scale = 1;

    public Plot(DrawingPanel thePanel, double[] theCenter, double theScale ) {
        panel = thePanel;
        w = panel.getWidth();
        h = panel.getHeight();
        scale = theScale;
        center[0] = theCenter[0];
        center[1] = theCenter[1];

    }

    public void setCenter(double x, double y) {
        center[0] = x;
        center[1] = y;
    }

//    public void plotPoint(double X, double Y, Color color) {
//        int x = (int)Math.round(X);
//        int y = (int)Math.round(Y);
//
//        int xP = center[0] + x;
//        int yP = center[1] - y;
//        if (xP < 0 || yP < 0) {
//            System.out.println("Out of panel window");
//        } else {
//            panel.setPixel(x,y,color);
//        }
//
//    }


    public void mandlebrotSet() {
        panel.clear();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Complex z = Complex.ofCartesian(i*scale+(center[0]-scale*(w/2)),j*scale+(center[1]-scale*(h/2)));
                if (Mandle.MandelSetCheck(Mandle.MandelFuncItt(z,z,50))) {
                    panel.setPixel(i,j,Color.BLACK);
                } else panel.setPixel(i,j,Color.BLUE);
            }
        }
    }

}
