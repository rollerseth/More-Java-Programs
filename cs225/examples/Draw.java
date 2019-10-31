import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class Draw {
    public Canvas extends JComponent {
	// if you want a 2D graphics you have to cast 
	public void paint(Graphics g) {
	    //	    Graphics2D gg = (Graphics2D)g;
	    g.drawRect(100, 100, 200, 200);
	    g,setColor(Color.BLUE);
	    g.fillArc(200, 200, 200, 200, 0, 360);
	    // last two are in degrees
	    int[] x = { 100, 200, 200, 150, 100 };
	    int[] y = { 250, 250, 150, 100, 150 };
	    g.setColor(Color.YELLOW);
	    g.fillPolygon(x, y, 5);
	    g.drawImage(image, 0, 0, null);
	    try {
	    BufferedImage image = ImageIO.read(new File("sun.png"));
	    }
	    catch (IOException e) {
		System.err.println(e.message());
	    }
	    //Line2D.Float line = new Line2D.Float(100, 100, 400, 400);
	    //gg.draw(line);
	   	    
	}
    }
    
    
    class DrawFrame extends JFrame {
	DrawFrame() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(600, 600));
	    Canvas canvas = new Canvas();
	    add(canvas);
	    JButton button = new JButton("Exit");
	    button.addActionListener((e) -> {frame.setVisible(false); frame.dispose(); });
	    add(button, BorderLayout.SOUTH);
	    pack();

	}

    }

    DrawFrame frame;

    public Draw() {
	frame = new DrawFrame();
    }

    public static void main(String[] argv) {

    }

}
