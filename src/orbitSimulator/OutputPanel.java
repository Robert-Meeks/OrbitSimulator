package orbitSimulator;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;


public class OutputPanel extends JPanel {
	// JPanels
	private JPanel outputTopView;
	// canvases
	private Canvas canvasTopView;
	
	// vars for calculated orbits (used to calculate rendering) (inputs to this class)
			private double r;
			private double v;
			private double T;
			private double epsilon;
			private double i;
			private String renderScale;
	// var used within this class and generated to pass to relevant canvas
			private double I_r; // Convention Illustrative_radius
			private double I_v;
			private double I_T;
			private double I_epsilon;
			private double I_i;
			
			private Shape earth;
			private Shape orbit;
	
	public OutputPanel() {
		setLayout(new MigLayout("", "[133.00px][195.00,center][152.00]", "[][264.00px][][]"));
		
		JLabel lblGraphicalOutputPanelTitle = new JLabel("Graphical Outputs");
		add(lblGraphicalOutputPanelTitle, "cell 1 0");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tabbedPane, "cell 0 1 3 1,alignx left,aligny top,grow");
		
		JPanel outputTopView = new JPanel();
		outputTopView.setBackground(Color.WHITE);
		tabbedPane.addTab("Image Top view", null, outputTopView, null);
		outputTopView.setLayout(new MigLayout("", "[388.00]", "[180.00]"));
		
		Canvas canvasTopView = new CanvasTopView();
		canvasTopView.setVisible(false);
		canvasTopView.setBackground(Color.WHITE);
		outputTopView.add(canvasTopView, "cell 0 0,grow");
		
		JPanel outputSideView = new JPanel();
		outputSideView.setBackground(Color.WHITE);
		tabbedPane.addTab("Image Side View", null, outputSideView, null);
		outputSideView.setLayout(new MigLayout("", "[414.00]", "[220.00]"));
		
		Canvas canvasSideView = new Canvas();
		canvasSideView.setBackground(Color.WHITE);
		outputSideView.add(canvasSideView, "cell 0 0,grow");
		
		JPanel outputAnimation = new JPanel();
		outputAnimation.setBackground(Color.WHITE);
		tabbedPane.addTab("2D Animation (Top View)", null, outputAnimation, null);
		outputAnimation.setLayout(new MigLayout("", "[418.00]", "[227.00]"));
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		outputAnimation.add(canvas, "cell 0 0,grow");
		
		/*
		 * The layered Jpanel would seem to be the way to have toggle views in the output I toggle orbit track or equatorial plane
		 * */
		
		
	}
	//from circular
	public void drawNewGraphics(double r, double v, double T, double epsilon, double i, String renderScale)
	{
		// set vals
		this.r = r;
		this.v = v;
		this.T = T;
		this.epsilon = epsilon;
		this.i = i;
		this.renderScale = renderScale;
		// call appropriate 
		if(renderScale.toLowerCase() == "illustrative")
		{
			calculateValuesForRealViewOnCanvasUsingCircularInputs();
		}
		else if(renderScale.toLowerCase() == "accurate")
		{
			calculateValuesForIllustrativeViewOnCanvasUsingCircularInputs();
		}
		
		//drawCanvasTopView(); // send vals for illustrative and real view 
		//drawCanvasSideView();  // send vals for illustrative and real view
		
	}
	
	//from elliptical
	public void drawNewGraphics()
	{
		
	}
	
	private void calculateValuesForRealViewOnCanvasUsingCircularInputs()
	{
		
		// planet 
			// set color 
			 // height 
			// width
			// x position
			// y position
		
		// orbit 
			// height 
			// width
			// x position
			// y position
		
		
		((CanvasTopView) canvasTopView).setCircularParameters(/* add the appropriate args*/);
		
		
		//CanvasTopView.paint();
		System.out.println("after paint()");
		
		canvasTopView.setVisible(true);
		

		// NB nothing to calculate just pass the values on so they can be displayed. because its illustrative there is no need for scale 
		// so a default layout is possible. The purpose of this is to make sure I can do it properly without worrying about calculations
		// but also the argument is its a good teaching aid. This becomes useless if more than one orbit is on view at once!!!!
	}
	private void calculateValuesForIllustrativeViewOnCanvasUsingCircularInputs()
	{
		
	}

}
