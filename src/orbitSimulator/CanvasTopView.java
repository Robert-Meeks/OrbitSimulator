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

	// constants 
	static double pi = Math.PI;
	static double D2R = pi / 180;
	double R2D = 180 / pi;
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
	private static String _orbitType;
	// circular
	private double _velocity = 0;
	private double _radius = 0;
	// eliptical
	private static double _ap = 0;
	private static double _ra = 0;
	private static double _rp = 0;
	private static double _a = 0;
	private static double _VatR = 0;
	private static double _RforV = 0;
	private static double _va = 0;
	private static double _vp = 0;
	private static double _ta = 0; // default of 0 set for when it is a circular orbit.
	private static double _e = 0;
	// render vals
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
		
		System.out.println("------- top view canvas Methods - position of...");
		calcPositionOfPlanet();
		calcPositionOfOrbit();
			calcPositionOfVectorV();
			calcPositionOfVectorR();
			calcPositionOFLblV();
			calcPositionOFLblR();
		System.out.println("------ END");
		
		// TESTS
		System.out.println("======= TEST SECTION - in top view convas ");
		System.out.println("orbit type = " + _orbitType);
		System.out.println("_planetPositionX = " + _planetPositionX);
		System.out.println("_planetPositionY = " + _planetPositionY);
		System.out.println("_planetDiameter = " + _planetDiameter);
		System.out.println("_orbitingPlanet = " + _orbitingPlanet);
		System.out.println("_orbitPositionX = " + _orbitPositionX);
		System.out.println("_orbitPositionY = " + _orbitPositionY);
		System.out.println("_orbitHeight = " + _orbitHeight);
		System.out.println("_orbitWidth = " + _orbitWidth);
		System.out.println("_ap = " + _ap);
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
		System.out.println("======= END TEST SECTION - canvas top view");
		//------
		
		if (_orbitType == "elliptical") { /* specific to elliptical */
			
		}
		
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
			_orbit = new Ellipse2D.Double(_orbitPositionX, _orbitPositionY, _orbitWidth, _orbitHeight); // args - (x, y, w, h)
			System.out.println("before orbit?");
			// set orbit rotation to take into account Arg of Peri
			g2.rotate(Math.toRadians(_ap), _canvasW / 2, _canvasH / 2);
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
			System.out.println("in top view reprint()");
			repaint();
	}
	// from circular
	public static void setIllustrativeTopViewParams(/*String planetColour,*/ double radius, double velocity, int canvasW, int canvasH) {
		System.out.println("in setIllustrativeCircularParams()");
		_orbitR = radius;
		_orbitV = velocity;
		_orbitType = "circular";
		// TEST vals are coming through correctly 
		System.out.format("planet diameter = %f\n", (float)_planetDiameter);
		System.out.format("planet position (x,y) = (%f,%f)\n", (float)_planetPositionX, (float)_planetPositionY);
		System.out.format("orbit colour = " + _orbitColour + "\n");
		System.out.format("orbit height = %f\n", (float)_orbitHeight);
		System.out.format("orbit width = %f\n", (float)_orbitWidth);
		System.out.format("orbit position (x,y) = (%f,%f)\n", (float)_orbitPositionX, (float)_orbitPositionY);
	}
	// from elliptical 
	public static void setIllustrativeTopViewParams(double ap, double ra, double rp, double a, double VatR, double RforV, double va,
			double vp, double ta, double e) {
		_ap = ap; /* USED */
		_ra = ra; /* USED */
		_rp = rp; /* USED */
		_a = a; /* USED */
		_VatR = VatR;
		_RforV = RforV;
		_va = va;
		_vp = vp;
		_ta = ta; /* USED */
		_e = e; /* USED */
		_orbitType = "elliptical";
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
		
		if (_orbitType == "circular") {
			System.out.println("circular orbit position calculated");
			_orbitWidth = _canvasH * 0.85;
			_orbitHeight = _canvasH * 0.85;
			_orbitPositionX = (_canvasW / 2) - (_orbitWidth / 2);
			_orbitPositionY = (_canvasH / 2) - (_orbitHeight / 2);
		} else if (_orbitType == "elliptical") {
			System.out.println("elliptical orbit position calculated");
			// size 
			if ((_ap <= 45) || (_ap >= 135 && _ap <= 225 ) || (_ap >= 315)) {
				System.out.println("if ------ calc orbit dimensions for canvas");
				_orbitWidth = _canvasW * 0.5;
			} else if((_ap > 45  && _ap < 135) || (_ap > 225 && _ap < 315)) {
				System.out.println("else if ------ calc orbit dimensions for canvas");
				_orbitWidth = _canvasH * 0.5;
			} 
			_orbitHeight = 2 * (0.5 * _orbitWidth * (Math.sqrt(1 - (_e * _e))));
			// position (NOT INCLUDING rotation for Arg of Periapsis. Rotation is done in the animation)
			
			_orbitPositionX = (_canvasW / 2) - ((_rp / (2 * _a)) * _orbitWidth);
			_orbitPositionY = (_canvasH / 2) - (_orbitHeight / 2); 
				
		}
		
		
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

	private void calcPositionOfVectorV() {
		double offSetX = 0;
		double offSetY = 0;
		if (_orbitType == "circular") {
			offSetX = _orbitWidth / 2;
			offSetY = _orbitHeight / 2;
			
		} else if (_orbitType == "elliptical") {
			// get orbit dimensions as far as the canvas is concerned so km to pixels
			double canvasRp = _orbitWidth * (_rp / (2 * _a));
			double canvasRa = _orbitWidth * (_ra / (2 * _a));
			double canvas_a = (canvasRp + canvasRa) / 2;
			double r = canvas_a * (1 - _e * _e) /1 + _e * cos(_ta);
			
			if (_ta <= 90) {
				offSetX = r * sin(_ta) ;
				offSetY = r * cos(90 - _ta);
			} else if (_ta > 90 && _ta <= 180) {
				
			} else if (_ta > 180 && _ta <= 270) {
				
			} else if (_ta > 270 && _ta <= 360) {
				
			}
		}	
			Shape v;
			// vector origin
			v = new Ellipse2D.Double((_canvasW/2) + (offSetX) - 1, (_canvasH/2), 2, 2);
			_velocityArrow.add(v);
			// main part of arrow
			v = new Line2D.Double((_canvasW/2) + (offSetX), (_canvasH/2), (_canvasW/2) + (offSetX), (_canvasH/2)-50);
			_velocityArrow.add(v);
			// left bit
			v = new Line2D.Double((_canvasW/2) + (offSetX), (_canvasH/2)-50, (_canvasW/2) + (offSetX)-7, (_canvasH/2)-43);
			_velocityArrow.add(v);
			// right bit 
			v = new Line2D.Double((_canvasW/2) + (offSetX), (_canvasH/2)-50, (_canvasW/2) + (offSetX)+7, (_canvasH/2)-43);
			_velocityArrow.add(v);
		
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

 private static double sin(double t) {
	 double ans = Math.sin(t * D2R);
	 return ans;
 }
 private static double cos(double t) {
	 double ans = Math.cos(t * D2R);
	 return ans;
 }
	

	
	
}
