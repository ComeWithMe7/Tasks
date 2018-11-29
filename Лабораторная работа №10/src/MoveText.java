import java.applet.Applet;
import java.awt.*;
import javax.swing.*;

class DrawLine extends Canvas {
    String line;
    Color lineColor;
    Dimension dim = new Dimension(500, 500);
    int x;
    int y;
    private Color bgColor = Color.white;
    boolean isBorder = false;
    boolean reverseWay = false;

    final double COEFFICIENT = 6.8;

    DrawLine(String text, Color textColor, int inX, int inY) {
        super();
        x = inX;
        y = inY;
        line = text;
        lineColor = textColor;
        isBorder = false;
        reverseWay = false;

        setBackground(bgColor);
        setMaximumSize(dim);
        setBounds(x, y, dim.width, dim.height);
    }

    public void paint(Graphics g) {
        g.setColor(lineColor);
        g.drawString(line, x, y);

        if(x + line.length()*COEFFICIENT >= dim.width){
            isBorder = true;
            reverseWay = true;
        }

        if(x == 0) {
            isBorder = true;
            reverseWay = false;
        }
    }
}


public class MoveText extends Applet /*implements Runnable*/{
    static final int CX = 500, CY = 500;

    Thread thread;
    DrawLine drL;

    int num;
    int cnt = 0; // Color counters.

    public void init() {
        setSize(CX, CY);
        setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        setBackground(Color.white);

        int x1 = 150;
        int y1 = 250;

        String text = getParameter("text");
        if(text == null){
            text = "There is no any text.";
        }

        num = Integer.parseInt(getParameter("num"));

        Color buffer = new Color(0, 0, 0); // default color.
        buffer = getHtmlColor(getParameter("color_".concat(Integer.toString(cnt))), buffer);
        cnt++;
        drL = new DrawLine(text, buffer, x1, y1);

        add(drL);
        /*if ( thread == null ) {
            thread = new Thread( this );
        }
        thread.start();*/
    }

    Timer timer = new Timer(1000 / 60, e -> { doMove(); });

    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }

    public void destroy() {
        if (thread != null ) {
            thread.interrupt();
            thread = null;
        }
    }

    /*@Override
    public void run() {
        while ( true ) {
            try {
                Thread.sleep( 100 );
                doMove();
            } catch ( InterruptedException e ) {
                break;
            }
        }
    }*/

    public synchronized void doMove() {
        if(!drL.reverseWay)
            drL.x = drL.x + 10;
        if(drL.reverseWay)
            drL.x = drL.x - 10;
        if(drL.isBorder)
        {
            drL.isBorder = false;
            if(num != 0){
                if(cnt == num)
                    cnt = 0;
                Color buffer = new Color(0, 0, 0);
                buffer = getHtmlColor(getParameter("color_".concat(Integer.toString(cnt))), buffer);
                drL.lineColor = buffer;
                cnt = cnt + 1;
            }
        }
        drL.repaint();
    }


    public Color getHtmlColor( String strRGB, Color def ) {
        if ( strRGB != null && strRGB.charAt(0)== '#' ) {
            try { return new Color(Integer.parseInt( strRGB.substring( 1 ), 16 ) );
            } catch ( NumberFormatException e ) {
                return def;
            }
        }
        return def;
    }
}