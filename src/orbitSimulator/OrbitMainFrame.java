package orbitSimulator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/* TO DO LIST 
	- error messages in:
		-- inputPanelChanger() for to out comes of the switch within it.
*/	
// added a comment to check I had set things up properly
public class OrbitMainFrame extends JFrame {
	
	// FOR TESTING
	private Border redBorder = 
			BorderFactory.createLineBorder(Color.RED, 1);
	private Border yellowBorder = 
			BorderFactory.createLineBorder(Color.YELLOW, 1);
	private Border greenBorder = 
			BorderFactory.createLineBorder(Color.GREEN, 1);
	private Border blueBorder = 
			BorderFactory.createLineBorder(Color.BLUE, 1);
	private Border cyanBorder = 
			BorderFactory.createLineBorder(Color.CYAN, 1);
	private Border orangeBorder = 
			BorderFactory.createLineBorder(Color.ORANGE, 1);
	private Border whiteBorder = 
			BorderFactory.createLineBorder(Color.WHITE, 1);
	// input panels
	private JPanel defaultInputPanel; 
	private JPanel circularPanel;
	private JPanel ellipticalPanel;
	//toolbar
		// params
		public static String orbitingBody = "null";
		public static String manoeuvreType = "null";
		public static HashMap<String, Double> orbitingBodyData;
		private static String _orbitType;
		// Parameterized components for comboboxes stored in respecive arraylists 
			// OrbitTypes  
			private String selectType = "Select Type"; // first entry
			private String circular = "Circular"; // second entry
			private String elliptical = "Elliptical"; // third entry
			private String hyperbolic = "Hyperbolic"; // fourth entry
			// OrbitingBodies
			private String selectOrbitingBody = "Select Orbiting Body";
			private String earth = "Earth";
			private String moon = "Moon";
			private String mars = "Mars";
			// Maneuvres
			private String selectManoeuvre = "Select Manoeuvre";
			private String orbit = "Orbit";
			private String hohmannTransfer = "Hohmann Transfer";
		// BUTTONS
			private JButton btnSelectOrbitType;
			private JButton btnSelectOrbiting;
			private JButton btnSelectManoeuvre;
		// COMBOBOX
			private JComboBox comboBoxOrbitType;
			final JComboBox comboBoxOrbitingBody; 
			private JComboBox comboBoxManoeuvreType;
	// OUTPUTS
	private JPanel outputPanel;

	
	// Constructor
	public OrbitMainFrame()
	{
		getContentPane().setLayout(new MigLayout("", "[min!][][281.00][grow]", "[121.00][grow]"));
		
		// TOOLBAR - mig layout
			JPanel toolbarPanel = new JPanel();//MainToolbar();
			getContentPane().add(toolbarPanel, "cell 0 0 3 1,growx,aligny top");
			toolbarPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			toolbarPanel.setLayout(new MigLayout("", "[][17.00][79.00][109.00px][][100px]", "[][16px][][]"));
			
			JLabel lblToolbarHeading = new JLabel("Main scenario settings");
			lblToolbarHeading.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblToolbarHeading.setHorizontalAlignment(SwingConstants.LEFT);
			toolbarPanel.add(lblToolbarHeading, "cell 0 0 4 1");
			
		// Listeners   
			ActionListener comboBoxListener = new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JComboBox clicked = (JComboBox)e.getSource();
					
					if(clicked == comboBoxOrbitType)
					{
						System.out.println("combobox orbitType has been selected");
						inputPanelChanger(comboBoxOrbitType.getSelectedItem().toString());
						
					}
					else if (clicked == comboBoxOrbitingBody)
					{
						System.out.println("comboBoxOrbitingBody selected");
						System.out.println("...and the following was selected: " + comboBoxOrbitingBody.getSelectedItem().toString());
						switch(comboBoxOrbitingBody.getSelectedItem().toString()) {
						case "Select Orbiting Body":
							System.out.println("it did!");
							setOrbitingBody("null");
							orbitingBodyData = null;
							break;
						default:
							setOrbitingBody(comboBoxOrbitingBody.getSelectedItem().toString());
							SolarSystemDatabase ObjectSelectedToOrbit = new SolarSystemDatabase();
							if(orbitingBody != "null") {
								orbitingBodyData = ObjectSelectedToOrbit.getSolarSystemObjectInformation(orbitingBody.toLowerCase());
							}
							break;
						}
//						if (comboBoxOrbitingBody.getSelectedItem().toString() == "Select Orbiting Body")
//						{
//							System.out.println("it did!");
//							setOrbitingBody("null");
//							orbitingBodyData = null;
//						}
//						else
//						{
//						setOrbitingBody(comboBoxOrbitingBody.getSelectedItem().toString());
//						SolarSystemDatabase ObjectSelectedToOrbit = new SolarSystemDatabase();
//							if(orbitingBody != "null") {
//							orbitingBodyData = ObjectSelectedToOrbit.getSolarSystemObjectInformation(orbitingBody.toLowerCase());
//							}						
//						}
					}
					else if (clicked == comboBoxManoeuvreType) // It will probably be best to make this its own card panel with a default when not needed and then a different layout for the other choices. 
					{ // THE IF WONT WORK
						System.out.println("manoeuvre clicked");
						System.out.println(comboBoxManoeuvreType.getSelectedItem().toString());
						switch(comboBoxManoeuvreType.getSelectedItem().toString()) {
						case "Select Manoeuvre":
							System.out.println("select manoeuvre selected");
							manoeuvreType = "null";
							break;
						case "Orbit":
							System.out.println("Orbit selected");
							manoeuvreType = comboBoxManoeuvreType.getSelectedItem().toString();
							break;
						case "Hohmann Transfer":
							System.out.println("hohmann transfer");
							JOptionPane.showMessageDialog(null, "This Manoeuvre option is currently unavailable");
							break;
						}
					}
					else
					{
						System.out.println("Pretty sure this should never print, as there are no other comboBoxes but if I add one and forget to do anything with it I will know.");
					}
				}
			};
			
		// Components of toolbar
			//Buttons
			JButton btnNewScenario = new JButton("New Scenario");
			btnNewScenario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// are you sure message JOptionPane.showMessageDialog(null, "My Goodness, this is so concise"); 
					int reply = JOptionPane.showConfirmDialog(null, "Starting a new scenario will delete all inputs. Are you sure you want to do this?", null, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
					{// method to reset scenario
					newScenario();
					}
				}
			});
			toolbarPanel.add(btnNewScenario, "cell 5 1");
			
			// comboBoxes and associated arrays and lbls
			JLabel lblWhatOrbit = new JLabel("Orbit type:");
			lblWhatOrbit.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(lblWhatOrbit, "cell 2 1,alignx left,gapright 0,aligny center");
			
			ArrayList<String> orbitTypes = new ArrayList<String>();
			orbitTypes.add(new String(selectType));
			orbitTypes.add(new String(circular));
			orbitTypes.add(new String(elliptical));
			orbitTypes.add(new String(hyperbolic));
			comboBoxOrbitType = new JComboBox(orbitTypes.toArray());
			comboBoxOrbitType.addActionListener(comboBoxListener);
			comboBoxOrbitType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(comboBoxOrbitType, "cell 3 1,growx");
			
			JLabel lblOrbitingBody = new JLabel("Orbiting?");
			lblOrbitingBody.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(lblOrbitingBody, "cell 2 2,alignx left,aligny center");
			
			ArrayList<String> orbitingBody = new ArrayList<String>();
			orbitingBody.add(new String(selectOrbitingBody));
			orbitingBody.add(new String(earth));
			orbitingBody.add(new String(moon));
			orbitingBody.add(new String(mars));
			comboBoxOrbitingBody = new JComboBox(orbitingBody.toArray());
			comboBoxOrbitingBody.addActionListener(comboBoxListener);
			comboBoxOrbitingBody.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(comboBoxOrbitingBody, "cell 3 2,growx");
			
			JLabel lblManoeuvreType = new JLabel("Manoeuvre type?");
			lblManoeuvreType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(lblManoeuvreType, "cell 2 3,alignx left,aligny center");
			
			ArrayList<String> manoeuvreTypes = new ArrayList<String>();
			manoeuvreTypes.add(new String(selectManoeuvre));
			manoeuvreTypes.add(new String(orbit));
			manoeuvreTypes.add(new String(hohmannTransfer));
			comboBoxManoeuvreType = new JComboBox(manoeuvreTypes.toArray());
			comboBoxManoeuvreType.addActionListener(comboBoxListener);
			comboBoxManoeuvreType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			toolbarPanel.add(comboBoxManoeuvreType, "cell 3 3,growx");
		
		// INPUT Panel
			// Main panel - card Layout
				JPanel inputPanel = new JPanel();
				inputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
				getContentPane().add(inputPanel, "cell 0 1 3 1,grow");
				inputPanel.setLayout(new CardLayout(0, 0));
			
			// Default input panel - not sure wheter to giv this its own class or not...probably should...
				defaultInputPanel = new JPanel();
				inputPanel.add(defaultInputPanel, "defaultPanel");
				defaultInputPanel.setLayout(new MigLayout("", "[][][][][]", "[][][39.00]"));
				
				JLabel lblInputAreaHeading = new JLabel("Input Area");
				lblInputAreaHeading.setFont(new Font("Lucida Grande", Font.BOLD, 18));
				defaultInputPanel.add(lblInputAreaHeading, "cell 4 0");
				
				JLabel lblDefaultInputPanelText = new JLabel("<html>Please select the Orbit type above to begin<br>entering relevant data for the scenario<br>you wish to calculate.</html>");
				defaultInputPanel.add(lblDefaultInputPanelText, "cell 1 1 4 2");
			
			// first Input Panel - circular - mig layout
				circularPanel = new CircularOrbitInputs();
				inputPanel.add(circularPanel, "circular");
			
			// second Input panel - elliptical - mig layout
				ellipticalPanel = new EllipticalOrbitInputs();
				inputPanel.add(ellipticalPanel, "elliptical");
			
			
		
		// OUTPUT Panel
			final JPanel outputPanel = new OutputPanel();
			outputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(outputPanel, "cell 3 0 1 2,grow");
		
			
		// listeners
			//circular
			((CircularOrbitInputs) circularPanel).setNewGraphics(new MainFrameListener(/*r, v, T, epsilon, i*/) {
				//@Override
				public void setNewGraphics(double r, double v, double T,
						double epsilon, String renderScale, double i, ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray) {
					((orbitSimulator.OutputPanel) outputPanel).drawNewGraphics(r,v,T,epsilon, renderScale,i, canvasUArray, canvasVArray);
				}
				
			});
			// elliptical
			((EllipticalOrbitInputs) ellipticalPanel).setNewGraphics(new MainFrameListenerElliptical() {
				//@Override
				public void setNewGraphics(Double raan, Double ra, Double rp, Double a, Double e, Double i, Double VatR, 
						Double RforV, Double va, Double vp, Double epsilon, Double TrueAnomaly, Double T, String renderScale) {
					((orbitSimulator.OutputPanel) outputPanel).drawNewGraphics(raan, ra, rp, a, e, i, VatR, RforV, va, vp, 
							epsilon, TrueAnomaly, T, renderScale);
				}
			});
	
	} // END CONSTRUCTOR
	
	protected void inputPanelChanger(String newParam) {
		switch(newParam)
		{
		case "Select Type":
			newScenario();
			_orbitType = "none selected";
			break;
		case "Circular":
			circularPanel.setVisible(true);
			defaultInputPanel.setVisible(false);
			ellipticalPanel.setVisible(false);
			_orbitType = "circular";
			break;
		case "Elliptical":
			ellipticalPanel.setVisible(true);
			defaultInputPanel.setVisible(false);
			circularPanel.setVisible(false);
			//JOptionPane.showMessageDialog(null, "The functionality for this Orbit type has not yet been developed, please select another.");
			_orbitType = "elliptical";
			break;
		case "Hyperbolic":
			ellipticalPanel.setVisible(false);
			defaultInputPanel.setVisible(true);
			circularPanel.setVisible(false);
			_orbitType = "hyperbolic";
			JOptionPane.showMessageDialog(null, "Nothing has yet been implemented for this orbit type please select another.");
			break;
		default :
			// error message
			break;
		}
		
		
	}
	public void setOrbitingBody(String orbiting)
	{
		orbitingBody = orbiting;
	}
	public static double getOrbitingBodyData(String planetParam)
	{
		return orbitingBodyData.get(planetParam);
	}
	private void newScenario()
	{
		// reset input panel
		String activePanel = comboBoxOrbitType.getSelectedItem().toString().toLowerCase();
		switch(activePanel)
		{
		case "circular":
			CircularOrbitInputs.resetCircularPanel();
			
			break;
		case "elliptical":
			EllipticalOrbitInputs.resetEllipticalPanel();
			break;
		case "Hyperbolic":
			// HyperbolicOrbitInputs.resetHyperbolicPanel();
		}
		// reset output panel
		// reset input panel
		defaultInputPanel.setVisible(true);
		circularPanel.setVisible(false);
		ellipticalPanel.setVisible(false);
		// orbit type combo back to default 
		comboBoxOrbitType.setSelectedIndex(0);
		// orbiting body combo back to default 
		comboBoxOrbitingBody.setSelectedIndex(0);
		// manoeuvre combo back to default 
		comboBoxManoeuvreType.setSelectedIndex(0);
		// reset parameters
		setOrbitingBody("null");
		manoeuvreType = "null";
		orbitingBodyData = null;
	}

	
	

}
