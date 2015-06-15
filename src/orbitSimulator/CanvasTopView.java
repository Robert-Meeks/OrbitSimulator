package orbitSimulator;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class CanvasTopView extends Canvas {

	// canvas 
	private double _canvasW;
	private double _canvasH;
	private Graphics g2;
	// arrows and lbls 
	private double _lblRx;
	private double _lblRy;
	private String _lblR = "r = ";
	private double _lblVx;
	private double _lblVy;
	private String _lblV = "v = ";
	// planet
	private static Shape _planet;
	private static String _orbitingPlanet = "earth";
	private static double _planetDiameter = 0;
	private static double _planetPositionX = 0;
	private static double _planetPositionY = 0;
	// orbit
	private Shape _orbit;
	private static boolean _orbitColour = false;
	private double _velocity = 0;
	private double _radius = 0;
	private double xOffSet = 0;
	private double yOffSet = 0;
	private double orbitHeight = 0;
	private double orbitWidth = 0;
	private static double _orbitHeight;
	private static double _orbitWidth;
	private static double _orbitPositionX;
	private static double _orbitPositionY;
	private static double _orbitV;
	private static double _orbitR;
	
	private ArrayList<Shape> _velocityArrow = new ArrayList<Shape>();
	private ArrayList<Shape> _radiusArrow = new ArrayList<Shape>();
	Point startDrag, endDrag;
	Shape found = null;
	

	public void paint(Graphics g)
	{
		// calculations
		_canvasW = (double)this.getWidth();
		_canvasH = (double)this.getHeight();
		calcPositionOfPlanet();
		calcPositionOfOrbit();
		calcPositionOfVectorV();
		calcPositionOfVectorR();
		calcPositionOFLblV();
		calcPositionOFLblR();
		
		System.out.println("in paint()");
		// cast g to type Graphics2D - didnt understand the explanation I got from p800 in my java for dummies book
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			// draw planet
			_planet = new Ellipse2D.Double(_planetPositionX, _planetPositionY, _planetDiameter, _planetDiameter); // args - (x, y, w, h)
			
			g2.setStroke(new BasicStroke((float) 0.8));
			
			switch(_orbitingPlanet) {
				case "earth":
					g2.setPaint(Color.decode("#848BFF"));
					break;
				case "mars":
					g2.setPaint(Color.decode("#FF7878"));
					break;
				case "moon": 
					g2.setPaint(Color.GRAY);
					break;
				default:
					System.out.println("black");
					g2.setPaint(Color.BLACK);
					break;
			}
			g2.fill(_planet);
			g2.draw(_planet);

			// draw orbit track
			_orbit = new Ellipse2D.Double(_orbitPositionX, _orbitPositionY, _orbitHeight, _orbitWidth); // args - (x, y, w, h)
			System.out.println("before orbit?");
			g2.setStroke(new BasicStroke((float) 0.8));
			if(_orbitColour == true) {
				g2.setPaint(Color.BLACK);
			}
			else {
				g2.setPaint(Color.RED);
			}
			System.out.println("before draw orbit");
			g2.draw(_orbit);
			System.out.println("after draw orbit");
			
			// velocity
			g2.setPaint(Color.GREEN);
			g2.setFont(new Font("Arial", Font.PLAIN, 10));
			// draw velocity label
			g2.drawString(_lblV + ((Double)_orbitV).toString() + " Km/s", (int)_lblVx, (int)_lblVy);
			// draw velocity arrow
			for(Shape v : _velocityArrow) {
				g2.draw(v);
			}
			// draw radius label
			g2.setPaint(Color.RED);
			g2.drawString(_lblR + ((Double)_orbitR).toString() + " Km", (int)_lblRx, (int)_lblRy);

			// draw radius arrow
			for(Shape r : _radiusArrow) {
				g2.draw(r);
			}
			
	}
	
	public void reRender() {
			System.out.println("in reprint()");
			repaint();
	}
	
	public static void setIllustrativeTopViewParams(/*String planetColour,*/ double radius, double velocity, int canvasW, int canvasH) {
		System.out.println("in setIllustrativeCircularParams()");
		_orbitR = radius;
		_orbitV = velocity;
		
		// check val
		System.out.format("planet diameter = %f\n", (float)_planetDiameter);
		System.out.format("planet position (x,y) = (%f,%f)\n", (float)_planetPositionX, (float)_planetPositionY);
		System.out.format("orbit colour = " + _orbitColour + "\n");
		System.out.format("orbit height = %f\n", (float)_orbitHeight);
		System.out.format("orbit width = %f\n", (float)_orbitWidth);
		System.out.format("orbit position (x,y) = (%f,%f)\n", (float)_orbitPositionX, (float)_orbitPositionY);
	}
	
	private void calcPositionOfPlanet() {

		_planetDiameter = 24;
		System.out.println("Canvas width = " + _canvasW);
		System.out.println("Canvas height = " + _canvasH);
		_planetPositionX = (_canvasW / 2) - 12 ;
		_planetPositionY = (_canvasH / 2) - 12 ;
		
	}
	private void calcPositionOfOrbit() {
		_orbitColour = true;
		_orbitWidth = _canvasH * 0.85;
		_orbitHeight = _canvasH * 0.85;
		_orbitPositionX = (_canvasW / 2) - (_orbitWidth / 2);
		_orbitPositionY = (_canvasH / 2) - (_orbitHeight / 2);
		
	}

	private void calcPositionOFLblR() {
		_lblRy = (_canvasH / 2) + (( _orbitHeight / 2) * 0.85);
		_lblRx = (_canvasW / 2) + (( _orbitWidth / 2) * 0.85);
		//_lblR = _lblR + ((Double)_orbitR).toString();
		
	}


	private void calcPositionOFLblV() {
		_lblVy = (_canvasH / 2);
		_lblVx = (_canvasW / 2) + ( _orbitWidth / 2) + 8;
		//_lblV = _lblV + ((Double)_orbitV).toString();
		
	}


	private void calcPositionOfVectorR() {
		Shape r;
		// main part of arrow
				r = new Line2D.Double((_canvasW/2), (_canvasH/2), ((_canvasW/2) + ((_orbitHeight / 2) * 0.707)), ((_canvasH/2) + ((_orbitWidth / 2) * 0.707)));
				_radiusArrow.add(r);
				// left bit
				r = new Line2D.Double(((_canvasW/2) + ((_orbitHeight / 2) * 0.707)), ((_canvasH/2) + ((_orbitWidth / 2) * 0.707)), ((_canvasW/2) + ((_orbitHeight / 2) * 0.707)), (int)((_canvasH/2) + ((_orbitWidth / 2) * 0.707) - 10));
				_radiusArrow.add(r);
				// right bit 
				r = new Line2D.Double((_canvasW/2) + ((_orbitHeight / 2) * 0.707), ((_canvasH/2) + ((_orbitWidth / 2) * 0.707)), ((_canvasW/2) + ((_orbitHeight / 2) * 0.707) - 10), ((_canvasH/2) + ((_orbitWidth / 2) * 0.707)));
				_radiusArrow.add(r);
	}


	private void calcPositionOfVectorV() {
		Shape v;
		// vector origin
		v = new Ellipse2D.Double((_canvasW/2) + (_orbitWidth/2) - 1, (_canvasH/2), 2, 2);
		_velocityArrow.add(v);
		// main part of arrow
		v = new Line2D.Double((_canvasW/2) + (_orbitWidth/2), (_canvasH/2), (_canvasW/2) + (_orbitWidth/2), (_canvasH/2)-50);
		_velocityArrow.add(v);
		// left bit
		v = new Line2D.Double((_canvasW/2) + (_orbitWidth/2), (_canvasH/2)-50, (_canvasW/2) + (_orbitWidth/2)-7, (_canvasH/2)-43);
		_velocityArrow.add(v);
		// right bit 
		v = new Line2D.Double((_canvasW/2) + (_orbitWidth/2), (_canvasH/2)-50, (_canvasW/2) + (_orbitWidth/2)+7, (_canvasH/2)-43);
		_velocityArrow.add(v);
	}

	
	
}
