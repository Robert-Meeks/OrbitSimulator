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
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CanvasSideView extends Canvas {
		// general constants
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
		// orbit parameters 
		private static String _orbitType;
		private Shape _orbit;
		private static boolean _orbitColour = false;
		private double _velocity = 0;
		private double _radius = 0;
		private double _orbitx1;
		private double _orbitx2;
		private double _orbity1;
		private double _orbity2;
		private static double _i; 
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
		// equitirial plane
		private double _ePlanex1;
		private double _ePlaney1;
		private double _ePlanex2;
		private double _ePlaney2;
		private String _ePlaneLbl = "Equitorial Plane";
		
		// inclination 
		private int smallAngleOffset = 0;
		private ArrayList<Shape> _inclination = new ArrayList<Shape>();
		Point startDrag, endDrag;
		Shape found = null;
		

		public void paint(Graphics g)
		{
			// calculations
			_canvasW = (double)this.getWidth();
			_canvasH = (double)this.getHeight();
			calcPositionOfPlanet();
			calcPositionOfOrbit();
			calcEquitorialPlane();
			calcInclinationLbl();
			
			// cast g to type Graphics2D - didnt understand the explanation I got from p800 in my java for dummies book
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
			// planet
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
			
			// Equatorial plane
			g2.setPaint(new Color(182, 182, 182));
			Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 9);
	        g2.setStroke(dashed);
			g2.drawLine(0, (int)(_canvasH / 2), (int)_canvasW, (int)(_canvasH / 2));
			g2.setFont(new Font("Arial", Font.PLAIN, 10));
			g2.drawString("Equatorial Plane", 10, (int)(_canvasH / 2 - 4));
			
			// orbit
			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke( 0.8f));
			g2.drawLine((int)_orbitx1, (int)_orbity1, (int)_orbitx2, (int)_orbity2); 
			System.out.println("before orbit?");
			
			
			//orbit labels
				// inclination arc
			for(Shape I : _inclination) {
				g2.draw(I);
			}
				// inclination label (degree symbol unicode u00B0)
			if (_i <= 20) {
				smallAngleOffset = 15;
			}
			g2.setFont(new Font("Arial", Font.PLAIN, 10));
			g2.drawString("i = " + String.format("%.0f", _i) + "\u00B0", (int)((_canvasW / 2) + (_planetDiameter * 1.4f)), (int)(_canvasH / 2) - 3 + smallAngleOffset);
			
			
			
		} // END paint
		
		public void reRender() {
			System.out.println("in repaint for side view canvas");
			repaint();
		}
		
		public static void setIllustrativeSideViewParams(double i) {
			_i = i;
			_orbitType = "circular";
		}

		public void setIllustrativeSideViewParams(Double ap, Double ra,	Double rp, Double a, Double VatR, Double RforV, 
				Double va, Double vp, Double ta, Double e, Double i) {
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
			_i = i; /* USED */
			_orbitType = "elliptical";
		}

		private void calcPositionOfPlanet() {

			_planetDiameter = 24;
			_planetPositionX = (_canvasW / 2) - 12 ;
			_planetPositionY = (_canvasH / 2) - 12 ;
			
		}
		private void calcPositionOfOrbit() {
			double orbitLeftRadius = 0;
			double orbitRightRadius = 0;
			double orbitWidth = _canvasH * 0.85;
			double i = _i;
			if (_orbitType == "circular") {
				orbitLeftRadius = orbitWidth / 2; 
				orbitRightRadius = orbitLeftRadius;
			} else if (_orbitType == "elliptical") {
				double canvasRp = (_rp / (2 * _a)) * orbitWidth;
				double canvasRa = (_ra / (2 * _a)) * orbitWidth;
//				if (_ap <= 90 || _ap > 270) { // ie periapsis is on the left hand side of the canvas
					orbitLeftRadius = canvasRp; // = Rp
					orbitRightRadius = canvasRa; // = Ra
//				} else if (_ap > 90 && _ap <= 270) { // ie apoapsis is on the left hand side of the canvas
//					i = i * -1;
//					orbitLeftRadius = canvasRa; // = Ra
//					orbitRightRadius = canvasRp; // = Rp
//				}
			}
			// left radius (side)
			System.out.println("_canvasW = " + _canvasW);
			System.out.println("_canvasH = " + _canvasH);
			System.out.println("_i = " + _i);
			_orbitx1 = (_canvasW / 2) - orbitLeftRadius * cos(i); 
			_orbity1 = (_canvasH / 2) + orbitLeftRadius * sin(i);
			System.out.println("orbit x1 and y1 respectively = " + _orbitx1 + _orbity1);
			
			// right radius (side)
			_orbitx2 = (_canvasW / 2) + orbitRightRadius * cos(i); 
			_orbity2 = (_canvasH / 2) - orbitRightRadius * sin(i);
			System.out.println("orbit x2 and y2 respectively = " + _orbitx2 + _orbity2);
		}
		
		private void calcEquitorialPlane() {
			_ePlanex1 = 0;
			_ePlaney1 = _canvasH / 2;
			_ePlanex2 = _canvasW;
			_ePlaney2 = _canvasH / 2;
		}
		
		private void calcInclinationLbl() {
			
			Shape I;
			
			I = new Arc2D.Double(new Rectangle2D.Double(_canvasW / 2 - ((_planetDiameter /2) * 1.8), _canvasH / 2 - ((_planetDiameter/2) * 1.8), _planetDiameter * 1.8, _planetDiameter * 1.8), 0, _i, Arc2D.OPEN);
			_inclination.add(I);
			
		}

		// General helper methods --------------------------------------------------------

		 private static double sin(double t) {
			 double ans = Math.sin(t * D2R);
			 return ans;
		 }
		 private static double cos(double t) {
			 double ans = Math.cos(t * D2R);
			 return ans;
		 }
		


		
		
}
