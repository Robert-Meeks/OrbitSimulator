package orbitSimulator;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CanvasTopView extends Canvas {

	private Graphics g2;
	private double _velocity = 0;
	private double _radius = 0;
	private double xOffSet = 0;
	private double yOffSet = 0;
	private double _planetDiameter = 0;
	private double orbitHeight = 0;
	private double orbitWidth = 0;
	
	
	
	
	public void paint(Graphics g)
	{
		System.out.println("in paint()");
		// cast g to type Graphics2D - didnt understand the explanation I got from p800 in my java for dummies book
			Graphics2D g2 = (Graphics2D)g;
			System.out.println("cast completed");
			// the following controls tell the program how to render the drawing. from what I can see the 2 commands 
			// used in the example make the line less pixely (softer) and makes it so the user can see the rendering 
		    // in the order it is coded which looks cool
			//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			System.out.println("before planet");
			
			Shape planet = new Ellipse2D.Double(CanvasTopView.HEIGHT/2 - xOffSet, CanvasTopView.HEIGHT/2 - yOffSet, _planetDiameter, _planetDiameter); // args - (x, y, w, h)
			
			System.out.println(" planet");
			g2.setStroke(new BasicStroke((float) 0.8));
			g2.fill(planet);
			g2.setPaint(Color.GREEN);
			System.out.println("before draw planet");
			g2.draw(planet);
			
			Shape orbit = new Ellipse2D.Double(CanvasTopView.HEIGHT/2, CanvasTopView.HEIGHT/2, orbitWidth, orbitHeight); // args - (x, y, w, h)
			System.out.println("before orbit?");
			g2.setStroke(new BasicStroke((float) 0.8));
			g2.setPaint(Color.BLACK);
			System.out.println("before draw orbit");
			g2.draw(orbit);
			System.out.println("after draw orbit");
			
	}
	
	public void setCircularParameters()
	{
		repaint();
	}
}
