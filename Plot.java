import java.awt.*;

public class Plot {

    public DrawingPanel panel;
    public Graphics g;

    public int[] winSize;

    public double xMax = 10;
    public double yMax = 10;
    public int[] center = {0,0};
    // Plot origin relative to drawing panel origin

    public double xScale = 1;
    public double yScale = 1;

    public Plot(DrawingPanel thePanel, Graphics theGraphics, double maximumX, double maximumY) {
        panel = thePanel;
        g = theGraphics;
        xMax = maximumX;
        yMax = maximumY;
        winSize[0] = panel.getWidth();
        winSize[1] = panel.getHeight();

        center[0] = Math.ceilDiv(winSize[0],2);
        center[1] = Math.ceilDiv(winSize[1],2);

        xScale = xMax / Math.floorDiv(winSize[0],2);
    }

    public void plotPoint(double X, double Y, Color color) {
        int x = (int)Math.round(X);
        int y = (int)Math.round(Y);

        int xP = center[0] + x;
        int yP = center[1] - y;
        if (xP < 0 || yP < 0) {
            System.out.println("Out of panel window");
        } else {
            drawPixel(xP, yP, color);
        }

    }

    public void drawPixel(int x, int y, Color color) {
        g.setColor(color);
        g.drawLine(x,y,x,y);
    }

}
