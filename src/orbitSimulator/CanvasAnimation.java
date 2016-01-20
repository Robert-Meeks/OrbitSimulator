package orbitSimulator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Timer;






public class CanvasAnimation extends Canvas implements ActionListener {
	
	private int CanvasW;
	private int CanvasH;
	private ArrayList<Double> _coordListU = new ArrayList<Double>();
	private ArrayList<Double> _coordListV = new ArrayList<Double>();
	private int u;
	private int v;
	private Double RAAN = 0.0;
	private int step = 0;
	//private ActionListener gListener;
	private Timer gTimer = new Timer(30, this);
			/* Do I do the calculations in this class for the animation or do I do them in the output panel?? 
	how do i buffer the animation? prob best to create all the information and then set it going. however i did 
	figure out how to do double buffering in the asteroid dodge applet so maybe copy this across??? in which case 
	it can probably work it out as it goes but may still need to calc all the point anyway NNB the javascript animation
	is there to be used as well!!!!  */
	private boolean flipper = true;
	private boolean setRAAN = false;
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Shape canvasReset = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
		
		g2.setPaint(Color.WHITE);
		g2.setPaint(Color.BLACK);
		g2.draw(canvasReset);

		
		// draw planet 
		g2.setPaint(Color.BLUE);
		g2.fillOval(((this.getWidth() / 2)-5), ((this.getHeight() / 2)-5), 10, 10);
		// draw satellite NB need to correct RAAN of the orbit and then return to normal each loop
		double t = -90 - RAAN;
		//System.out.println("%%%%%%%%%%%%%%%%%% t = "+ t);
		g2.rotate(t, 236, 217);
		
		g2.setPaint(Color.RED);
		g2.fillOval(u, v, 5, 5);
		
		t = 90 + RAAN;
		//System.out.println("%%%%%%%%%%%%%% t = "+ t);
		g2.rotate(t, 236, 217);
		
		// draw the red lines
		g2.drawLine(10, 0, 10, 440);
		g2.drawLine(470, 0, 470, 440);
		g2.drawLine(0, 10, 480, 10);
		g2.drawLine(0, 430, 480, 430);
	}
	
	// - NEW bit for animation
	// circular 
	public void AnimationControl(ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray, Double raan) {
//		int arrayU = canvasUArray.size();
//		for(int i = 0; i < arrayU; i++) {
//			System.out.println("U values - " + canvasUArray.get(i));
//		}
//		for(int i = 0; i < arrayU; i++) {
//			System.out.println("V values - " + canvasVArray.get(i));
//		}
		_coordListU = canvasUArray;
		_coordListV = canvasVArray;
		System.out.println("CanvasW = " + this.getWidth());
		System.out.println("CanvasH = " + this.getHeight());
		Double RAAN = raan;
		System.out.println("%%%%%%%%%%%%%%    raan = " + raan);
		System.out.println("%%%%%%%%%%%%%%    RAAN = " + RAAN);
		
		gTimer.start();
	}
	// - END
	
	
	
	public int GetCanvasW() {
		return CanvasW;
	}
	public int GetCanvasH() {
		return CanvasH;
	}
	public void SetCanvasW(int w) {
		CanvasW = w;
	}
	public void SetCanvasH(int h) {
		 CanvasH = h;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("Timer Tick");
		UpdateSatPosition();
		repaint();
	}

	private void UpdateSatPosition() {
		double uAsDouble = _coordListU.get(step);
		u = (int) uAsDouble;
		double vAsDouble = _coordListV.get(step);
		v = (int) vAsDouble;
		if(step == _coordListU.size() - 1) {
			step = 0;
		} else {
		step++;
		}
		
	}
}
