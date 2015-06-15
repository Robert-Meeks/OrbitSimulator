package orbitSimulator;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CanvasAnimation extends Canvas {

	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(new Font("Arial", Font.PLAIN, 24));
		g2.drawString("Not implemented yet.", 30, 55);
		g2.setFont(new Font("Arial", Font.PLAIN, 12));
		g2.drawString("- I have the algorithm for the animation ", 50, 80);
		g2.drawString("(tested in JavaScript), I just need to learn", 60, 100);
		g2.drawString("how to animate on a canvas in Java to animate.", 60, 120);
	}
	
}
