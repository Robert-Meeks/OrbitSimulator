package orbitSimulator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;


public class OutputPanel extends JPanel {
	// tab panel
	private static JTabbedPane tabbedPane;
	// JPanels
	private JPanel outputTopView;
	private JPanel outputSideView;
	private JPanel outputAnimation;
	// canvases
	private static Canvas canvasTopView;
	private static Canvas canvasSideView;
	private Canvas canvasAnimation;
	
	// vars for calculated orbits (used to calculate rendering) (inputs to this class)
		// Common to both
			private double T;
			private double epsilon;
			private double i;
			private String renderScale;
		//circular
			private double r;
			private double v;
		// Elliptical 
			private Double raan; 
			private Double ra;
			private Double rp;
			private Double a;
			private Double e;
			private Double VatR; /* User defined velocity, either directly or calculated from radius or true anomaly the user has entered */
			private Double RforV; /*User defined radius at a velocity might be calculated from a velocity or true anomaly the user has entered */
			private Double va;
			private Double vp;
			private Double ta; /* True Anomaly */
			
			//private Shape _planet;
			//private Shape _orbit;
	
	public OutputPanel() {
		setLayout(new MigLayout("", "[181.00px][195.00,center][186.00]", "[][264.00px][][195.00]"));
		
		JLabel lblGraphicalOutputPanelTitle = new JLabel("Graphical Outputs");
		add(lblGraphicalOutputPanelTitle, "cell 1 0");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tabbedPane, "cell 0 1 6 6,alignx left,aligny top,grow");
		
		outputTopView = new JPanel();
		outputTopView.setBounds(0, 0, 310, 210);
		outputTopView.setBackground(Color.WHITE);
		outputTopView.setBorder(null);
		tabbedPane.addTab("Image Top view", null, outputTopView, null);
		outputTopView.setLayout(new MigLayout("", "[469.00]", "[434.00,bottom]"));
		
		canvasTopView = new CanvasTopView();
		canvasTopView.setBackground(Color.RED);
		canvasTopView.setBounds(0, 0, 434, 434);
		canvasTopView.setVisible(false);
		canvasTopView.setBackground(Color.decode("#FFFFFF"));
		outputTopView.add(canvasTopView, "cell 0 0,aligny center,grow");
		
		outputSideView = new JPanel();
		outputSideView.setBackground(Color.WHITE);
		tabbedPane.addTab("Image Side View", null, outputSideView, null);
		outputSideView.setLayout(new MigLayout("", "[472.00]", "[:434.00:434,fill]"));
		
		canvasSideView = new CanvasSideView();
		canvasSideView.setBackground(Color.WHITE);
		canvasSideView.setVisible(false);
		outputSideView.add(canvasSideView, "cell 0 0,aligny center,grow");
		
		outputAnimation = new JPanel();
		outputAnimation.setBackground(Color.WHITE);
		tabbedPane.addTab("2D Animation (Top View)", null, outputAnimation, null);
		outputAnimation.setLayout(new MigLayout("", "[479.00]", "[434.00]"));
		
		canvasAnimation = new CanvasAnimation();
		canvasAnimation.setBackground(Color.WHITE);
		((CanvasAnimation) canvasAnimation).SetCanvasW(canvasAnimation.getWidth());
		((CanvasAnimation) canvasAnimation).SetCanvasH(canvasAnimation.getHeight());
		outputAnimation.add(canvasAnimation, "cell 0 0,aligny center,grow");
		
		/* [I have made the size og the canvas constant so this is redundant as it cant be changed]
		canvasAnimation.addComponentListener(new ComponentListener() {// HERE - im trying to listen for change in canvas size (not needed but would be good to do) i think i need to declare ComponentEvent at the top 
			@Override
			public void componentResized(ComponentEvent e) {
				//Get size of frame and do cool stuff with it 
				System.out.println("THE LISTENER WORKS");
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});*/
		
		
		
	} // END CONSTRUCTOR
	//==============================================================================================================================
	
	
	//---------from circular
	public void drawNewGraphics(double r, double v, double T, double epsilon, String renderScale, double i, ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray)
	{
		// set vals
		this.r = r;
		this.v = v;
		this.T = T;
		this.epsilon = epsilon;
		this.i = i;
		this.renderScale = renderScale;
		System.out.println("in drawNewGraphics() and call calculateSetRenderCircularOrbitParams();");
		calculateSetRenderCircularOrbitParams();
		
		// - NEW bit for animation 
		((CanvasAnimation) canvasAnimation).AnimationControl(canvasUArray, canvasVArray, 0.0);
		// - END new bit for animation 
		
	}
	
	
	
	private void calculateSetRenderCircularOrbitParams() {
		
		// set planet colour
			// CanvasTopView class is current getting it straight from OrbitMainFrame class - this needs to change!
		// planet Diameter 
		System.out.println("in calculateSetRenderCircularOrbitParams()");
		
		
		// set canvas vals as appropriate 
				if(renderScale.toLowerCase() == "illustrative")
				{
					//top view
					System.out.println("in the if to select illustrative and call setIllustrativeCircularParams()");
					((CanvasTopView) canvasTopView).setIllustrativeTopViewParams(/*_planetColour,*/ r, v);
					System.out.println("call reRender()");
					((CanvasTopView) canvasTopView).reRender();
					canvasTopView.setVisible(true);
					
					//side view
					((CanvasSideView) canvasSideView).setIllustrativeSideViewParams(i);
					((CanvasSideView) canvasSideView).reRender();
					canvasSideView.setVisible(true);
				}
				else if(renderScale.toLowerCase() == "accurate")
				{
					
				}
				
	}
	
	private void calculateValuesForIllustrativeViewOnCanvasUsingCircularInputs() // pass top side and animation params
	{
		// planet diameter 
		
		
	}
	
	private void calculateValuesForRealViewOnCanvasUsingCircularInputs() // pass top side and animation params
	{
		
		// planet 
			// set color 
			// set orbit height and width
			// set x, y position
		
		// orbit 
			// height 
			// width
			// x position
			// y position
		
		
	//	((CanvasTopView) canvasTopView).setCircularParameters(/* add the appropriate args*/);
		
		
		//CanvasTopView.paint();
	//	System.out.println("after paint()");
		
		
		

		// NB nothing to calculate just pass the values on so they can be displayed. because its illustrative there is no need for scale 
		// so a default layout is possible. The purpose of this is to make sure I can do it properly without worrying about calculations
		// but also the argument is its a good teaching aid. This becomes useless if more than one orbit is on view at once!!!!
	}
	
	//==============================================================================================================================
	//from elliptical
		public void drawNewGraphics(Double raan, Double ra, Double rp, Double a, Double e, Double i, Double VatR, 
				Double RforV, Double va, Double vp, Double epsilon, Double TrueAnomaly, Double T, String renderScale, ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray)
		{
			this.raan = raan; 
			this.ra = ra;
			this.rp = rp;
			this.a = a;
			this.e = e;
			this.i = i;
			this.VatR = VatR;
			this.RforV = RforV;
			this.va = va;
			this.vp = vp;
			this.epsilon = epsilon;
			this.ta = TrueAnomaly;
			this.T = T;
			this.renderScale = renderScale;
			calculateSetRenderEllipticalOrbitParams();
			// - NEW bit for animation 
			((CanvasAnimation) canvasAnimation).AnimationControl(canvasUArray, canvasVArray, raan);
			// - END new bit for animation 
		}
		
		private void calculateSetRenderEllipticalOrbitParams() {
			if (VatR == null) {
				VatR = -0.0;
			}
			if (RforV == null) {
				RforV = -0.0;
			}
			if (ta == null) {
				ta = -0.0;
			}
			if (renderScale.toLowerCase() == "illustrative") {
				((CanvasTopView) canvasTopView).setIllustrativeTopViewParams(/*_planetColour,*/ raan, ra, rp, a, VatR, RforV, va, vp, ta, e);
				((CanvasTopView) canvasTopView).reRender();
				canvasTopView.setVisible(true);
				((CanvasSideView) canvasSideView).setIllustrativeSideViewParams(/*_planetColour,*/ raan, ra, rp, a, VatR, RforV, va, vp, ta, e, i);
				((CanvasSideView) canvasSideView).reRender();
				canvasSideView.setVisible(true);
			}
			else if (renderScale.toLowerCase() == "accurate") {
				
			}
			
		}
		public static void resetOutput() {
			
			System.out.println("=######################=");
			//int zero = 0;
			//tabbedPane.setSelectedIndex(zero);
			
			canvasTopView.setVisible(false);
			canvasSideView.setVisible(false);
		}

}
