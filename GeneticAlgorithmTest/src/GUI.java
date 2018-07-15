import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class GUI extends Applet {

	public static int FRAME_WIDTH = 500, FRAME_HEIGHT = 600;
	Population test;
	PVector v1;
	boolean isBest;
	
	public void init(){
		resize (FRAME_WIDTH, FRAME_HEIGHT);
		test = new Population(100);
	}
	
	public void start(){}
	
	public void stop (){}
	
	public void destroy(){}
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.setColor(Color.red);
		g.fillArc(FRAME_WIDTH/2 - 15, 40, 15, 15, 0, 360);
		update(g);
	}
	
	public void update (Graphics g){
		try{
			Thread.sleep(30);
		}catch(InterruptedException ex){}
		g.setColor(Color.white);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.setColor(Color.red);
		g.fillArc(FRAME_WIDTH/2 - 15, 10, 15, 15, 0, 360);

		if (test.allDotsDead()){
			try{
				Thread.sleep(50);
			}catch(InterruptedException ex){}
			test.calculateFitness();
			test.naturalSelection();
			test.mutateBabies();
		} else {
			test.update();
		}
		
		if (test != null && test.dots != null){
			g.setColor(Color.green);
			g.fillArc((int)test.dots[0].pos.getX(), (int)test.dots[0].pos.getY(), 13, 13, 0, 360);		
			g.setColor(Color.black);
			for (int i = 1; i < test.dots.length; i++){
				g.fillArc((int)test.dots[i].pos.getX(), (int)test.dots[i].pos.getY(), 10, 10, 0, 360);	
			}
		}
		g.drawString("Gen "+Population.gen, 450, 10);
		repaint();
	}
}