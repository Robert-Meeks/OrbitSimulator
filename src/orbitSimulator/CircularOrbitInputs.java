package orbitSimulator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.ButtonGroup;

public class CircularOrbitInputs extends JPanel implements ActionListener {
	private static JTextField tfRadius;
	private static JTextField tfVelocity;
	private static JTextField tfSpecificMechanicalEnergy;
	private static JTextField tfPeriod;
	private static JTextField tfInclination;
	private JRadioButton rdbtnIllustrative;
	private JRadioButton rdbtnAccurate;
	private JButton btnCalculateOrbit;
	
	private MainFrameListener newGraphicsListener;
	
	private static JComboBox comboBoxCircularInputs;
	
	// general variables
	private double pi = Math.PI;
	// variables
	private double r = 0;
	private double v = 0; 
	private double epsilon = 0; 
	private double T = 0;
	private double i = 0;
	//vars that get set
	//--- private static String orbitingBody; // holds the name of the planet the user has selected to orbit as a string
	//---HashMap<String, Double> orbitingBodyData; // hold the data of the planet the user has chosen to orbit. 
	private String selectedInputVariable;
	private String renderScale;
	// for animation 
	private static ArrayList<Double> thetaArray = new ArrayList<Double>();
	private static ArrayList<Double> radiusArray = new ArrayList<Double>();
	private static ArrayList<Double> localXArray = new ArrayList<Double>();
	private static ArrayList<Double> localYArray = new ArrayList<Double>();
	private static ArrayList<Double> globalXArray = new ArrayList<Double>();
	private static ArrayList<Double> globalYArray = new ArrayList<Double>();
	private static ArrayList<Double> canvasUArray = new ArrayList<Double>();
	private static ArrayList<Double> canvasVArray = new ArrayList<Double>();
	
	// error params
	private int anErrorOccured;
	private String errorMessage = "null";
	private ArrayList<String> ERROR_OCCURED_CalculatingCircularOrbitMethod;
	private String orbitingBodyIsNullMessage = "- An Orbiting Body has not been selected\n";
	private String unselectedInputVar = "- Please select the Input you would like to diffine for\n  this scenario in the dropdown menu (r,v,epsilon,T)\n";
	private String manouevreIsNullMessage = "- Plaese select a manoeuvre type.";
	private String specificError_orbitRadiusIsTooSmall = "- The Orbit radius is inside the chossen planet, make it bigger.\n";
	private String specificError_orbitRadiusIsTooLarge = "- The Orbit radius is out side the Orbiting\n  Bodies Sphere of Influence, please reduce Radius.\n";
	private String specificError_InclinationOutsideOfExceptableRange = "- The orbit Inclination is outside of the exceptable range. Exceptible range: 0 <= i <=180\n";
	// warning params
	private int warningOccured;
	private String warningMessage = "null";
	private ArrayList<String> WARNING_OCCURED_CalculatingCircularOrbitMethod;
	private String specificWarning_radiusInsideAtmosphere = "- The orbit radius is small enough for the atmosphere to have a massive drag effect on the satellite.\n";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	CircularOrbitInputs()
	{
		
		setLayout(new MigLayout("", "[][17.00][][106.00,grow][grow][]", "[][][][][][][][][][][][][]"));
		
		JLabel lblCircularPanelHeader = new JLabel("Circular Orbit Inputs");
		lblCircularPanelHeader.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblCircularPanelHeader, "cell 0 0 3 1");
		
		String[] circularVariables = {"Variable", "Radius", "Velocity","Specific Mechanical Energy", "Period"};
		
		JLabel lblCircularIntro = new JLabel("Only one input can be defined at a time.");
		add(lblCircularIntro, "cell 2 1 2 1");
		
		JLabel lblSelectInput = new JLabel("Select Input");
		add(lblSelectInput, "cell 2 2,alignx left");
		comboBoxCircularInputs = new JComboBox(circularVariables);
		add(comboBoxCircularInputs, "cell 3 2 3 1,growx");
		
		comboBoxCircularInputs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JComboBox comboBoxCircularVariables = (JComboBox) e.getSource();
				Object selected = comboBoxCircularVariables.getSelectedItem();
				selectedInputVariable = selected.toString();
				switch(selectedInputVariable)
				{
				case "Variable":
					tfRadius.setEditable(false);
					tfVelocity.setEditable(false);
					tfSpecificMechanicalEnergy.setEditable(false);
					tfPeriod.setEditable(false);
					break;
				case "Radius":
					tfRadius.setEditable(true);
					tfVelocity.setEditable(false);
					tfSpecificMechanicalEnergy.setEditable(false);
					tfPeriod.setEditable(false);
					break;
				case "Velocity":
					tfRadius.setEditable(false);
					tfVelocity.setEditable(true);
					tfSpecificMechanicalEnergy.setEditable(false);
					tfPeriod.setEditable(false);
					break;
				case "Specific Mechanical Energy":
					tfRadius.setEditable(false);
					tfVelocity.setEditable(false);
					tfSpecificMechanicalEnergy.setEditable(true);
					tfPeriod.setEditable(false);
					break;
				case "Period":
					tfRadius.setEditable(false);
					tfVelocity.setEditable(false);
					tfSpecificMechanicalEnergy.setEditable(false);
					tfPeriod.setEditable(true);
					break;
				}
			}
		});
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRadius, "cell 2 4,alignx left");
		
		tfRadius = new JTextField();
		tfRadius.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tfRadius.setEditable(false);
		add(tfRadius, "cell 3 4,growx");
		tfRadius.setColumns(10);
		
		JLabel lblRadiusUnits = new JLabel("Km");
		lblRadiusUnits.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblRadiusUnits, "cell 4 4");
		
		JLabel lblVelocity = new JLabel("Velocity");
		lblVelocity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelocity, "cell 2 5,alignx left");
		
		tfVelocity = new JTextField();
		tfVelocity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tfVelocity.setEditable(false);
		tfVelocity.setColumns(10);
		add(tfVelocity, "cell 3 5,growx");
		
		JLabel lblVelocityUnits = new JLabel("Km/s");
		lblVelocityUnits.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblVelocityUnits, "cell 4 5");
		
		JLabel lblSpecificMechanicalEnergy = new JLabel("Specific Mechanical Energy");
		lblSpecificMechanicalEnergy.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSpecificMechanicalEnergy, "cell 2 6,alignx left");
		
		tfSpecificMechanicalEnergy = new JTextField();
		tfSpecificMechanicalEnergy.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tfSpecificMechanicalEnergy.setEditable(false);
		add(tfSpecificMechanicalEnergy, "cell 3 6,growx");
		tfSpecificMechanicalEnergy.setColumns(10);
		
		JLabel lblSpecificMechanicalEnergyUnits = new JLabel("kgkm/s");
		lblSpecificMechanicalEnergyUnits.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSpecificMechanicalEnergyUnits, "cell 4 6");
		
		JLabel lblPeriod = new JLabel("Period");
		lblPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblPeriod, "cell 2 7,alignx left");
		
		tfPeriod = new JTextField();
		tfPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tfPeriod.setEditable(false);
		add(tfPeriod, "cell 3 7,growx");
		tfPeriod.setColumns(10);
		
		JLabel lblPeriodUnits = new JLabel("s");
		lblPeriodUnits.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblPeriodUnits, "cell 4 7");
		
		JButton btnChangeInputVariable = new JButton("Change Input Variable");
		btnChangeInputVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetCircularPanel();
			}
		});
		
		btnCalculateOrbit = new JButton("Calculate Orbit");
		btnCalculateOrbit.addActionListener(this);
			
		JLabel lblInclination = new JLabel("Inclination");
		lblInclination.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblInclination, "cell 2 8,alignx left");

		tfInclination = new JTextField();
		tfInclination.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(tfInclination, "cell 3 8,growx,aligny top");
		tfInclination.setColumns(10);
		
		JLabel lblUnitDegree = new JLabel("\u00B0");
		add(lblUnitDegree, "cell 4 8");


		JLabel lblSelectDesiredScale = new JLabel("Select Desired Scale");
		lblSelectDesiredScale.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(lblSelectDesiredScale, "cell 2 9");
		
		rdbtnIllustrative = new JRadioButton("Illustrative");
		buttonGroup.add(rdbtnIllustrative);
		rdbtnIllustrative.setSelected(true);
		rdbtnIllustrative.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(rdbtnIllustrative, "cell 3 9");
		
		rdbtnAccurate = new JRadioButton("Accurate");
		buttonGroup.add(rdbtnAccurate);
		rdbtnAccurate.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		add(rdbtnAccurate, "cell 3 10");
		add(btnCalculateOrbit, "cell 3 11");
		add(btnChangeInputVariable, "cell 3 12");
		
		
	} //END CONSTRUCTOR
	
	// listener model view controller architecture
	public void setNewGraphics(MainFrameListener listener)
	{
		System.out.println("setNewGraphics()");
		this.newGraphicsListener = listener;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			// check a planet has been selected
			getScaleForRendering(); // not in the check Inputs because been designed that one has to be always selected
			
			/*try {
				CheckInputsBeforeCalculation(); // error methods are at the bottom of this class
			
			} catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "1 or more inputs are invalid.");
			}
			System.out.println("anErrorOccured just before CalculateCircularOrbit = " + anErrorOccured);
			int errors = warningOccured + anErrorOccured;
			switch(errors) {
			case 0:
				try {
					CalculateCircularOrbit();
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "The input has characters, please make sure the input is a number.");
				}
				break;
			default:
				break;
			}*/
			CalculateCircularOrbit();
			i = Double.parseDouble(tfInclination.getText());
			// calculate animation inputs required - vales made here are store in this class and sent as arguments through the listener architecture
			CalculateCircularAnimationInputs();
			newGraphicsListener.setNewGraphics(r, v, T, epsilon, renderScale, i, canvasUArray, canvasVArray);
	}
	
	private void getScaleForRendering() {
		
		if(rdbtnIllustrative.isSelected() == true)
		{
			renderScale = "illustrative";
		}
		else if(rdbtnAccurate.isSelected() == true)
		{
			renderScale = "Accurate";
		}
		
	}

	// Orbit method methods
	private void CalculateCircularOrbit() {
		System.out.println("CalculateCircularOrbit() has been entered");
		// if tfRadius isnt null then it is populated, if it is populated then the user has selected this var as the input and we want to call
		// the method that can calculate all the other parameters using the input.
		if(selectedInputVariable == null) {
			selectedInputVariable = "null";
		}
		switch(selectedInputVariable)
		{
		case "null":
			JOptionPane.showMessageDialog(null, "Please select an input variable.");
			break;
		case "Radius":
			System.out.println("the switch to recognise which input has been selected works");
			CalculateOrbitWithRadius(tfRadius.getText());
			break;
		case "Velocity":
			CalculateOrbitWithVelocity(tfVelocity.getText());
			break;
		case "Specific Mechanical Energy":
			CalculateOrbitWithSME(tfSpecificMechanicalEnergy.getText());
			break;
		case "Period":
			CalculateOrbitWithPeriod(tfPeriod.getText());
			break;
		}
		
		
		selectedInputVariable = null;
	}
	
	private void CalculateOrbitWithRadius(String radius) {
		System.out.println("now inside the calculateOrbitWithRadius()");
		// get vars required
		double mu = OrbitMainFrame.getOrbitingBodyData("mu");
		System.out.println("mu = " + mu);
		// change input var from string to double
		r = Double.parseDouble(radius);
		
		System.out.println("r = " + r);
		// work out answers 
		v = Math.sqrt(mu/r);
		System.out.println("v = " + v);
		epsilon = - mu/ (2 * r);
		System.out.println("epsilon = " + epsilon);
		T = 2 * pi * Math.sqrt(r*r*r / mu);
		System.out.println("T = " + T);
		
		// put answers in relevant textFields
		tfVelocity.setText(String.format("%.3f", v));
		System.out.println("v = " + v);
		tfSpecificMechanicalEnergy.setText(String.format("%.3f", epsilon));
		tfPeriod.setText(String.format("%.3f", T));
		
		
	}
	
	private void CalculateOrbitWithVelocity(String velocity) {
		// get vars required 
		double mu = OrbitMainFrame.getOrbitingBodyData("mu");
		// change input var from string to double
		v = Double.parseDouble(velocity);
		// work out answers
		r = mu / (v * v); 
		epsilon = - mu/ (2 * r);
		T = 2 * pi * Math.sqrt(r*r*r / mu);
		// put answers in relevant text fields
		tfRadius.setText(String.format("%.3f", r));
		tfSpecificMechanicalEnergy.setText(String.format("%.3f", epsilon));
		tfPeriod.setText(String.format("%.0f", T));
	}
	private void CalculateOrbitWithSME(String SME) { // NB SpecificMechanicalEnergy = SME = epsilon
		// get vars required 
		double mu = OrbitMainFrame.getOrbitingBodyData("mu");
		// change input var from string to double
		epsilon = Double.parseDouble(SME);
		// work out answers
		r = - mu / (2 * epsilon);
		v = Math.sqrt(mu/r);
		T = 2 * pi * Math.sqrt(r*r*r / mu);
		// put answers in relevant text fields
		tfRadius.setText(String.format("%.3f", r));
		tfVelocity.setText(String.format("%.3f", v));
		tfPeriod.setText(String.format("%.0f", T));
	}
	
	private void CalculateOrbitWithPeriod(String period) {
		// get vars required 
		double mu = OrbitMainFrame.getOrbitingBodyData("mu");
		// change input var from string to double
		T = Double.parseDouble(period);
		// work out answers
		r = Math.cbrt((Math.pow((T / (2*pi)), 2) * mu));
		v = Math.sqrt(mu/r);
		epsilon = - mu/ (2 * r);
		// put answers in relevant text fields
		tfRadius.setText(String.format("%.3f", r));
		tfVelocity.setText(String.format("%.3f", v));
		tfSpecificMechanicalEnergy.setText(String.format("%.3f", epsilon));
	}
	public static void resetCircularPanel()
	{
		comboBoxCircularInputs.setSelectedIndex(0);
		tfRadius.setText("");
		tfVelocity.setText("");
		tfSpecificMechanicalEnergy.setText("");
		tfPeriod.setText("");
		tfInclination.setText("");
		OutputPanel.resetOutput();
	}
	private static void CalculateCircularAnimationInputs() {
		System.out.println("In CalculateCircularAnimationInputs()");
		System.out.println("--------------------------------------");
		// assumptions send real values, will be scaled by the CanvasAnimation class 
		// ### TO BE ARGS ###
		double RAAN = 0; // if i refactor this need to install an IF in OrbitMainFrame to send RAAN = 0 in the case circular orbits are being calculated so thet the method has everything it needs  
			 
		
		// var Constants
		double pi = Math.PI;
		double twoPi = pi * 2;

		// General 
		double t = 0;
		double sun_mu = 132712440018000000000.0;
		double x0 = 236;
		double y0 = 217;
		double a = 1.496E11;
		double T = twoPi * Math.sqrt(Math.pow(a, 3) / sun_mu);
		double n = twoPi / T;
		double e = 0;
		// calculate points
		double iterator = 0.0025;
		
		// calculate theta 
		double step = 0;
		
		while (t < T)	{
			System.out.println("in theta while at iteration " + step);
			t = T * step;
			
			double M = n * t;
			
			double j = 0;
			/*let*/ double E = 0;
			while (j < 15) {
				E = M + (e * sin(E));
				j++;
			}
			
			double theta_R = (e - cos(E)) / ((e * cos(E)) - 1);
			theta_R = Math.acos(theta_R);
			double theta_ = theta_R * 180 / pi;
			System.out.println("theta_ = " + theta_ + " and test theta_ + 2 = " + (theta_ + 2));
			if (Double.isNaN(theta_)) { System.out.println("IT DOES THINK ITS NaN...");}
			if (t < (0.5 * T)) {
				if(theta_ == 0) {
					thetaArray.add(theta_ + 0.001);
				} else {
					thetaArray.add(theta_);
				}
				//console.log("at " + i + "T theta = " + theta_);
			} else if (t > 0.5 * T) {
				thetaArray.add(180 - theta_ + 180);
				//console.log("at " + i + "T theta = " + (180 - theta_ + 180));
			}
			
			step = step + iterator;
		}
		// calculate radius
		int thetaArrayLength = thetaArray.size();
		int i = 0;
		for (i = 0; i < thetaArrayLength; i++) {
			double theta_ = thetaArray.get(i);
			double theta_R = theta_ * Math.PI / 180;
	
			double r = (a * (1 - (e * e)) / (1 + (e * cos(theta_R))));
			radiusArray.add(r);
			//console.log("r = " + r);
		}
		// polar to local
			// x
		i = 0;
		
		double scaler = 1345274666.7;
		for (i = 0; i < thetaArrayLength; i++) {
			double theta_ = thetaArray.get(i);// need to get scale here 
			double r = radiusArray.get((int) i);
			double lx = 0;
			double ly = 0;
			
			// x
			if (theta_ <= 90) { 
				lx = (r / scaler) * Math.cos(theta_ * Math.PI / 180);
				//console.log("when theta is < 90 lx = " + lx);
			} else if (theta_ <= 180) {
				lx = -1*((r / scaler) * Math.cos((180 - theta_) * Math.PI / 180));
				//console.log("when theta is < 180 lx = " + lx);
			} else if (theta_ <= 270) {
				lx = (r / scaler) * Math.cos((theta_) * Math.PI / 180);
				//console.log("when theta is < 270 lx = " + lx);
			} else if (theta_ <= 360) {
				lx = (r / scaler) * Math.cos((theta_) * Math.PI / 180);
				//console.log("when theta is < 360 lx = " + lx);
			}
			localXArray.add(lx);
		
			// y
			if (theta_ <= 90) { 
				ly = (r / scaler) * Math.sin(theta_ * Math.PI / 180);
				//console.log("when theta is < 90 ly = " + ly);
			} else if (theta_ <= 180) {
				ly = (r / scaler) * Math.sin((180 - theta_) * Math.PI / 180);
				//console.log("when theta is < 180 ly = " + ly);
				
			} else if (theta_ <= 270) {
				ly = (r / scaler) * Math.sin(theta_ * Math.PI / 180);
				//console.log("when theta is < 270 ly = " + ly);
			} else if (theta_ <= 360) {
				ly = (r / scaler) * Math.sin(theta_ * Math.PI / 180);
				//console.log("when theta is < 360 ly = " + ly);
			}
			localYArray.add(ly);
		}
		
		// local to global
		i=0;
		int j = localYArray.size();
		for ( i = 0; i < j; i++) {
			// get vals 
			double lx = localXArray.get(i);
			double ly = localYArray.get(i);
			//x
			double gx = lx * cos(RAAN * pi / 180) - ly * sin(RAAN * pi / 180);
			globalXArray.add(gx);
			//console.log("gx = " + gx);
			//y
			double gy = lx * sin(RAAN * pi / 180) + ly * cos(RAAN * pi / 180);
			globalYArray.add(gy);
			//console.log("gy = " + gy);
			//console.log("");
		}
		// global to UV (canvas)
		i = 0;
		int globalArrayLength = globalXArray.size();
		for (i = 0; i < globalArrayLength; i++) {
			double gx = globalXArray.get(i);
			double gy = globalYArray.get(i);

			double u = gx + x0;
			double v = y0 - gy;
			
			canvasUArray.add(u);
			canvasVArray.add(v);

			//console.log("u = " + u);
			//console.log("v = " + v);
			//console.log("");
			
		}
		
		// NB helper functions cos and sin
	}
		//-------------------------------
		// helpers
		private static double cos(double a) {
			double ans = Math.cos(a);
			return ans;
		}

		private static double sin(double a) {
			double ans = Math.sin(a);
			return ans;
		}
	//==============================================================================================================================
	// Error handling
	private void CheckInputsBeforeCalculation() {
		anErrorOccured = 0;
		warningOccured = 0;
		errorMessage = "";
		warningMessage = "";
		ERROR_OCCURED_CalculatingCircularOrbitMethod = new ArrayList<String>();
		WARNING_OCCURED_CalculatingCircularOrbitMethod = new ArrayList<String>();
		ERROR_OCCURED_CalculatingCircularOrbitMethod.clear();
		ERROR_OCCURED_CalculatingCircularOrbitMethod.add(0, "The following Errors occured:\n\n");
		WARNING_OCCURED_CalculatingCircularOrbitMethod.add(0, "Please note the following warnings:\n\n");
		
		// Toolbar inputs
		if (OrbitMainFrame.orbitingBody == "null")
		{
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(orbitingBodyIsNullMessage); 
		}
		
		if (OrbitMainFrame.manoeuvreType == "null") {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(manouevreIsNullMessage);
		}
		//  if no input is entered
		if (tfRadius.getText() == null && selectedInputVariable != comboBoxCircularInputs.getSelectedItem()) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(unselectedInputVar);
		}
		if (tfVelocity.getText() == null && selectedInputVariable != comboBoxCircularInputs.getSelectedItem()) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(unselectedInputVar);
		}
		if (tfSpecificMechanicalEnergy.getText() == null && selectedInputVariable != comboBoxCircularInputs.getSelectedItem()) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(unselectedInputVar);
		}
		if (tfPeriod.getText() == null && selectedInputVariable != comboBoxCircularInputs.getSelectedItem()) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(unselectedInputVar);
		}
		System.out.println(tfInclination.getText());
		if (tfInclination.getText() == null) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(unselectedInputVar);
		}
		
		if (selectedInputVariable == "Radius")
		{
			double checkRadius = Double.parseDouble(tfRadius.getText());
			
			if(checkRadius < OrbitMainFrame.getOrbitingBodyData("radius"))
			{
				anErrorOccured = anErrorOccured + 1;
				ERROR_OCCURED_CalculatingCircularOrbitMethod.add(specificError_orbitRadiusIsTooSmall);
			}
			// if radius of orbit is too big
			if(checkRadius > OrbitMainFrame.getOrbitingBodyData("SOI"))
			{
				anErrorOccured = anErrorOccured + 1;
				ERROR_OCCURED_CalculatingCircularOrbitMethod.add(specificError_orbitRadiusIsTooLarge);
			}
				// radius of the orbit is inside atmosphere
			if(checkRadius > OrbitMainFrame.getOrbitingBodyData("radius") && checkRadius < OrbitMainFrame.getOrbitingBodyData("atmosphereRadius"))
			{
				warningOccured = warningOccured + 1;
				WARNING_OCCURED_CalculatingCircularOrbitMethod.add(specificWarning_radiusInsideAtmosphere);
			}
		}
		if(Double.parseDouble(tfInclination.getText()) < 0 || Double.parseDouble(tfInclination.getText()) > 180) {
			anErrorOccured = anErrorOccured + 1;
			ERROR_OCCURED_CalculatingCircularOrbitMethod.add(specificError_InclinationOutsideOfExceptableRange);
		}
//		
		System.out.println("error occured = " + anErrorOccured);
		// ERROR MESSAGE
		if(anErrorOccured > 0) {
			for(String i : ERROR_OCCURED_CalculatingCircularOrbitMethod) {
				errorMessage = errorMessage + i;
			}
			JOptionPane.showMessageDialog(null, errorMessage + "\n-----------------------------------\n Please make these corrections to procede.");
		} else {
			anErrorOccured = 0;
		}
		
		// WARNING MESSAGE
		if(warningOccured > 0 && anErrorOccured == 0)
		{
			
			String[] message = new String[WARNING_OCCURED_CalculatingCircularOrbitMethod.size()];
			WARNING_OCCURED_CalculatingCircularOrbitMethod.toArray(message);
			
			//for(int i = 0; i < WARNING_OCCURED_CalculatingCircularOrbitMethod.size(); i++)
			for(String i : message)
			{
				warningMessage = warningMessage + i; //message[i];
			}
			
			int reply = JOptionPane.showConfirmDialog(null, warningMessage + "\n-----------------------------------\n If you choose to correct the above, click Cancel and when changes\n have been made reselect the calculate button.\n To ignore warnings click on OK", null, JOptionPane.OK_CANCEL_OPTION);
			if (reply == JOptionPane.OK_OPTION) {// method to reset scenario
				warningOccured = 0;
			}
			else if (reply == JOptionPane.CANCEL_OPTION) {
				
			}
		}
		
		
	}


}
