package orbitSimulator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EllipticalOrbitInputs extends JPanel {
	// in put text fields - NB put the name of any added tf's to the inputTextFields array at the bottom of this list as a string
	private JTextField tfArgOfPeri;
	private JTextField tfPeriapsis;
	private JTextField tfApoapsis;
	private JTextField tfSemimajorAxis;
	private JTextField tfEccentricity;
	private JTextField tfOrbitalPeriod;
	private JTextField tfRAAN;
	private JTextField tfPeriod;
	// Array of input text fields
	private static String[] inputTextFields = {"tfArgOfPeri", "tfPeriapsis", "tfApoapsis", 
		"tfSemimajorAxis", "tfEccentricity", "tfOrbitalPeriod", "tfRAAN", "tfPeriod"};
	private static final Map<String, Double> userInputs = new HashMap<String, Double>();
	
	private MainFrameListenerElliptical newGraphicsListener;
	

	EllipticalOrbitInputs()
	{
		setLayout(new MigLayout("", "[22.00][29.00][][grow]", "[][][][][][][][][][][][][12.00][][][][][][][]"));
		
		JLabel lblEllipticalOrbitInputs = new JLabel("Elliptical Orbit Inputs");
		lblEllipticalOrbitInputs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblEllipticalOrbitInputs, "cell 0 0 3 1");
		
		JLabel lblArgumentOfPeriapsis = new JLabel("Argument of Periapsis");
		lblArgumentOfPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblArgumentOfPeriapsis, "cell 1 2 2 1");
		
		tfArgOfPeri = new JTextField();
		tfArgOfPeri.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfArgOfPeri, "cell 3 2,growx");
		tfArgOfPeri.setColumns(10);
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRadius, "cell 1 3 2 1");
		
		JLabel lblPeriapsis = new JLabel("Periapsis");
		lblPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblPeriapsis, "cell 2 4,alignx left");
		
		tfPeriapsis = new JTextField();
		tfPeriapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfPeriapsis, "cell 3 4,growx");
		tfPeriapsis.setColumns(10);
		
		JLabel lblApoapsis = new JLabel("Apoapsis");
		lblApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblApoapsis, "cell 2 5,alignx left");
		
		tfApoapsis = new JTextField();
		tfApoapsis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfApoapsis, "cell 3 5,growx");
		tfApoapsis.setColumns(10);
		
		JLabel lblSemimajorAxis = new JLabel("Semimajor Axis");
		lblSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSemimajorAxis, "cell 1 6 2 1");
		
		tfSemimajorAxis = new JTextField();
		tfSemimajorAxis.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfSemimajorAxis, "cell 3 6,growx");
		tfSemimajorAxis.setColumns(10);
		
		JLabel lblEccentricity = new JLabel("Eccentricity");
		lblEccentricity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblEccentricity, "cell 1 7 2 1");
		
		tfEccentricity = new JTextField();
		tfEccentricity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfEccentricity, "cell 3 7,growx");
		tfEccentricity.setColumns(10);
		
		JLabel lblInclination = new JLabel("Inclination");
		lblInclination.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblInclination, "cell 1 8 2 1,alignx left");
		
		tfOrbitalPeriod = new JTextField();
		tfOrbitalPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfOrbitalPeriod, "cell 3 8,growx,aligny top");
		tfOrbitalPeriod.setColumns(10);
		
		JLabel lblRaan = new JLabel("RAAN");
		lblRaan.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRaan, "cell 1 9 2 1");
		
		tfRAAN = new JTextField();
		tfRAAN.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfRAAN, "cell 3 9,growx");
		tfRAAN.setColumns(10);
		
		JLabel lblOrbitalPeriod = new JLabel("Orbital Period");
		lblOrbitalPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblOrbitalPeriod, "cell 1 10 2 1,alignx left");
		
		tfPeriod = new JTextField();
		tfPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfPeriod, "cell 3 10,growx");
		tfPeriod.setColumns(10);
		
		JButton btnCalculateEllipticalOrbit = new JButton("Calculate Orbit");
		add(btnCalculateEllipticalOrbit, "cell 3 11");
	} // END CONSTRUCTOR
	
	// listener model view controller architecture
		public void setNewGraphics(MainFrameListenerElliptical listener)
		{
			System.out.println("setNewGraphics()");
			this.newGraphicsListener = listener;
		}
		
		/*@Override -  I'm not sure why this isnt required as it is in CircularOrbitInputs using the same architecture. 
		               The only possibility I can think of is that circularPanel has a button which may mean that it needs 
		               overriding.*/
		//@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("in actionPerformed(ActionEvent e)");
			//getOrbitRenderScale();
			calculateEllipticalOrbit();
			//newGraphicsListener.setNewGraphics();
		}
	
		private void getOrbitRenderScale() {
				// TODO Auto-generated method stub
				
			}
	
		private void calculateEllipticalOrbit() {
			System.out.println("in calculateEllipticalOrbit()");	
			// get all the inputs and set them to private parameters 
				getUserInputs();
				// calculations (multiple methods)
				// write calculated values to relevant text fields
				
			}
		
		private void getUserInputs() {
			System.out.println(" in getUserIputs()");
			int count = inputTextFields.length;
			for(int i = 0; i < count; i++){
				String textFieldName = inputTextFields[i];
				Object textField = textFieldName;
				
				if (((JTextField) textField).getText() != null || ((JTextField) textField).getText() == "") {
					double textFieldValue = Double.parseDouble(((JTextField) textField).getText());
					String paramName = textFieldName.substring(2);
					
					userInputs.put(paramName, textFieldValue);
					System.out.println("item " + i + "entered into userInputs is: " + paramName + " = " + textFieldValue);
				}
			}
			
		}

		public static void resetEllipticalPanel() {
			// TODO Auto-generated method stub
			
		}
	
}
